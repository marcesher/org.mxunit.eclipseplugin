package org.mxunit.eclipseplugin.actions;

import java.util.Arrays;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.properties.MXUnitPropertyManager;

public class TestSuiteCreator {
	private MXUnitPropertyManager props;   
	private String testFilter;
	private TestSuite suite;
	/* slashes and periods as the component root property indicate "don't prefix anything underneath this container with a root, but don't try to derive the component path from the webroot, either */
	private String[] emptyPathIndicators = {"/","\\","."};
	
	public TestSuiteCreator(){
		props  = new MXUnitPropertyManager();
		testFilter = "(?i)Test.*.cfc|(?i).*Test.cfc";
		suite = new TestSuite();
		Arrays.sort(emptyPathIndicators);
	}


	
	/**
	 * This is the heart 'n soul of gathering up all the tests for the selected project/folder/test. It crawls
	 * through directories and finds files that look like tests. It then derives the CFC notation (dot notation) 
	 * for each file.
	 * @param resources the selected resources (tests, folders, projects)
	 * @return a brand spankin' new TestSuite
	 */
	public TestSuite createSuite(IResource[] resources){
		IResource startResource = resources[0];
		for (int i = 0; i < resources.length; i++) {
			MXUnitPluginLog.logInfo("Adding to suite with resource named " + resources[i] + "; rawLocation is " + resources[i].getRawLocation().toString());
			collectFiles(resources[i]);
		}
		
		if(startResource.getType() == IResource.FOLDER){
			suite.setName( startResource.getFullPath().toString() );
		}else{
			suite.setName( suite.getTestsAsArray()[0].getName() );
		}
		
		return suite;
	}
	
	private void collectFiles(IResource resource){
		IResourceVisitor visitor = new TestVisitor();
		try {
			resource.accept(visitor);
		} catch (CoreException e) {
			MXUnitPluginLog.logError("Error visiting test resources with Resource " + resource.toString(), e);
		}
	}

	
	/**
	 * convenience method for adding a resource to the suite
	 * @param suite The TestSuite to modify
	 * @param name component name using dot notation
	 * @param resource The Eclipse Resource backing this element
	 */
	private void addTestToSuite(TestSuite suite, String name, IResource resource){
		TestCase tc = new TestCase();
		tc.setResource(resource);
		tc.setFilePath(resource.getRawLocation().toString());
		tc.setName(name);
		suite.addTest(tc);
		MXUnitPluginLog.logInfo("MXUnit TestSuiteCreator: Adding Test To Suite: fullPath is " + tc.getFilePath() + "; name is " + name);
	}
	

	
	/**
	 * a dandy of a function that parses stuff and otherwise creates the DOT-notation CFC paths
	 * @param resource the resource whose cfc notation will be derived
	 * @return the cfc notation
	 */
	private String deriveCFCPath(IResource resource){
		String path = "";
		String propValue = "";
		
		//for each cfc found, loop over all parents up to the project;
		//if any parents have a property configured for cfc path
		//then attach that root to the component and return;
		
		IResource currentParent = resource;
		IPath p = resource.getLocation().removeFileExtension();
		//the 'device' (c:) is not part of the segment count. we gotta knock it off for the stuff below to work.
		p = p.setDevice(null);
		
		//this loop is broken up so much b/c I want descriptive logging
		for(int i = p.segmentCount()-1; i > 0; i--){
			currentParent = currentParent.getParent();
			propValue = props.getComponentPropertyValue(currentParent).trim();
			path = segmentsToCFCPath(p, i);
			
			if(currentParent.getType() == IResource.ROOT){
				MXUnitPluginLog.logInfo("No cfc property found up to project root. Using default derived path ["+path+"]" );
				break;
			}
			
			if(Arrays.binarySearch(emptyPathIndicators, propValue) >= 0){
				MXUnitPluginLog.logInfo("'Empty Path Indicator' property found on resource "+currentParent+". Derived path is ["+path+"]" );
				break;
			}
			
			if(propValue.length()>0) {
				path = propValue + "." + path;
				MXUnitPluginLog.logInfo("Using resource " + currentParent + " as source for componentroot property [value="+propValue+"]; path is determined to be " + path);
				break;	
			}
			
		}
	
		return path;
	}


	/**
	 * @param path
	 * @param segmentsToRemove
	 * @return
	 */
	private String segmentsToCFCPath(IPath path, int segmentsToRemove) {
		return path.removeFirstSegments(segmentsToRemove).toString().replaceAll("/", ".");
	}
	
	
	class TestVisitor implements IResourceVisitor{
		public boolean visit(IResource resource) throws CoreException {
			if(resource.getType() == IResource.FILE && 
					resource.getFullPath().lastSegment().matches(testFilter)){				
				String path = deriveCFCPath(resource);
				addTestToSuite(suite, path, resource);
			}
			return true;
		}
	}
}
