package org.mxunit.eclipseplugin.actions.listeners;

import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class LoadTestsExecutionListener extends AbstractTestExecutionListener {

	public LoadTestsExecutionListener(MXUnitView view){
		this.view = view;
	}
	public void onUpdateStart(final ITest item){
		System.out.println("Starting " + item);
	}
	public void onUpdateFinish(final ITest item) {
		System.out.println("Finishing " + item);
		asyncExec(
				new Runnable() {
			        public void run() {
			        	view.getTestsViewer().refresh(item);		                	
			        	view.getTestsViewer().update(item.getParent(),null);  
			        }
			    }		
		);
	}
	public void onError(final ITest item, final Exception e){
		super.onError(item, "ERROR getting test methods for Component: ", e);
	}

}
