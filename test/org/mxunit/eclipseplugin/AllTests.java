package org.mxunit.eclipseplugin;

import org.mxunit.eclipseplugin.model.TestCaseComparatorTest;
import org.mxunit.eclipseplugin.model.TestCaseTest;
import org.mxunit.eclipseplugin.model.TestHistoryTest;
import org.mxunit.eclipseplugin.model.TestSuiteTest;
import org.mxunit.eclipseplugin.sandbox.StructReturnInvokerTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.mxunit.eclipseplugin");
		//$JUnit-BEGIN$
		//suite.addTestSuite(StructReturnInvokerTest.class);
		suite.addTestSuite(TestCaseTest.class);
		suite.addTestSuite(TestSuiteTest.class);
		suite.addTestSuite(TestCaseComparatorTest.class);
		suite.addTestSuite(TestHistoryTest.class);
		
		//$JUnit-END$
		return suite;
	}

}
