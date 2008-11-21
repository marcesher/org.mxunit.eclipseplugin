package org.mxunit.eclipseplugin.actions;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
	private String webroot;
	private String testFilter;
	TestSuite suite;
	
	public TestSuiteCreator(){
		prefs = MXUnitPlugin.getDefault().getPluginPreferences();
		props  = new MXUnitPropertyManager();
		webroot = prefs.getString(MXUnitPreferenceConstants.P_WEBROOTPATH);
		testFilter = "(?i)Test.*.cfc|(?i).*Test.cfc";
		suite = new TestSuite();
	}
	
	public TestSuite createSuite(IResource[] resources){
		//for now, i'm only supporting a single resource being selected; in the future
		//I may add support for multiples; this should "just work"
		IResource startResource = resources[0];
		for (int i = 0; i < resources.length; i++) {
			MXUnitPluginLog.logInfo("Adding to suite with resource named " + resources[i] + "; webroot is " + webroot + "; rawLocation is " + resources[i].getRawLocation().toString());
			collectFiles(resources[i]);
		}
		
		if(startResource.getType() == IResource.FOLDER){
			suite.setName( startResource.getFullPath().toString() );
		}else{
			suite.setName( suite.getTestsAsArray()[0].getName() );
		}
		
		return suite;
	}
	
	public TestSuite createSuiteOLD(IResource[] resources){
		TestSuite suite = new TestSuite();
		
		//initialize the roots to work with: webroot (e.g. c:\inetpub\wwwroot ) or componentroot (eg. components)
        String componentRoot = "";
        String componentName = "";		    
		IWorkspaceRoot wsroot = ResourcesPlugin.getWorkspace().getRoot();
		
		IResource res = resources[0];
		
		File selectedResourceAsFile = new File(res.getRawLocation().toString());			
		
		componentRoot = getProjectComponentRoot(res);			
		
		String fileRelativeToRoot = res.getProjectRelativePath().toString();		
					
		MXUnitPluginLog.logInfo("webroot is " + webroot + "; selectedResourceAsFile is " + selectedResourceAsFile.toString() + "; componentRoot is " + componentRoot + "; fileRelativeToRoot is " + fileRelativeToRoot);
		
		
		
		if(res.getType() == IResource.FOLDER){		
			
			
			//printVisitorStuff(res);
			
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
	
	private void collectFiles(IResource resource){
		IResourceVisitor visitor = new TestVisitor();
		try {
			resource.accept(visitor);
		} catch (CoreException e) {
			MXUnitPluginLog.logError("Error visiting test resources with Resource " + resource.toString(), e);
		}
		
	}
	
	
	private String deriveCFCPath(IResource resource){
		String path = "";
		String propValue = "";
		
		//loop over all parents up to the project;
		//if any parents have a property configured for cfc path
		//then attach that root to the component and return;
		//otherwise, derive it from the webroot
		
		IResource currentParent = resource;
		IPath p = resource.getFullPath().removeFileExtension();
		for(int i = p.segmentCount()-1; i > 0;i--){
			currentParent = currentParent.getParent();
			if(currentParent.getType() == IResource.ROOT){
				break;
			}
			
			propValue = props.getComponentPropertyValue(currentParent).trim();
			if(propValue.length()>0){
				path = propValue + "." + p.removeFirstSegments(i).toString();
				path = path.replaceAll("/", ".");
				break;
			}
		}
		
		if(path.length() == 0){
			//File selectedResourceAsFile = new File(resource.getRawLocation().toString());
			path = PathUtils.deriveComponentPath(webroot, resource.getRawLocation().toString());
		}
		
		return path;
	}
	
	
	class TestVisitor implements IResourceVisitor{

		public boolean visit(IResource resource) throws CoreException {
			if(resource.getType() == IResource.FILE && 
					resource.getFullPath().lastSegment()
					.matches(testFilter)){
				
				String path = deriveCFCPath(resource);
				addTestToSuite(suite, resource.getRawLocation().toString(), path);
			}
			return true;
		}
		
	}
}
