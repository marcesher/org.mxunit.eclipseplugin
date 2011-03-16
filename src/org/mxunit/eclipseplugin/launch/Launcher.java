package org.mxunit.eclipseplugin.launch;

import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.mxunit.eclipseplugin.actions.delegates.RunActiveEditorInputActionDelegate;
import org.mxunit.eclipseplugin.actions.delegates.RunSelectedResourceActionDelegate;

public class Launcher implements ILaunchShortcut {

	public void launch(ISelection selection, String mode) {
		RunSelectedResourceActionDelegate runTest = new RunSelectedResourceActionDelegate();
		runTest.selectionChanged(null, selection);
		runTest.run(null);
	}

	public void launch(IEditorPart editor, String mode) {
		RunActiveEditorInputActionDelegate runTest = new RunActiveEditorInputActionDelegate();
		runTest.setActiveEditor(null, editor);
		runTest.run(null);
	}
	
	
}