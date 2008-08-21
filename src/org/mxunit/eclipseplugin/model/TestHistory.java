package org.mxunit.eclipseplugin.model;

import java.util.LinkedList;

import org.eclipse.jface.resource.ImageDescriptor;
import org.mxunit.eclipseplugin.views.ResourceManager;
import org.mxunit.eclipseplugin.views.TestListLabelProvider;

/**
 * TestHistory maintains the history of test runs
 * @author mesher
 *
 */
public class TestHistory {

	private LinkedList<TestSuite> history;
	private int maxEntries = 30;//for now.... will make this a preference later
	private final int ABSOLUTE_MAX = 100;
	
	private final TestListLabelProvider labelProvider;
	
	private TestSuite activeEntry;
	
	
	public TestHistory(){
		history = new LinkedList<TestSuite>();
		labelProvider = new TestListLabelProvider();
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
	
	public LinkedList<TestSuite> getHistory(){
		return history;
	}
	
	public void addSuite(TestSuite suite){
		if(history.indexOf(suite) == -1){
			history.addFirst(suite);
		}
		setActiveEntry(suite);
		resize();
	}
	
	public void setActiveEntry(TestSuite suite){
		activeEntry = suite;
	}
	
	public boolean isActiveEntry(TestSuite suite){
		return activeEntry.equals(suite);
	}
	
	public void removeAllSuites(){
		history.clear();
	}
	
	private void resize(){
		while(history.size() > maxEntries){
			history.removeLast();
		}
	}
	
	public ImageDescriptor getImageDescriptor(TestSuite suite){		
		String image = labelProvider.getImageString(suite);
		return ResourceManager.getImageDescriptor(image);
	}
	
}
