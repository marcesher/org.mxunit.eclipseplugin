package org.mxunit.eclipseplugin.model;

import java.util.Comparator;

/**
 * For sorting an array of test elements (test cases, test methods) appropriately. We need this
 * because Tree.getSelection() returns the selected elements in an arbitrary order, not the 
 * visual order you see in the tree view
 * @author Marc Esher
 *
 */
public class TestCaseComparator implements Comparator<ITest> {

	public int compare(ITest t1, ITest t2) {
		
		String s1 = t1.getParent().getName().toLowerCase() + "." + t1.getName().toLowerCase();
		String s2 = t2.getParent().getName().toLowerCase() + "." + t2.getName().toLowerCase();
		
		return s1.compareTo(s2);
	}

}
