package org.mxunit.eclipseplugin.actions.listeners;

import org.eclipse.jface.dialogs.MessageDialog;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.views.MXUnitView;

public abstract class AbstractTestExecutionListener implements ITestExecutionListener {

	MXUnitView view;
	
	public void setView(MXUnitView view){
		this.view = view;
	}
	
	public void onError(final ITest item, final String message, final Exception e){
		MXUnitPluginLog.logError(message,e);
		asyncExec(new Runnable() {
	        public void run() {
	        	MessageDialog.openError(null, "MXUnit",
								message + "\n\n"
	        					+ "Exception is: \n\n"
								+ e.getMessage() + "\n\n*Component*: "
								+ item.getName());
	        }
	    });
	}
	
	protected void asyncExec(Runnable runnable){
		view.getSite().getShell().getDisplay().asyncExec(runnable);
	}

}
