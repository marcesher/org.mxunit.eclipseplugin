package org.mxunit.eclipseplugin.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestSuite;

public class TestListContentProvider implements IStructuredContentProvider, 
	ITreeContentProvider{
	
	public TestListContentProvider(){
		
	}
	

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		//System.out.println("inside inputChanged");
		
	}
	public void dispose() {
	}
	public Object[] getElements(Object parent) {	
		Object[] children = getChildren(parent);
		return children;
	}
	public Object getParent(Object child) {
		return ((ITest)child).getParent();
	}
	
	public Object [] getChildren(Object parent) {
		return ((ITest)parent).getChildren();
		
	}
	
	public boolean hasChildren(Object parent) {
		return ((ITest)parent).hasChildren();
	}
}



