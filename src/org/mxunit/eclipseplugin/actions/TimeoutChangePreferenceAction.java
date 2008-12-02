package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;
import org.mxunit.eclipseplugin.views.MXUnitView;



public class TimeoutChangePreferenceAction extends Action {
	
	MXUnitView view;
	
	public TimeoutChangePreferenceAction(MXUnitView view){
		this.view = view;
	}
	
	public void run(){
		InputDialog dialog = new InputDialog(view.getSite().getShell(), 
				"Change Timeout Preference",
				"Please enter a number between 0 and 180",
				Integer.toString(MXUnitPlugin.getDefault().getPluginPreferences().getInt(MXUnitPreferenceConstants.P_REMOTE_CALL_TIMEOUT)),
				new Validator());
		if(dialog.open() == IDialogConstants.OK_ID){
			//set the history preference
			MXUnitPlugin.getDefault().getPluginPreferences().setValue(MXUnitPreferenceConstants.P_REMOTE_CALL_TIMEOUT, 
					dialog.getValue());			
		}
	}
	
	class Validator implements IInputValidator{
		public String isValid(String newText) {
			String message = "C'mon: [" + newText + "] is not a number between 0 and 180";
			try {
				int value = Integer.parseInt(newText);
				if(value < 0 || value > 180){
					return message;
				}			
			} catch (Exception e) {
				return message;
			}			
			return null;
		}		
	}
}
