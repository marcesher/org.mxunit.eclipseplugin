package org.mxunit.eclipseplugin.actions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;
import org.mxunit.eclipseplugin.actions.listeners.ITestExecutionListener;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestMethodDataProviderElement;

/**
 * Responsible for loading and running tests for a given set of components
 * 
 * @author marc
 * 
 */
public class TestExecutor {
	private RemoteFacade facade;
	private List<ITestExecutionListener> listeners = new ArrayList<ITestExecutionListener>();

	public TestExecutor(RemoteFacade facade) {
		this.facade = facade;
	}
	
	public void addListener(ITestExecutionListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(ITestExecutionListener listener){
		listeners.remove(listener);
	}

	/**
	 * loads all the tests for the given array of test cases
	 * @param selectedTests
	 * @return
	 * @throws CFCInvocationException
	 * @throws RemoteException
	 */
	public ITest[] loadTests(ITest[] selectedTests) {

		for (int i = 0; i < selectedTests.length; i++) {
			ITest item = selectedTests[i];
			
			//TODO: notify caller of what we're about to do
			notifyOnUpdateStart(item);
			Object[] tmpresult;
			try {
				tmpresult = (Object[]) facade.getComponentMethods(item.getName());
				TestCase tc = (TestCase) item;
				tc.clearMethods();
				tc.clearStatus();
				for (int j = 0; j < tmpresult.length; j++) {
					TestMethod tm = new TestMethod();
					tm.setName((String) tmpresult[j]);
					
					/*TestMethodDataProviderElement[] elements = {new TestMethodDataProviderElement(),new TestMethodDataProviderElement()};
					tm.addDataProvider(elements);*/
					tc.addMethod(tm);
				}
			
			} catch (RemoteException e) {
				notifyOnError(item,e);
			}

			//TODO: notify caller so that the tree can be updated. must also update the parent
			notifyOnUpdateFinish(item);
		}
		return selectedTests;
	}

	private void notifyOnError(ITest item, Exception e) {
		for (ITestExecutionListener listener : listeners) {
			listener.onError(item, e);
		}
	}
	private void notifyOnUpdateStart(ITest item) {
		for (ITestExecutionListener listener : listeners) {
			listener.onUpdateStart(item);
		}
	}
	private void notifyOnUpdateFinish(ITest item) {
		for (ITestExecutionListener listener : listeners) {
			listener.onUpdateFinish(item);
		}
	}
}
