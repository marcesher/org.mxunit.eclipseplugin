package org.mxunit.eclipseplugin.actions;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import junit.framework.TestCase;

public class TestSuiteCreatorTest extends TestCase {

	IWorkspaceRoot root;
	TestSuiteCreator creator;
	
	protected void setUp() throws Exception {
		super.setUp();
		root = ResourcesPlugin.getWorkspace().getRoot();
		creator = new TestSuiteCreator();
	}

	public void testTestSuiteCreator() {
		TestSuiteCreator creator = new TestSuiteCreator();
	}
	

	public void testCreateSuite() {
		fail("Not yet implemented");
	}

	public void testGetProjectComponentRoot() {
		fail("Not yet implemented");
	}

	public void testAlertIfResourceNotConfigured() {
		fail("Not yet implemented");
	}

	public void testIsResourceConfigured() {
		fail("Not yet implemented");
	}

}
