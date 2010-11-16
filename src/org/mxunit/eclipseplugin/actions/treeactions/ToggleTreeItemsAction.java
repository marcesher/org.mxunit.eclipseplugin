package org.mxunit.eclipseplugin.actions.treeactions;

import org.eclipse.jface.action.Action;
import org.mxunit.eclipseplugin.views.MXUnitView;

public final class ToggleTreeItemsAction extends Action {
	private MXUnitView view;
	
	public ToggleTreeItemsAction(MXUnitView view){
		this.view = view;
	}
	
	public void run(){
		
		if(view.getTestsViewer().getVisibleExpandedElements().length==0){
			view.getTestsViewer().expandAll();
		}else{
			view.getTestsViewer().collapseAll();
		}
	}
}
