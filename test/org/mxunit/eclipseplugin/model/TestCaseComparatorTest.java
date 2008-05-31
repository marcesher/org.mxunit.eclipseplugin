package org.mxunit.eclipseplugin.model;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class TestCaseComparatorTest extends TestCase {

	/**
	 * brute force, no-finesse test
	 *
	 */
	public void testCompare() {
		TestCaseComparator tcc = new TestCaseComparator();
		org.mxunit.eclipseplugin.model.TestSuite ts1 = new org.mxunit.eclipseplugin.model.TestSuite();
		
		List<org.mxunit.eclipseplugin.model.TestCase> cases = ts1.example();
		//
		ITest[] methods1 = cases.get(0).getMethodsAsArray();
		ITest[] methods2 = cases.get(1).getMethodsAsArray();
		ITest[] methods3 = cases.get(2).getMethodsAsArray();
		ITest[] methods4 = cases.get(3).getMethodsAsArray();
		//create a big old mixed up array
		ITest[] tests = new ITest[8];
		tests[0] = methods4[3];
		tests[1] = methods3[2];
		tests[2] = methods2[1];
		tests[3] = methods1[0];
		tests[4] = methods4[0];
		tests[5] = methods3[1];
		tests[6] = methods2[2];
		tests[7] = methods1[3];
		Arrays.sort(tests,tcc);
		
		assertEquals(tests[0],methods1[0]);
		assertEquals(tests[1],methods1[3]);
		assertEquals(tests[2],methods2[1]);
		assertEquals(tests[3],methods2[2]);
		assertEquals(tests[4],methods3[1]);
		assertEquals(tests[5],methods3[2]);
		assertEquals(tests[6],methods4[0]);
		assertEquals(tests[7],methods4[3]);
		
		//now make sure the transition from testmethod to test case works OK
		tests = new ITest[4];
		tests[0] = methods4[3];
		tests[1] = methods3[2];
		tests[2] = cases.get(1);
		tests[3] = cases.get(0);
		
		Arrays.sort(tests,tcc);
		
		assertEquals(tests[0],cases.get(0));
		assertEquals(tests[1],cases.get(1));
		assertEquals(tests[2],methods3[2]);
		assertEquals(tests[3],methods4[3]);
		
	}
	
	

}
