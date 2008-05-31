package org.mxunit.eclipseplugin.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
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
		//System.out.println("children.length is " + children.length);
		return children;
	}
	public Object getParent(Object child) {
		if(child instanceof TestMethod){			
			return ((TestMethod)child).getParent();
		}else if(child instanceof TestCase){
			return ((TestCase)child).getParent();
		}else{
			return null;
		}		
	}
	
	public Object [] getChildren(Object parent) {
		if(parent instanceof TestSuite){
			//System.out.println("it's a testsuite");
			return ((TestSuite)parent).getTestsAsArray();
		}else if(parent instanceof TestCase){
			//System.out.println("it's a testcase");
			return ((TestCase)parent).getMethodsAsArray();
		}else{
			//System.out.println("it's a nothing!");
			return new Object[0];
		}
	}
	
	public boolean hasChildren(Object parent) {
		if(parent instanceof TestSuite){
			return ((TestSuite)parent).hasChildren();
		}else if(parent instanceof TestCase){
			return ((TestCase)parent).hasChildren();
		}else{
			return false;
		}		
	}
}



