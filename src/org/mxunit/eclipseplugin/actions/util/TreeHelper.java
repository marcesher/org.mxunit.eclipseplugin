package org.mxunit.eclipseplugin.actions.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestCaseComparator;
import org.mxunit.eclipseplugin.model.TestElementType;
import org.mxunit.eclipseplugin.model.TestStatus;

public class TreeHelper {
	
	TreeViewer treeViewer;
	
	public TreeHelper(TreeViewer treeViewer){
		this.treeViewer = treeViewer;
	}
	
	
	/**
     * I assume I'm doing this because I can't figure out the API for the life of me;
     * why is it so hard to simply get an array of all tree items regardless of selection?
     */
	public TreeItem[] getAllTreeItems(){
		List<TreeItem> all = new ArrayList<TreeItem>();
        TreeItem[] parents = treeViewer.getTree().getItems();
        for (int i = 0; i < parents.length; i++) {
            all.add(parents[i]);
            TreeItem[] children = parents[i].getItems();
            for (int j = 0; j < children.length; j++) {
                all.add(children[j]);
            }
        }
        return all.toArray(new TreeItem[0]);
	}
	
	/**
	 * gets an array of distinct selected TestCases in the tree. thus, if a test case has multiple methods selected, it'll return
	 * just the parent
	 * @return an array of ITest
	 */
	public ITest[] getSelectedTestCases(){
		ArrayList<ITest> tmpList = new ArrayList<ITest>();
		TreeItem[] current = treeViewer.getTree().getSelection();
		//add all selected test cases
		for(int i=0; i<current.length; i++){	
			ITest test = (ITest)current[i].getData();
			if(test.getTestElementType() == TestElementType.TESTCASE){
				if(!tmpList.contains(test)){
					tmpList.add(test);
				}
			}else if(test.getTestElementType() == TestElementType.TESTMETHOD){
				ITest parent = test.getParent();
				if(!tmpList.contains(parent)){
					tmpList.add(parent);
				}
			}
		}
		Collections.sort(tmpList,new TestCaseComparator());
		return tmpList.toArray(new ITest[0]);
	}
	
	/**
	 * Creates an array of ITest items that correspond to all the methods that are to be run based
	 * on the tree's current selections
	 * @return array of ITest 
	 */
	public ITest[] getRunnableMethods(){
		ArrayList<ITest> tmpList = new ArrayList<ITest>();
		TreeItem[] current = treeViewer.getTree().getSelection();
		
		//add all selected test cases
		for(int i=0; i<current.length; i++){			
			ITest test = (ITest)current[i].getData();
			if(test.getTestElementType() == TestElementType.TESTCASE){				
				TestCase tc = (TestCase)test;
				if(tc.getMethods().size()>0){
					tmpList.add(test);	
				}
			}
		}
		//yes, that's right. same loop.
		//add all selected methods. if any of these methods had a selected
		//test case, remove that testcase. my rule is that if you select
		//both a test case and one of its methods, then the only thing
		//that gets run is the selected methods...not all other methods that
		//belong to the testcase
		for(int i=0; i<current.length; i++){
			ITest test = (ITest)current[i].getData();
			if(test.getTestElementType() == TestElementType.TESTMETHOD){
				tmpList.add((ITest)current[i].getData());		
				ITest parent = (ITest)current[i].getParentItem().getData();
				if( tmpList.contains(parent) ){
					tmpList.remove(parent);
				}
			}
		}
		tmpList.trimToSize();
		ArrayList<ITest> finalList = new ArrayList<ITest>(tmpList);
		//for any testcases that remain, add that testcase's methods, then remove the testcase
		for(ITest test : tmpList){
			if(test.getTestElementType() == TestElementType.TESTCASE){				
				finalList.remove(test);
				TestCase tc = (TestCase)test;
				if(tc.getMethods().size()>0){
					finalList.addAll(tc.getMethods());	
				}		
			}
		}		
		Collections.sort(finalList,new TestCaseComparator());
		return finalList.toArray(new ITest[0]);
	}
	
	public TreeItem[] getFailureItems(){
		TreeItem[] all = getAllTreeItems();
		ArrayList<TreeItem> failures = new ArrayList<TreeItem>();
		for (int i = 0; i < all.length; i++) {
			ITest testItem = (ITest)all[i].getData();
			if(testItem.getTestElementType() == TestElementType.TESTMETHOD 
					&& (testItem.getStatus() == TestStatus.FAIL || testItem.getStatus() == TestStatus.ERROR)){
				failures.add(all[i]);
			}
		}
		
		failures.trimToSize();
		return failures.toArray(new TreeItem[0]);
	}

}
