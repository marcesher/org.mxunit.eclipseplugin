package org.mxunit.eclipseplugin.sandbox;




import junit.framework.TestCase;

public class StructReturnInvokerTest extends TestCase {
    StructReturnInvoker invoker = new StructReturnInvoker();
   
    public void testInitializeCall() {
        invoker.initializeCall();
    }

    public void testGetResults() {
        invoker.getResults();
    }
    
    public void testGetSingleTestResult(){
        invoker.getSingleTestResult();
    }
    
    public void testGetResultsAsMapOfMaps(){
    	invoker.getResultsAsMapOfMaps();
    }
    
    public void testGetResultsAsMapOfMapOfMaps(){
    	invoker.getResultsAsMapOfMapofMaps();
    }
    
    public void testGetResultsWithTagContext(){
        invoker.getResultsWithTagContext();
    }

}
