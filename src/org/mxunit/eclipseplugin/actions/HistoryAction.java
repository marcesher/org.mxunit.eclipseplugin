package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class HistoryAction extends Action {
	
	private MXUnitView view;
	
	public HistoryAction (MXUnitView view){		
		super("Test Run History...", IAction.AS_DROP_DOWN_MENU);
		this.view = view;
	}
	
	public void run(){
		System.out.println("inside test run history action");
	}
	

}
