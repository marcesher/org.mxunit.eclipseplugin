package org.mxunit.eclipseplugin.model;

import java.util.List;

import junit.framework.TestCase;

public class TestHistoryTest extends TestCase {

	TestHistory history;
	
	public void setUp(){
		history = new TestHistory();
	}
	
	public void testSetMaxEntries() {
		int actual = history.setMaxEntries(1);
		assertEquals("",1,actual);
		actual = history.setMaxEntries(50);
		assertEquals("",50,actual);
		actual = history.setMaxEntries(-5000);
		assertEquals("",0,actual);
		actual = history.setMaxEntries(101);
		assertEquals("",100,actual);
	}

	public void testAddSuiteBasic() {
		List orig = history.getHistory();
		assertEquals("",0,orig.size());
		history.addSuite(new TestSuite());
		List current = history.getHistory();
		assertEquals("",1,current.size());
	}
	
	public void testAddSuiteDoesNotExceedMax(){
		history.setMaxEntries(10);
		for(int i = 0; i<20; i++){
			history.addSuite(new TestSuite());
		}
		List current = history.getHistory();
		assertEquals("",10,current.size());
	}

	public void testRemoveAllSuites() {
		testAddSuiteDoesNotExceedMax();
		history.removeAllSuites();
		List current = history.getHistory();
		assertEquals("",0,current.size());
	}
	
	

}
