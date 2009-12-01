package org.mxunit.eclipseplugin.actions;

import org.eclipse.compare.CompareUI;
import org.eclipse.jface.action.Action;

import org.mxunit.eclipseplugin.model.ResultCompareInput;
import org.mxunit.eclipseplugin.model.ResultCompareItem;
import org.mxunit.eclipseplugin.model.TestMethod;

public class ResultCompareAction extends Action {
	
	private TestMethod testMethod;	
	
	public void setTestMethod(TestMethod testMethod){
		this.testMethod = testMethod;
	}
	
	public void run(){
		ResultCompareItem expected = new ResultCompareItem("Expected",testMethod.getExpected());
        ResultCompareItem actual = new ResultCompareItem("Actual",testMethod.getActual());
        ResultCompareInput input = new ResultCompareInput(expected,actual);
        input.setTitle(testMethod.getParent().getName() + " -- " + testMethod.getName() + "()");
        CompareUI.openCompareDialog(input);
	}
}
