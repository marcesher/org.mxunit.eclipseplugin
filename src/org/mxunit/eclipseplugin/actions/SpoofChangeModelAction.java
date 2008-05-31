package org.mxunit.eclipseplugin.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestElementType;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestStatus;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class SpoofChangeModelAction extends Action {
	
	Tree tree;
	
	public SpoofChangeModelAction(Tree tree){
		this.tree = tree;
	}
	
	public void run(){
		TestSuite suite = (TestSuite)tree.getData();
		System.out.println(suite);
		List<TestCase> tests = suite.getTests();
		int testcount = tests.size();
		for(int i=0; i<testcount; i++){
			TestCase tc = tests.get(i);
			List<TestMethod> methods = tc.getMethods();
			int methodcount = methods.size();
			for (int j = 0; j < methodcount; j++) {
				TestMethod method = methods.get(j);
				method.setStatus(TestStatus.FAIL);
				//System.out.println(suite.hasChanged());
				suite.notifyObservers(method );				
			}
		}
		tree.setData(suite);
	}
	

}
