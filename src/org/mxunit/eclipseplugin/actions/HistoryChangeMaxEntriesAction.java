package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;
import org.mxunit.eclipseplugin.views.MXUnitView;



public class HistoryChangeMaxEntriesAction extends Action {
	
	MXUnitView view;
	
	public HistoryChangeMaxEntriesAction(MXUnitView view){
		this.view = view;
	}
	
	public void run(){
		//set the history preference
		MXUnitPlugin.getDefault().getPluginPreferences().setValue(MXUnitPreferenceConstants.P_MAX_HISTORY, 
				100);
		
		//reset the setting inside the view's history object
		view.getTestHistory().setMaxEntries(MXUnitPlugin.getDefault().getPluginPreferences().getInt(MXUnitPreferenceConstants.P_MAX_HISTORY));
	}

}
