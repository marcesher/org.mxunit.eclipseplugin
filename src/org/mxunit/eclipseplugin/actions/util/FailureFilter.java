package org.mxunit.eclipseplugin.actions.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestStatus;

/**
 * Filters out anything that passes. This way, our tree shows us failures, errors, and "blanks".
 * @author mesher
 *
 */
public class FailureFilter extends ViewerFilter {
	
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		ITest test = (ITest)element;
		
		//ensure that if there are no failures in the tree, we show everything;
		//i.e. the filter is ignored for a 100% successful tree. We do this
		//because we don't want to have an empty tree.
		if(test.getSuite().getStatus() == TestStatus.PASS){
			return true;
		}
		
		if( test.getStatus() == TestStatus.PASS  ){
			return false;
		}else{
			return true;
		}
	}
}
