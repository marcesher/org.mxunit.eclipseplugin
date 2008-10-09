package org.mxunit.eclipseplugin.actions;

import org.eclipse.jface.action.Action;
import org.mxunit.eclipseplugin.actions.util.FailureFilter;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * Action to show just problem tests in the test viewer tree.
 * This is a non-destructive filter, i.e. tests are merely hidden, 
 * not removed from the tree entirely. 
 * @author marc esher
 */
public class FilterFailuresAction extends Action {
	
	private MXUnitView view;
	private FailureFilter failureFilter;
	
	public FilterFailuresAction(MXUnitView view){
		this.view = view;
		failureFilter = new FailureFilter();
	}
	
	public void run(){		
		if(!isChecked()){
			view.getTestsViewer().removeFilter(failureFilter);
		}else{
			view.getTestsViewer().addFilter(failureFilter);
		}
		
		view.getTestsViewer().getTree().getDisplay().syncExec(new Runnable() {
	        public void run() {
	        	view.getTestsViewer().refresh();	
	        	view.getTestsViewer().expandAll();
	        }
	    });
	}
}
