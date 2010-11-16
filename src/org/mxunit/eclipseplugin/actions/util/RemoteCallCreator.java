package org.mxunit.eclipseplugin.actions.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Stub;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.ui.PlatformUI;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.bindings.Custom_RemoteFacadeServiceLocator;
import org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.RemoteFacadeRegistry;
import org.mxunit.eclipseplugin.model.RemoteServerType;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;
import org.mxunit.eclipseplugin.properties.MXUnitPropertyManager;

/**
 * For a given resource, creates a call that can be used for executing the
 * RemoteFacade
 * 
 * @author marc esher
 * 
 */
public class RemoteCallCreator {

	private Preferences prefs;
	private MXUnitPropertyManager props;
	private String facadeURL = "";
	private Exception currentException;

	private RemoteFacade facade;
	private String username = "";
	private String password = "";
	
	private RemoteFacadeRegistry registry = RemoteFacadeRegistry.getRegistry();
	

	public RemoteCallCreator() {
		prefs = MXUnitPlugin.getDefault().getPluginPreferences();
		props = new MXUnitPropertyManager();
	}
	
	public boolean canPingFacade(String facadeURL, String username, String password) {
		initFacade(facadeURL,username,password);
		return runPing();
	}

	private void initFacade(String facadeURL, String username, String password) {
		RemoteFacade facade = null;
		this.facadeURL = facadeURL;
		this.username = username;
		this.password = password;
		int timeout = prefs.getInt(MXUnitPreferenceConstants.P_REMOTE_CALL_TIMEOUT);
		try {
			Custom_RemoteFacadeServiceLocator locator = new Custom_RemoteFacadeServiceLocator();
			locator.setRemoteFacadeCfcEndpointAddress(facadeURL);
			
			//Custom_RemoteFacadeServiceLocator locator = new Custom_RemoteFacadeServiceLocator(facadeURL); 
			
			if(registry.getRegisteredFacadeType(facadeURL) == null){
				MXUnitPluginLog.logInfo(facadeURL + " is not registered. Attempting to get server type and register...");
				facade = locator.getRemoteFacadeCfc();
				
				
				String serverTypeString = facade.getServerType();
				RemoteServerType type = registry.registerFacade(facadeURL, serverTypeString);
				MXUnitPluginLog.logInfo(facadeURL + " registered as type " + type);
			}
			
			
			//this could return either the normal Binding OR the BlueDragon binding			
			locator.setRemoteServerType(  registry.getRegisteredFacadeType(facadeURL)  );
        	facade = locator.getRemoteFacadeCfc();
			
			
			 //only use this if we need to send credentials; it slows it down otherwise
            if (username.length() > 0) {  
            	setCredentials();	     
            }
            
            ((Stub) facade).setTimeout(timeout*1000);			
            
		} catch (ServiceException e) {			
			currentException = e;
			MXUnitPluginLog.logError("ServiceException getting remote facade", e);
		} catch (CFCInvocationException e) {
			currentException = e;
			MXUnitPluginLog.logError("CFCInvocationException calling getServerType", e);
		} catch (RemoteException e) {
			currentException = e;
			MXUnitPluginLog.logError("RemoteException calling getServerType", e);
		}
		this.facade = facade;
	}


	public RemoteFacade createFacade(ITest testelement) {
		IResource resource = testelement.getResource();
		facadeURL = determineURL(resource);
		if(resource != null){
			username = props.getUsernamePropertyValue(resource.getProject());
			password = props.getPasswordPropertyValue(resource.getProject());
		}
		initFacade(facadeURL,username,password);
		return facade;
	}


	/**
	 * 
	 */
	private void setCredentials() {
		System.out.println("Credentials are not empty. Setting...");
		((Stub) facade).setUsername(username);
		((Stub) facade).setPassword(password);		
	}

	public String getFacadeURL() {
		return facadeURL;
	}
	
	public String getFacadeURL(ITest testelement){
		IResource resource = testelement.getResource();
		return determineURL(resource);
	}

	public Exception getCurrentException() {
		return currentException;
	}

	public String determineURL(IResource resource) {
		String urlToUse = prefs.getString(MXUnitPreferenceConstants.P_FACADEURL);
		String resourceFacadeURL = "";
		IResource resourceParent;

		if(resource != null){
			// check for facadeUrl
			resourceParent = resource;
			while(resourceParent.getParent() != null) {
				resourceFacadeURL = props.getURLPropertyValue(resourceParent);
				if(resourceFacadeURL.length() > 0) {
					break;
				}
				resourceParent = resourceParent.getParent();
			}
			if(resourceFacadeURL.length() < 1) {
				resourceFacadeURL = props.getURLPropertyValue(resource.getProject());				
			}
		}else{
			MXUnitPluginLog.logInfo("Resource is null... using preferences facadeURL");
		}
		if (resourceFacadeURL != null && resourceFacadeURL.length() > 0) {
			urlToUse = resourceFacadeURL;
		}
		
		//tiny little helper; see http://www.barneyb.com/barneyblog/2008/07/18/mx-unit-is-slick/ for the genesis of this little change
		urlToUse = urlToUse.trim();
		if(!urlToUse.toLowerCase().endsWith(".cfc") && !urlToUse.toLowerCase().endsWith("wsdl")){
			urlToUse += "/mxunit/framework/RemoteFacade.cfc";
		}
		
		if (urlToUse.toLowerCase().endsWith(".cfc")) {
			urlToUse += "?wsdl";
		}
		
		MXUnitPluginLog.logInfo("Determined facadeURL to be " + urlToUse);
		
		return urlToUse;
	}

	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	public boolean runPing() {
		boolean ok = false;
		try {
			ok = facade.ping();
		} catch (org.apache.axis.AxisFault e) {
			currentException = e;

			// pop open a browser to display server response
			try{				
				String html = getStackTrace(e);
				int htmlStart = html.indexOf("faultDetail:");
				int htmlEnd = html.lastIndexOf("{http://xml.apache.org/axis/}");
				html = html.substring(htmlStart,htmlEnd);
				// super cheese unescaping of xml
				final String finalhtml = html.replaceAll("&amp;","&").replaceAll("&lt;","<").replaceAll("&gt;",">");
				
				PlatformUI.getWorkbench().getDisplay().syncExec(
						new Runnable(){
							public void run(){
								new HTMLViewer().display(finalhtml);								
							}
						}
				);
				
			} catch (Exception any) {
			    MXUnitPluginLog.logError("Error displaying server response",any);
			}

		    MXUnitPluginLog.logError("Error running ping to URL " + facadeURL,e);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ok;
	}

}
