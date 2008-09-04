package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;
import org.mxunit.eclipseplugin.views.MXUnitView;



public class HistoryChangeMaxEntriesAction extends Action {
	
	MXUnitView view;
	
	public HistoryChangeMaxEntriesAction(MXUnitView view){
		super("Change Max Entries");
		this.view = view;
	}
	
	public void run(){
		InputDialog dialog = new InputDialog(view.getSite().getShell(), 
				"Change Max History Entries",
				"Please enter a number between 1 and 100",
				Integer.toString(MXUnitPlugin.getDefault().getPluginPreferences().getInt(MXUnitPreferenceConstants.P_MAX_HISTORY)),
				new Validator());
		if(dialog.open() == IDialogConstants.OK_ID){
			//set the history preference
			MXUnitPlugin.getDefault().getPluginPreferences().setValue(MXUnitPreferenceConstants.P_MAX_HISTORY, 
					dialog.getValue());
			
			//reset the setting inside the view's history object
			view.getTestHistory().setMaxEntries(MXUnitPlugin.getDefault().getPluginPreferences().getInt(MXUnitPreferenceConstants.P_MAX_HISTORY));
		}
	}
	
	class Validator implements IInputValidator{
		public String isValid(String newText) {
			String message = "Hello, McFly: [" + newText + "] is not a number between 1 and 100";
			try {
				int value = Integer.parseInt(newText);
				if(value < 0 || value > 100){
					return message;
				}			
			} catch (Exception e) {
				return message;
			}			
			return null;
		}		
	}
}
