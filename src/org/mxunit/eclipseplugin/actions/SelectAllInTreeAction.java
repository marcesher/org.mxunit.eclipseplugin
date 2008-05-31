package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.mxunit.eclipseplugin.views.MXUnitView;

public final class SelectAllInTreeAction extends Action {
	
	private MXUnitView view;
	
	public SelectAllInTreeAction(MXUnitView view){
		this.view = view;
	}
	
	public void run(){		
		view.getTestsViewer().getTree().deselectAll();
		view.getTestsViewer().getTree().setSelection(view.getTestsViewer().getTree().getItems());		
	}
	
	

}
