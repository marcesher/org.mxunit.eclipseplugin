package org.mxunit.eclipseplugin.preferences;

import java.io.File;

import junit.framework.TestCase;

public class MXUnitPreferenceInitializerTest extends TestCase {

	public void testGetReasonableDefaultWebroot() {
		String root = MXUnitPreferenceInitializer.getReasonableDefaultWebroot('\\');
		System.out.println(root);
		assertNotNull(root);
		assertTrue(root.length()>1);
		assertTrue(root.indexOf(File.separatorChar)>=0);
	}
	
	public void testGetReasonableDefaultWebrootNonWindows() {
		char sep = '/';
		String root = MXUnitPreferenceInitializer.getReasonableDefaultWebroot(sep);
		System.out.println(root);
		
		if(File.separatorChar==sep){
			assertTrue(root.length()>1);
			assertTrue(root.indexOf(File.separatorChar)>=0);
		}else{
			assertNotNull(root);
			assertTrue(root.length()==0);
		}
		
	}

}
