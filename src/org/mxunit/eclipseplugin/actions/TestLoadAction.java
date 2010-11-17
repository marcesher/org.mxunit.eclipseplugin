package org.mxunit.eclipseplugin.actions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.TreeItem;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;
import org.mxunit.eclipseplugin.actions.listeners.LoadTestsExecutionListener;
import org.mxunit.eclipseplugin.actions.util.RemoteCallCreator;
import org.mxunit.eclipseplugin.actions.util.TreeHelper;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestCase;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * loads all functions for given test cases into the tree
 * @author marc esher
 *
 */
public final class TestLoadAction extends BaseRemoteAction {


	private TreeItem[] items;
	private ITest[] selectedTests;
	private ITest itemForURL;
	final private boolean runTests;
	public  boolean isFinished = false;
	
	
	public TestLoadAction(MXUnitView view, boolean runAfterLoading) {
		this.view = view;
		this.runTests = runAfterLoading;
		treeHelper = new TreeHelper(view.getTestsViewer());
	}
	

	public void run() {
	    final long runID = System.currentTimeMillis();
	    //set state in our view
	    view.setRunID(runID);
		view.disableActions();
		view.resetCounts(1);
		
		//establish data we need for loading tests
		items = view.getTestsViewer().getTree().getSelection();
		if (items.length == 0) {
			view.getTestsViewer().getTree().selectAll();
			items = view.getTestsViewer().getTree().getSelection();
		}
		itemForURL = (ITest) items[0].getData();
		facadeURL = callCreator.getFacadeURL(itemForURL);
		boolean facadeOK = notifyOnEmptyFacade();
		
		if(!facadeOK) return;
		
		//now get on with it
		view.clearDetailsPanel();
		selectedTests = treeHelper.getSelectedTestCases();
		final Job loadMethodsJob = new Job("Loading Tests"){
			protected IStatus run(IProgressMonitor monitor){
				monitor.beginTask("Loading methods", 100);
				monitor.worked(5);
				setName("Initializing webservice");
				facade = callCreator.createFacade(itemForURL);
				monitor.worked(20);
				
				setName("Establishing connection to server");
				boolean pingSuccessful = verifyOKToRun();
				monitor.worked(25);
				if (pingSuccessful) {
					setName("Loading tests");
					
					loadTests(runID);
					
					monitor.worked(50);
					monitor.done();
					return new Status(IStatus.OK,MXUnitView.ID,IStatus.OK,"Tests Loaded",null);
					
				}else{
					view.resetCounts(0);
					view.writeToConsole("Ping was not successful. Are you sure your server is running?");
					return new Status(IStatus.WARNING,MXUnitView.ID,IStatus.WARNING,"No Test cases present",null);
				}
			
			}
		};
		loadMethodsJob.addJobChangeListener(new JobChangeAdapter(){
			public void done(IJobChangeEvent event){
				view.getSite().getShell().getDisplay().syncExec(new Runnable() {
					public void run() {
						view.enableActions();
						view.getTestsViewer().expandAll();
						
						if( runTests ){
							TestRunAction runAction = new TestRunAction(view);
							runAction.run();
						}
					}
				});
				
			}
		});
		loadMethodsJob.schedule();
	}

	/**
	 * lamest f'n method i've ever written. i hate looking at it it's so bad.
	 * @param runID
	 */
	private void loadTests(long runID) {
		if(view.getRunID()!=runID){
			System.out.println("View runID: " + view.getRunID() + "; expectedID: " + runID);
			System.out.println("Bad runID. Returning...");
	        return;
	    }
		TestExecutor executor = new TestExecutor(facade);
		executor.addListener(new LoadTestsExecutionListener(view));
		executor.loadTests(selectedTests);
		view.resetCounts(selectedTests.length);
	}
}
