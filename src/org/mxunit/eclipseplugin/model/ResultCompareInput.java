package org.mxunit.eclipseplugin.model;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.runtime.IProgressMonitor;

public class ResultCompareInput extends CompareEditorInput {

	ResultCompareItem expected, actual;

	public ResultCompareInput(ResultCompareItem expected,
			ResultCompareItem actual) {
		super(new CompareConfiguration());
		this.expected = expected;
		this.actual = actual;
	}

	@Override
	protected Object prepareInput(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {

		getCompareConfiguration().setLeftLabel("Expected result");
		getCompareConfiguration().setRightLabel("Actual result");
		DiffNode node = new DiffNode(expected, actual);
		
		node.setKind(Differencer.CHANGE);
		return node;

	}

}
