package org.mxunit.eclipseplugin.actions;

import junit.framework.TestCase;

public class RawInvokerTest extends TestCase {
    RawInvoker invoker = new RawInvoker();
    public void setUp(){
        RawInvoker invoker = new RawInvoker();
    }
    
    public void testInitializeCall(){
        invoker.initializeCall();
    }
    
    public void testPing(){
        invoker.runPing();
        
    }

}
