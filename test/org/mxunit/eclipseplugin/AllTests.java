package org.mxunit.eclipseplugin;

import org.mxunit.eclipseplugin.model.TestCaseComparatorTest;
import org.mxunit.eclipseplugin.model.TestCaseTest;
import org.mxunit.eclipseplugin.model.TestSuiteTest;
import org.mxunit.eclipseplugin.sandbox.StructReturnInvokerTest;
import org.mxunit.eclipseplugin.utils.PathUtilsTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.marc.sandbox");
		//$JUnit-BEGIN$
		suite.addTestSuite(PathUtilsTest.class);
		//suite.addTestSuite(StructReturnInvokerTest.class);
		suite.addTestSuite(TestCaseTest.class);
		suite.addTestSuite(TestSuiteTest.class);
		suite.addTestSuite(TestCaseComparatorTest.class);
		//$JUnit-END$
		return suite;
	}

}
