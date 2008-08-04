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
		if(test.getStatus() == TestStatus.PASS  ){
			return false;
		}else{
			return true;
		}
	}
}
