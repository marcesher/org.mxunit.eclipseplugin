package org.mxunit.eclipseplugin.actions.util;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.RemoteFacade;
import org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.RemoteFacadeRegistry;
import org.mxunit.eclipseplugin.model.RemoteServerType;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestElementType;
import org.mxunit.eclipseplugin.model.TestMethod;
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
	private String username;
	private String password;
	
	private RemoteFacadeRegistry registry = RemoteFacadeRegistry.getRegistry();
	

	public RemoteCallCreator() {
		prefs = MXUnitPlugin.getDefault().getPluginPreferences();
		props = new MXUnitPropertyManager();
	}


	public RemoteFacade createFacade(ITest testelement) {
		IResource resource = determineResource(testelement);

		facadeURL = determineURL(resource);
		if(resource != null){
			username = props.getUsernamePropertyValue(resource.getProject());
			password = props.getPasswordPropertyValue(resource.getProject());
		}
		try {
			RemoteFacadeServiceLocator locator = new RemoteFacadeServiceLocator(facadeURL, username, password); 
			
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
		return facade;
	}

	public String getFacadeURL() {
		return facadeURL;
	}

	public Exception getCurrentException() {
		return currentException;
	}

	private IResource determineResource(ITest testelement) {
		IResource res = null;
		ITest item = testelement;
		String filepath = "";
		if (item.getTestElementType() == TestElementType.TESTCASE) {
			filepath = ((TestCase) item).getFilePath();
		} else {
			filepath = ((TestMethod) item).getParent().getFilePath();
		}
		IPath path = new Path(filepath);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile ifile = root.getFileForLocation(path);
		if(ifile != null){
			res = root.findMember(ifile.getFullPath());
		}else{
			MXUnitPluginLog.logWarning("Bad news... resource is null for filepath " + filepath);
		}
		
		return res;
	}

	private String determineURL(IResource resource) {
		String urlToUse = prefs.getString(MXUnitPreferenceConstants.P_FACADEURL);
		String projectFacadeURL = "";
		
		if(resource != null){
			projectFacadeURL = props.getURLPropertyValue(resource.getProject());
		}else{
			MXUnitPluginLog.logInfo("Resource is null... using preferences facadeURL");
		}
		if (projectFacadeURL != null && projectFacadeURL.length() > 0) {
			urlToUse = projectFacadeURL;
		}
		if (urlToUse.toLowerCase().endsWith(".cfc")) {
			urlToUse += "?wsdl";
		}
		MXUnitPluginLog.logInfo("Determined facadeURL to be " + urlToUse);
		
		return urlToUse;
	}

	public boolean runPing() {
		boolean ok = false;
		try {
			ok = facade.ping();
		} catch (Exception e) {
			currentException = e;
			MXUnitPluginLog.logError("Error running ping to URL " + facadeURL,e);
		}
		return ok;
	}

}