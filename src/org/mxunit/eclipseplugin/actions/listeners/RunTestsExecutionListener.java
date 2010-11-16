package org.mxunit.eclipseplugin.actions.listeners;

import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class RunTestsExecutionListener extends AbstractTestExecutionListener {

	public RunTestsExecutionListener(MXUnitView view){
		this.view = view;
	}
	public void onUpdateStart(final ITest item){
		System.out.println("Starting " + item);
	}
	public void onUpdateFinish(final ITest item) {
		System.out.println("Finishing " + item);
	}
	public void onError(final ITest item, final Exception e){
		super.onError(item, "ERROR running test: ", e);
	}

}
