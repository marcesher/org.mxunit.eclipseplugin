package org.mxunit.eclipseplugin.actions;

import org.eclipse.swt.widgets.TreeItem;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class RunFailuresOnlyAction extends TestRunAction {

	private MXUnitView view;
	public RunFailuresOnlyAction(MXUnitView view) {
		super(view);
		this.view = view;
	}
	
	public void run(){
		TreeItem[] failures = treeHelper.getFailureItems();
		if(failures.length>0){
			view.getTestsViewer().getTree().deselectAll();
			view.getTestsViewer().getTree().setSelection(failures);
		}		
		super.run();
	}

}
