package org.mxunit.eclipseplugin.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * TestHistory maintains the history of test runs
 * @author mesher
 *
 */
public class TestHistory {

	private LinkedList<TestSuite> history;
	private int maxEntries = 30;//for now.... will make this a preference later
	private final int ABSOLUTE_MAX = 100;
	public TestHistory(){
		history = new LinkedList<TestSuite>();
	}
	
	public int setMaxEntries(int maxEntries){
		if(maxEntries < 0){
			maxEntries = 0;
		}
		if(maxEntries > ABSOLUTE_MAX){
			maxEntries = ABSOLUTE_MAX;
		}
		this.maxEntries = maxEntries;
		resize();
		return this.maxEntries;
	}
	
	public TestSuite getSuiteAt(int position){
		return history.get(position);
	}
	
	public LinkedList getHistory(){
		return history;
	}
	
	public void addSuite(TestSuite suite){
		history.addFirst(suite);
		resize();
	}
	
	public void removeAllSuites(){
		history.clear();
	}
	
	private void resize(){
		while(history.size() > maxEntries){
			history.removeLast();
		}
	}
	
	
	
}
