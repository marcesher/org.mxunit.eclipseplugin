package org.mxunit.eclipseplugin.sandbox;

import junit.framework.TestCase;

public class RegexTest extends TestCase {

	protected void setUp() throws Exception {
		
	}
	
	public void testReplaceCaps(){
		System.out.println("oneTwoThree".replaceAll("([A-Z])", " $1"));
		assertEquals("one Two Three", "oneTwoThree".replaceAll("([A-Z])", " $1") );
	}

}
