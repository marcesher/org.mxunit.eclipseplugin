package org.mxunit.eclipseplugin.actions;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.dialogs.MessageDialog;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;
import org.mxunit.eclipseplugin.properties.MXUnitPropertyManager;
import org.mxunit.eclipseplugin.utils.PathUtils;

public class TestSuiteCreator {

	
	private Preferences prefs;
	private MXUnitPropertyManager props;   
	
	public TestSuiteCreator(){
		prefs = MXUnitPlugin.getDefault().getPluginPreferences();
		props  = new MXUnitPropertyManager();
	}
	
	public TestSuite createSuite(IResource[] resources){
		TestSuite suite = new TestSuite();
		
		//initialize the roots to work with: webroot (e.g. c:\inetpub\wwwroot ) or componentroot (eg. components)
        String webroot = prefs.getString(MXUnitPreferenceConstants.P_WEBROOTPATH);
        String componentRoot = "";
        String componentName = "";		    
		IWorkspaceRoot wsroot = ResourcesPlugin.getWorkspace().getRoot();
		
		IResource res = resources[0];
		
		File selectedResourceAsFile = new File(res.getRawLocation().toString());			
		
		componentRoot = getProjectComponentRoot(res);			
		
		String fileRelativeToRoot = res.getProjectRelativePath().toString();		
					
		MXUnitPluginLog.logInfo("webroot is " + webroot + "; selectedResourceAsFile is " + selectedResourceAsFile.toString() + "; componentRoot is " + componentRoot + "; fileRelativeToRoot is " + fileRelativeToRoot);
		
		
		
		if(res.getType() == IResource.FOLDER){		
			IContainer container = (IContainer)res;
			
			try {
				IResource[] children = container.members();
				for (int i = 0; i < children.length; i++) {
					System.out.println("resource: " + children[i].getProjectRelativePath().toString());
				}
				
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//get all cfcs in the directory
			suite.setName(res.getFullPath().toString());
			MXUnitPluginLog.logInfo("TestSuiteCreator: Passing " + selectedResourceAsFile + " to PathUtils.getTestComponents");
			Collection<File> components = PathUtils.getTestComponents( selectedResourceAsFile  );
			for (File file : components) {						    
			    if(componentRoot.length()>0){  
			    	IFile fileResource = wsroot.getFileForLocation(new Path(file.getAbsolutePath()));			    	
			    	fileRelativeToRoot = fileResource.getProjectRelativePath().toString();
			    	
			    	MXUnitPluginLog.logInfo("MXUnit TestSuiteCreator: file is " + file.toString() + "; fileRelativeToRoot is " + fileRelativeToRoot + "; componentRoot is " + componentRoot);
			    	
                    componentName = PathUtils.deriveComponentPathRelativeToProject(componentRoot, fileRelativeToRoot);
			    }else{
			        componentName = PathUtils.deriveComponentPath(webroot, file.toString());
			    }
				
				addTestToSuite(suite, file.getAbsolutePath(), componentName);
			}
		}else{	                    
            if(componentRoot.length()>0){                                                
                componentName = PathUtils.deriveComponentPathRelativeToProject(componentRoot, fileRelativeToRoot);
            }else{
                componentName = PathUtils.deriveComponentPath(webroot, selectedResourceAsFile.toString());
            }
			suite.setName(componentName);
			System.out.println("componentname is " + componentName);
			addTestToSuite(suite, selectedResourceAsFile.getAbsolutePath(), componentName);
		}		
		
		MXUnitPluginLog.logInfo("MXUnit TestSuiteCreator: Using file as top-level resource: " + selectedResourceAsFile);
		
		return suite;
	}
	
	/**
	 * returns a component root for the resource's project if one is defined; otherwise, returns an empty string
	 * @param resource the resource in question
	 * @return String
	 */
	public String getProjectComponentRoot(IResource resource){
		String componentRoot = props.getComponentPropertyValue(resource.getProject());
        if(componentRoot ==null){
            componentRoot = "";
        }
        return componentRoot;
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public void alertIfResourceNotConfigured(IResource resource){		
		MXUnitPluginLog.logInfo("No webroot or component root defined for resource " + resource.toString());
		MessageDialog.openInformation( null, "No webroot or component root defined", "Either a webroot or component root must be defined.\n\nIf your test files are located under the webroot, please specify the webroot in Window -- Preferences -- MXUnit.\n\nIf the test files in this project are located outside of your webroot, please specify a component root in Project -- Properties -- MXUnit");			
	}
	
	public boolean isResourceConfigured(IResource resource){
		String componentRoot = getProjectComponentRoot(resource);
		String webroot = prefs.getString(MXUnitPreferenceConstants.P_WEBROOTPATH);
		boolean configured = true;
		
		if(webroot.length()==0 && componentRoot.length()==0){
			configured = false;
		}
		return configured;
	}
	
	/**
	 * convenience method for adding a resource to the suite
	 * @param suite The TestSuite to modify
	 * @param fullPath full path to component
	 * @param name component name using dot notation
	 */
	private void addTestToSuite(TestSuite suite, String fullPath, String name){
		TestCase tc = new TestCase();
		tc.setFilePath(fullPath);
		tc.setName(name);
		suite.addTest(tc);
		MXUnitPluginLog.logInfo("MXUnit TestSuiteCreator: Adding Test To Suite: fullPath is " + fullPath + ";name is" + name);
	}
}
