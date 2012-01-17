package org.mxunit.eclipseplugin.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestElementType;

public class TestListNameSorter extends ViewerSorter {
	
	boolean sortMethodsAlphabetically = true;
	
	public void setSortMethodsAlphabetically( boolean sortEm ){
		this.sortMethodsAlphabetically = sortEm;
	}
	
	/**
	 * For methods, we'll honor the sort returned from the server unless the "sort alphabetical" preference is true. For testcases, we'll sort them alphabetically regardless
	 */
	@Override
	public int compare( Viewer viewer, Object e1, Object e2 ){
		//System.out.println("COMPARE " + sortMethodsAlphabetically);
		if( ((ITest)e1).getTestElementType() == TestElementType.TESTMETHOD && !sortMethodsAlphabetically ){
			return 0;
		} else {
			return super.compare(viewer, e1, e2);
		}
		
	}

}
