package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class FilterFailuresAction extends Action {
	
	private MXUnitView view;
	
	public FilterFailuresAction(MXUnitView view){
		this.view = view;
		
	}
	
	public void run(){
		System.out.println("inside FiltureFailuresAction run: checked = " +  isChecked());
		
	}

}
