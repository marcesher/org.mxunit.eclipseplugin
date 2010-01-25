/**
 * RemoteFacadeServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon;

import java.rmi.RemoteException;
import java.util.HashMap;

import org.mxunit.eclipseplugin.actions.bindings.Custom_RemoteFacadeBlueDragonImpl;
import org.mxunit.eclipseplugin.actions.bindings.Custom_RemoteFacadeServiceLocator;
import org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;

public class RemoteFacadeServiceTestCase extends junit.framework.TestCase {
    public RemoteFacadeServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testRemoteFacadeCfcWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfcAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1RemoteFacadeCfcPing() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.ping();
        // TBD - validate results
    }

    public void test2RemoteFacadeCfcInitializeSuitePool() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.initializeSuitePool();
        // TBD - validate results
    }

    public void test3RemoteFacadeCfcPurgeSuitePool() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        double value = -3;
        value = binding.purgeSuitePool();
        // TBD - validate results
    }

    public void test4RemoteFacadeCfcGetServerType() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getServerType();
        // TBD - validate results
    }

    public void test5RemoteFacadeCfcGetFrameworkVersion() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getFrameworkVersion();
        // TBD - validate results
    }

    public void test6RemoteFacadeCfcGetFrameworkDate() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.util.Calendar value = null;
        value = binding.getFrameworkDate();
        // TBD - validate results
    }

    public void test7RemoteFacadeCfcStartTestRun() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.startTestRun();
        // TBD - validate results
    }

    public void test8RemoteFacadeCfcEndTestRun() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.endTestRun(new java.lang.String());
        // TBD - validate results
    }

    public void test9RemoteFacadeCfcExecuteTestCase() throws Exception {
        Custom_RemoteFacadeBlueDragonImpl binding;

            binding = (Custom_RemoteFacadeBlueDragonImpl) 
            	new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator()
            		.getRemoteFacadeCfc();

        System.out.println(binding.getPortName());
        System.out.println(binding);
        
        HashMap map = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
        //System.out.println(map);
    }
    
    public void testCustomOBDBinding_3() throws CFCInvocationException, RemoteException{
    	Custom_RemoteFacadeServiceLocator locator = new Custom_RemoteFacadeServiceLocator();
    	
    	Custom_RemoteFacadeBlueDragonImpl binding = locator.getRawBlueDragonBinding();
    	System.out.println(binding.getPortName());
    	System.out.println(binding);
    	 HashMap map = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
         System.out.println(map);
    }
    
    
    
    
    public void testCustomOBDBinding() throws CFCInvocationException, RemoteException{
    	Custom_RemoteFacadeServiceLocator locator = new Custom_RemoteFacadeServiceLocator();
    	RemoteFacade binding = locator.getBlueDragonBinding();
    	System.out.println(((Custom_RemoteFacadeBlueDragonImpl)binding).getPortName());
    	 HashMap map = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
         System.out.println(map);
    }
    
    public void testCustomOBDBinding_2() throws CFCInvocationException, RemoteException{
    	Custom_RemoteFacadeServiceLocator locator = new Custom_RemoteFacadeServiceLocator();
    	RemoteFacade binding = locator.getBlueDragonBinding();
    	
    	 HashMap map = binding.executeTestCase("mxunit.PluginDemoTests.HodgePodgeTest", "testOK", "");
         System.out.println(map);
    }
    

    

    public void test10RemoteFacadeCfcGetComponentMethods() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Object[] value = null;
        value = binding.getComponentMethods(new java.lang.String());
        // TBD - validate results
    }

}
