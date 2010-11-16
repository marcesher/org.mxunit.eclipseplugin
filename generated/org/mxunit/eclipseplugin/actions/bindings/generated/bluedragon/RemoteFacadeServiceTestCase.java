/**
 * RemoteFacadeServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon;

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
        org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.StructMap value = null;
        value = binding.executeTestCase(new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
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
