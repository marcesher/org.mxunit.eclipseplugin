package org.mxunit.eclipseplugin.model;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

public class TestSuiteTest extends TestCase {

	public void testExample() {
		TestSuite s = new TestSuite();
		List<org.mxunit.eclipseplugin.model.TestCase> tests = s.example();
		org.mxunit.eclipseplugin.model.TestCase[] testarray = 
			(org.mxunit.eclipseplugin.model.TestCase[])tests.toArray( new org.mxunit.eclipseplugin.model.TestCase[tests.size()] );
		
		for (int i = 0; i < testarray.length; i++) {
			System.out.println("\n" + testarray[i].getName());
			List methods = testarray[i].getMethods();
			
			for (Iterator iter = methods.iterator(); iter.hasNext();) {
				TestMethod element = (TestMethod) iter.next();
				System.out.println("-->" + element.getName());
			}
			
			assertTrue("",testarray[i].getMethods().size() > 1);
		}
		
		org.mxunit.eclipseplugin.model.TestCase[] exampleArray = s.exampleAsArray();
		assertEquals(tests.size(),exampleArray.length);
	}
	
	
	public void testGetTestsAsArray(){
		TestSuite s = new TestSuite();
		List<org.mxunit.eclipseplugin.model.TestCase> tests = s.example();
		for (Iterator iter = tests.iterator(); iter.hasNext();) {
			org.mxunit.eclipseplugin.model.TestCase element = (org.mxunit.eclipseplugin.model.TestCase) iter.next();
			s.addTest(element);
		}
		
		List<org.mxunit.eclipseplugin.model.TestCase> realTests = s.getTests();
		org.mxunit.eclipseplugin.model.TestCase[] testArray = s.getTestsAsArray();
		
		assertEquals(testArray.length,realTests.size());
		assertEquals(testArray.length,tests.size());
	}

}
