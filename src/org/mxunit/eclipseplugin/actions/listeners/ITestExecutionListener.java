package org.mxunit.eclipseplugin.actions.listeners;

import org.mxunit.eclipseplugin.model.ITest;

public interface ITestExecutionListener {
	public void onUpdateStart(ITest item);
	public void onUpdateFinish(ITest item);
	public void onError(ITest item, String message, Exception e);
	public void onError(ITest item, Exception e);
}
