/**
 * RemoteFacadeServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings;

import org.mxunit.eclipseplugin.model.RemoteServerType;

public class RemoteFacadeServiceTestCase extends junit.framework.TestCase {
	
	String ServiceURL = "http://localhost/mxunit/framework/RemoteFacade.cfc?wsdl";
	String BDServiceURL = "http://localhost:8501/mxunit/framework/RemoteFacade.cfc?wsdl";
	//String username = "marcnet\\marctest";
	//String password = "marctest";
	
	String username = "";
	String password = "";
	
	
	
	/*NOTE TO SELF: to watch the wire, 
	 * 
	 * 1)add port 1234 to the service url
	 * 2) in a command line, CD to the lib directory of this plugin.
	 * 3) at the command line, type: java -cp axis.jar org.apache.axis.utils.tcpmon
	 * 4) in tcpmon app, set listenport=1234, targethostname=localhost, and targetport = 80. click "add". 
	 * 5) run a test. the calls will show up in the tcpmon app
	 */
	
	
    public RemoteFacadeServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1RemoteFacadeCfcPing() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            boolean value = false;
            value = binding.ping();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test2RemoteFacadeCfcInitializeSuitePool() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            binding.initializeSuitePool();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test3RemoteFacadeCfcEndTestRun() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.lang.String value = null;
            value = binding.endTestRun(new java.lang.String());
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test4RemoteFacadeCfcPurgeSuitePool() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            double value = -3;
            value = binding.purgeSuitePool();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test5RemoteFacadeCfcExecuteTestCase() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.util.HashMap value = null;
            value = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
            System.out.println(value);
           
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }
    
   /* public void test5RemoteFacadeCfcExecuteTestCaseBlueDragon() throws Exception {
    	String ServiceURL = BDServiceURL;
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.util.HashMap value = null;
            value = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
            System.out.println(value);
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }*/
    
    public void test5RemoteFacadeCfcExecuteTestCaseBlueDragon() throws Exception {
    	String ServiceURL = BDServiceURL;
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        RemoteFacadeServiceLocator locator = new RemoteFacadeServiceLocator(ServiceURL,username,password);
        locator.setRemoteServerType(RemoteServerType.BLUEDRAGON);
        try {
            binding = locator.getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.util.HashMap value = null;
            value = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
            //System.out.println(value);
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test6RemoteFacadeCfcGetComponentMethods() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.lang.Object[] value = null;
            value = binding.getComponentMethods("mxunit.PluginDemoTests.HodgePodgeTest");
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test7RemoteFacadeCfcStartTestRun() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.lang.String value = null;
            value = binding.startTestRun();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }
    
    public void test8RemoteFacadeCfcGetServerType() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        try {
            binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            java.lang.String value = null;
            value = binding.getServerType();
            assertEquals("ColdFusion Server",value);
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
          
    }
    
    public void test8RemoteFacadeCfcGetServerTypeBlueDragon() throws Exception {
    	String ServiceURL = BDServiceURL;
        org.mxunit.eclipseplugin.actions.bindings.RemoteFacade binding;
        binding = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeServiceLocator(ServiceURL,username,password).getRemoteFacadeCfc();
       
        // Test operation
        try {
            java.lang.String value = null;
            value = binding.getServerType();
            assertEquals("BlueDragon",value);
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
          
    }

}
