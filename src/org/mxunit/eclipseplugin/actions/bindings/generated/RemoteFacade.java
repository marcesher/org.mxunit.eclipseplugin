/**
 * RemoteFacade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.generated;


public interface RemoteFacade extends java.rmi.Remote {
    public boolean ping() throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
    public void initializeSuitePool() throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
    public java.lang.String endTestRun(java.lang.String testRunKey) throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
    public double purgeSuitePool() throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
    public java.util.HashMap executeTestCase(java.lang.String componentName, java.lang.String methodNames, java.lang.String testRunKey) throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
    public java.lang.Object[] getComponentMethods(java.lang.String componentName) throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
    public java.lang.String startTestRun() throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
	public String getServerType()  throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
	public void setTimeout(int timeout)  throws java.rmi.RemoteException, org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
}
