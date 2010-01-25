/**
 * RemoteFacadeServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.generated;

public class RemoteFacadeServiceTestCase extends junit.framework.TestCase {
    public RemoteFacadeServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testRemoteFacadeCfcWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfcAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1RemoteFacadeCfcPing() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            boolean value = false;
            value = binding.ping();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test2RemoteFacadeCfcGetServerType() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.lang.String value = null;
            value = binding.getServerType();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test3RemoteFacadeCfcInitializeSuitePool() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            binding.initializeSuitePool();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test4RemoteFacadeCfcEndTestRun() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.lang.String value = null;
            value = binding.endTestRun(new java.lang.String());
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test5RemoteFacadeCfcGetFrameworkDate() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.util.Calendar value = null;
            value = binding.getFrameworkDate();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test6RemoteFacadeCfcPurgeSuitePool() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            double value = -3;
            value = binding.purgeSuitePool();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test7RemoteFacadeCfcExecuteTestCase() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.util.HashMap value = null;
            value = binding.executeTestCase(new java.lang.String(), new java.lang.String(), new java.lang.String());
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test8RemoteFacadeCfcGetFrameworkVersion() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.lang.String value = null;
            value = binding.getFrameworkVersion();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test9RemoteFacadeCfcGetComponentMethods() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.lang.Object[] value = null;
            value = binding.getComponentMethods(new java.lang.String());
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test10RemoteFacadeCfcStartTestRun() throws Exception {
        org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub binding;
        try {
            binding = (org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub)
                          new org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator().getRemoteFacadeCfc();
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
        try {
            java.lang.String value = null;
            value = binding.startTestRun();
        }
        catch (org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException e1) {
            throw new junit.framework.AssertionFailedError("CFCInvocationException Exception caught: " + e1);
        }
            // TBD - validate results
    }

}
