package org.mxunit.eclipseplugin.actions.delegates;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.TestLoadAction;
import org.mxunit.eclipseplugin.actions.TestRunAction;
import org.mxunit.eclipseplugin.actions.TestSuiteCreator;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * runs the current page in the active editor as an MXUnit test
 * @author Marc Esher
 *
 */
public final class RunActiveEditorInputActionDelegate implements IEditorActionDelegate {

	private ITextEditor editor;

	private TestLoadAction loadAction;
	private TestRunAction runAction;

	public void run(IAction action) {
		
		try {
			FileEditorInput input = (FileEditorInput) editor.getEditorInput();
			IResource resource = ((FileEditorInput) input).getFile();

			TestSuiteCreator testSuiteCreator = new TestSuiteCreator();
			
			//ensure cfc cause i suck and can't figure out how the hell to get enablement to work in plugin.xml for this viewerContribution
			if(resource.getType() == IResource.FILE && !resource.getFileExtension().equalsIgnoreCase("cfc")){
				File selectedResourceAsFile = new File(resource.getRawLocation().toString());
				MessageDialog.openInformation( null, "Whooops....not a CFC", "Selected file ["+ selectedResourceAsFile.getAbsolutePath() +"] is not a CFC");
			
			//get on with it
			} else {
				MXUnitView view = (MXUnitView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MXUnitView.ID);

				TestSuite suite = testSuiteCreator.createSuite(new IResource[] { resource });
				view.getTestsViewer().setInput(suite);
				view.getTestsViewer().getTree().selectAll();
				view.enableActions();

				loadAction = view.getTestLoadAction();
				loadAction.setRunTests(true);
				loadAction.run();
				//runAction.run();
			}

		} catch (PartInitException e) {
			MXUnitPluginLog.logError("Exception in RunActiveEditorInputActionDelegate",e);
		}
	}

	/**
	 * sets the active editor. NOTE: if CFEclipse ever changes and its editors don't implement ITextEditor, I'll have to change this.
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		if (targetEditor instanceof ITextEditor) {
			editor = (ITextEditor) targetEditor;
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// nuthin'
	}

}
