package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;
import org.mxunit.eclipseplugin.actions.util.RemoteCallCreator;
import org.mxunit.eclipseplugin.actions.util.TreeHelper;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * A Base class for common functionality related to communication between the plugin and CF
 * @author marc
 *
 */
public abstract class BaseRemoteAction extends Action {
	protected MXUnitView view = null;
	protected final RemoteCallCreator callCreator = new RemoteCallCreator();
	protected RemoteFacade facade = null;
	protected String facadeURL = null;
	protected TreeHelper treeHelper = null;


	/**
	 * checks that: --the facade URL has been defined at either the preferences
	 * or project properties level --the facade URL is accessible
	 * 
	 * @return true if it's OK to run the methods
	 */

	protected boolean verifyOKToRun() {
		// make sure we can connect to the remote url
		boolean pingResult = callCreator.runPing();
		if (!pingResult) {
			throw new RuntimeException("Could not connect to facade URL. \n\nTry running this in a browser: "
							+ callCreator.getFacadeURL() + "&method=ping");
		}
		return true;
	}

	protected boolean notifyOnEmptyFacade() {
		if (facadeURL == null || facadeURL.length() == 0) {
			MessageDialog
					.openInformation(
							null,
							"MXUnit Remote Facade not defined",
							"You must define a remote facade URL either in the project's properties or in Window -- Preferences -- MXUnit");
			return false;
		}
		return true;
	}

}
