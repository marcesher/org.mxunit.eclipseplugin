package org.mxunit.eclipseplugin.model;

import junit.framework.TestCase;

public class TestCaseTest extends TestCase {

	public void testAddMethod() {
		org.mxunit.eclipseplugin.model.TestCase tc = new org.mxunit.eclipseplugin.model.TestCase();
		tc.setName("testcase");
		TestMethod method1 = new TestMethod();
		TestMethod method2 = new TestMethod();
		TestMethod method3 = new TestMethod();
		TestMethod method4	= new TestMethod();
		method1.setName("method1");		
		method2.setName("method2");
		method3.setName("method1");
		method4.setName("method4");
		tc.addMethod(method1);
		tc.addMethod(method2);
		assertEquals("should be 2 methods",2,tc.getMethods().size());
		tc.addMethod(method3);
		assertEquals("should still only be 2 methods after adding a method " +
				"with a name that already exists in a previously " +
				"added method",2,tc.getMethods().size());
		tc.addMethod(method4);
		assertEquals("should now have 3 methods",3,tc.getMethods().size());
	}
	
	public void testUpdateStatus(){
		org.mxunit.eclipseplugin.model.TestCase tc = new org.mxunit.eclipseplugin.model.TestCase();
		org.mxunit.eclipseplugin.model.TestSuite ts = new org.mxunit.eclipseplugin.model.TestSuite();
		ts.setName("testsuite");
		tc.setName("testcase");
		tc.setParent(ts);
		
		TestMethod method1 = new TestMethod();
		TestMethod method2 = new TestMethod();
		TestMethod method3 = new TestMethod();
		TestMethod method4	= new TestMethod();
		method1.setName("method1");		
		method2.setName("method2");
		method3.setName("method3");
		method4.setName("method4");
		tc.addMethod(method1);
		tc.addMethod(method2);		
		tc.addMethod(method3);		
		tc.addMethod(method4);
		assertEquals("tc should still be blank",TestStatus.BLANK,tc.getStatus());
		assertEquals("ts should still be blank",TestStatus.BLANK,ts.getStatus());
		
		method1.setStatus(TestStatus.PASS);
		assertEquals("tc should now be passed",TestStatus.PASS,tc.getStatus());
		assertEquals("ts should now be passed",TestStatus.PASS,ts.getStatus());
		
		method2.setStatus(TestStatus.FAIL);		
		assertEquals("tc should now be failed",TestStatus.FAIL,tc.getStatus());
		assertEquals("ts should now be failed",TestStatus.FAIL,ts.getStatus());
		
		method3.setStatus(TestStatus.ERROR);
		assertEquals("tc should now be error",TestStatus.ERROR,tc.getStatus());
		assertEquals("ts should now be error",TestStatus.ERROR,ts.getStatus());
		
		method4.setStatus(TestStatus.FAIL);
		assertEquals("tc should still be error",TestStatus.ERROR,tc.getStatus());
		assertEquals("ts should still be error",TestStatus.ERROR,ts.getStatus());
		
		method1.setStatus(TestStatus.PASS);
		method2.setStatus(TestStatus.PASS);
		method3.setStatus(TestStatus.PASS);
		method4.setStatus(TestStatus.PASS);
		assertEquals("tc should now be passed",TestStatus.PASS,tc.getStatus());
		assertEquals("ts should now be passed",TestStatus.PASS,ts.getStatus());
		
		tc.clearStatus();
		assertEquals("tc should now be blank",TestStatus.BLANK,tc.getStatus());
		ts.clearStatus();
		assertEquals("ts should now be blank",TestStatus.BLANK,ts.getStatus());
		
	}

}
