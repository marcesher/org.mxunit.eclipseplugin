package org.mxunit.eclipseplugin.actions.delegates;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * Opens the MXUnit view via a toolbar button
 * @author Marc Esher
 *
 */
public final class OpenMXUnitViewActionDelegate implements
		IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		if(window == null){
			return;
		}
		IWorkbenchPage page = window.getActivePage();
		if(page == null){
			return;
		}
		
		//open that sucker
		try {
			page.showView(MXUnitView.ID);
		} catch (PartInitException e) {
			MXUnitPluginLog.logError("PartInitException in OpenMXUnitViewActionDelegate",e);
		}
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
