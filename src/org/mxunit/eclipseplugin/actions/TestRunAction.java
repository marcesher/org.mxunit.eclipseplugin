package org.mxunit.eclipseplugin.actions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.eclipse.swt.widgets.TreeItem;
import org.mxunit.eclipseplugin.MXUnitPlugin;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.treeactions.FilterFailuresAction;
import org.mxunit.eclipseplugin.actions.util.TreeHelper;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestHistory;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestStatus;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.views.MXUnitView;


public class TestRunAction extends BaseRemoteAction {

    private TreeItem[] allTreeItems;   
    private ITest[] runnableMethods;
    private ITest itemForURL;
    private Map<ITest, TreeItem> testItemsToTreeItemsMap;    
    private List<TreeItem> updatedSelectedTreeItemsList; 
    

    public TestRunAction(MXUnitView view) {
        this.view = view;
        MXUnitPlugin.getDefault().getPluginPreferences();
        treeHelper = new TreeHelper(view.getTestsViewer());
    }

    public void run() {     
        final long viewRunID = System.currentTimeMillis();
        view.setRunID(viewRunID);
        
     	//our "model"
        runnableMethods = treeHelper.getRunnableMethods();
        
        if( runnableMethods.length == 0 ){
        	view.writeToConsole("No runnable methods found");
        	return;
        }
        
        itemForURL = runnableMethods[0];
		facadeURL = callCreator.getFacadeURL(itemForURL);
        boolean facadeOK = notifyOnEmptyFacade();
		
		if(!facadeOK) return;
	
		facade = callCreator.createFacade(itemForURL);
	
		view.disableActions();
    	TestSuite currentSuite = (TestSuite) view.getTestsViewer().getInput();
    	currentSuite.setStartTime(System.currentTimeMillis());
    	TestHistory history = view.getTestHistory();
    	history.addSuite(currentSuite);
    	//first, get an array of all tree items so we can easily set the tree's 
        //selected elements
        allTreeItems = treeHelper.getAllTreeItems();
        
        //set up a map and list that will eventually be used for setting the input of the tree
        testItemsToTreeItemsMap = new HashMap<ITest, TreeItem>();
        updatedSelectedTreeItemsList = new ArrayList<TreeItem>();
                    
        //deselect all so that we can update the selection as the tests complete
        view.getTestsViewer().getTree().deselectAll();  
        view.clearDetailsPanel();
       
        //create the map of testitems to treeitems
        for (int i = 0; i < allTreeItems.length; i++) {
            testItemsToTreeItemsMap.put((ITest) allTreeItems[i].getData(), allTreeItems[i]);
        } 
       
        final Thread t = new Thread() {
            public void run(){     
                runTests(viewRunID);
            }
        };
        t.start();
    
    }


    /**
     * runs the runnable test methods
     */
    private void runTests(long viewRunID) {    
    	
    	resetProgressBar();
    	String testRunKey = startTestRun();
        for (int i = 0; i < runnableMethods.length; i++) {
            if(view.getRunID()!=viewRunID){
                view.writeToConsole(".... Stopped.");
                break;
            }
            //currentMethod = "";
            ITest testItem = runnableMethods[i];
            System.out.println("item is " + testItem.getName());  
           
            testItem.clearStatus();  
            //do the deed
            runTestMethod(testItem, viewRunID, testRunKey);            
        }
        
        endTestRun(testRunKey);
        ((TestSuite) view.getTestsViewer().getInput()).setEndTime(System.currentTimeMillis());
        FilterFailuresAction filter = new FilterFailuresAction(view);
        filter.run();
        view.enableActions();
        view.updateDetailsPanel();
    }
    
    /**
     * resets the progress bar to 'empty' color and sets the number of elements that will be run
     */
    private void resetProgressBar(){
    	view.resetCounts(runnableMethods.length);    	
    }
    
    
    /**
     * runs a test method
     * @param testItem the testmethod to run
     */
    @SuppressWarnings("unchecked")
	private void runTestMethod(final ITest testItem, long viewRunID, String testRunKey) {
        final TestMethod tm = (TestMethod) testItem;
        final String currentComponent = tm.getParent().getName();
        final String currentMethod = tm.getName();
        view.writeToConsole("Running method " + currentMethod + "...");        
        
        Map tmpresults;
        
        try {            
        	tm.setStartTime(System.currentTimeMillis());
        	tmpresults = (Map) facade.executeTestCase(currentComponent, currentMethod, testRunKey);
            
            Map results = new CaseInsensitiveMap(tmpresults);
            //printResults(results);
            Map parent = (Map) results.get(currentComponent);
            Map keys = (Map) parent.get(currentMethod);
            if(keys.get("EXCEPTION")!=null){
                tm.setException( (String) keys.get("EXCEPTION") + ": " + (String) keys.get("MESSAGE"));
            }
            if(keys.get("TAGCONTEXT")!=null){       
                Object[] tagContextArray = (Object[]) keys.get("TAGCONTEXT");
                Map[] tagContextMap = new HashMap[tagContextArray.length];
                for (int i = 0; i < tagContextArray.length; i++) {
                    Map trace = (Map) tagContextArray[i];
                    formatTagContextMap(trace);
                    tagContextMap[i] = trace;
                }
                tm.setTagcontext(tagContextMap);
            }
            tm.setResult((String) keys.get("MESSAGE"));
            tm.setOutput((String) keys.get("OUTPUT"));      
            tm.setActual((String) keys.get("ACTUAL"));
            tm.setExpected((String) keys.get("EXPECTED"));
            tm.setStatusFromString((String) keys.get("RESULT"));
            tm.setTotalServerTime( ((Number) keys.get("TIME")).longValue());
            
        } catch (RemoteException e) {
        	String message = e.toString();
        	if(message.indexOf("SocketTimeoutException") > 0){
        		message += "; Is the timeout preference too low? Are you dumping/calling debug() on large data or cfc instances?  ";
        	}
            tm.setStatus(TestStatus.ERROR);
            tm.setResult(message);
            tm.setException(message);
            view.writeToConsole("RemoteException: " + message);
            MXUnitPluginLog.logError("RemoteException in RunTestsAction",e);
        }finally{
        	tm.setEndTime(System.currentTimeMillis());
        }
        view.writeToConsole("   finished.\n");
        
        updatedSelectedTreeItemsList.add( testItemsToTreeItemsMap.get(testItem) );
        
        
        //repaint the tree and the details panel
        if(view.getRunID()==viewRunID){
            view.getTestsViewer().getTree().getDisplay().asyncExec(new Runnable() {
                public void run() {
                	//update the tree item to reflect the changes we made to the model
                    view.getTestsViewer().update(testItem, null);
                    view.getTestsViewer().update(testItem.getParent(),null);                
                    //this has the effect of selecting all the currently run methods. This was the only way
                    //i could figure out to achieve the type of selecting that i wanted. you cannot do
                    //an additive selection on a tree. you either select one or select an array...you can't have 
                    //some tree elements selected, say tree.select(item), and have that item selected in addition
                    //to the already selected items.
                    TreeItem[] tmp = updatedSelectedTreeItemsList.toArray( new TreeItem[0] );  
                    view.getTestsViewer().getTree().setSelection( tmp );  
                    view.addTestResult(tm.getStatus());
                }
            });
        }
    }
    
    private String startTestRun(){
    	String key = "";
    	try {
    		key = facade.startTestRun();
		} catch (RemoteException e) {
			MXUnitPluginLog.logError("RemoteException in RunTestsAction startTestRun",e);
		}
		System.out.println("key fetched is " + key);
		return key;
    }
    
    private void endTestRun(String testRunKey){
    	System.out.println("Invoking endTestRun with key " + testRunKey);
    	try {
    		facade.endTestRun(testRunKey);
		} catch (RemoteException e) {
			MXUnitPluginLog.logError("RemoteException in RunTestsAction endTestRun",e);
		}
    }

    private void printResults(Map results){
        System.out.println(results);
    }
    
    /**
     * hook for consistently formatting certain values. BlueDragon passes the LINE as a Double, while CF passes as an Integer. We don't want our model  
     * @param tagContextItem
     */
    private void formatTagContextMap(Map tagContextItem){
    	Number num = (Number)tagContextItem.get("LINE");
        tagContextItem.put("LINE", num.intValue());      
    }

}
