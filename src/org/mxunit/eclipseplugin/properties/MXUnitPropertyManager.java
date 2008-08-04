package org.mxunit.eclipseplugin.properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;

/**
 * Performs the actual work of retrieving and setting project-level preferences. 
 * Used by the MXUnitPropertyPage object
 * @author Marc Esher
 */


public class MXUnitPropertyManager {
	
	private final QualifiedName compRootQName = new QualifiedName(MXUnitPlugin.ID,MXUnitPreferenceConstants.P_PROJECT_COMPONENTROOT);
	private final QualifiedName urlQName = new QualifiedName(MXUnitPlugin.ID,MXUnitPreferenceConstants.P_PROJECT_FACADEURL);
	private final QualifiedName usernameQName = new QualifiedName(MXUnitPlugin.ID,MXUnitPreferenceConstants.P_PROJECT_USERNAME);
	private final QualifiedName passwordQName = new QualifiedName(MXUnitPlugin.ID,MXUnitPreferenceConstants.P_PROJECT_PASSWORD);
	
	public MXUnitPropertyManager(){
		
	}	
	
	private String getProp(IResource resource, QualifiedName qname){
		String prop = "";
		try {
			prop = resource.getPersistentProperty(qname);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(prop == null){
			prop = "";
		}
		return prop;
	}
	
	private void setProp(IResource resource, QualifiedName qname, String propValue){
		try {
			resource.setPersistentProperty(qname, propValue);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getComponentPropertyValue(IResource resource){
		return getProp(resource,compRootQName);		
	}
	
	public String getURLPropertyValue(IResource resource){
		return getProp(resource,urlQName);		
	}
	
	public String getUsernamePropertyValue(IResource resource) {
		return getProp(resource,usernameQName);
	}


	public String getPasswordPropertyValue(IResource resource) {
		return getProp(resource,passwordQName);
	}
	
	
	
	
	public void setComponentPropertyValue(IResource resource, String componentRoot) {
		setProp(resource,compRootQName,componentRoot);		
	}

	public void setURLPropertyValue(IResource resource, String url) {
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		setProp(resource,urlQName,url);		
	}


	public void setUsernamePropertyValue(IResource resource, String username) {
		setProp(resource,usernameQName,username);
	}


	public void setPasswordPropertyValue(IResource resource, String password) {
		setProp(resource,passwordQName,password);
	}


	

}
