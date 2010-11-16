/**
 * RemoteFacade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon;

public interface RemoteFacade extends java.rmi.Remote {
    public boolean ping() throws java.rmi.RemoteException;
    public void initializeSuitePool() throws java.rmi.RemoteException;
    public double purgeSuitePool() throws java.rmi.RemoteException;
    public java.lang.String getServerType() throws java.rmi.RemoteException;
    public java.lang.String getFrameworkVersion() throws java.rmi.RemoteException;
    public java.util.Calendar getFrameworkDate() throws java.rmi.RemoteException;
    public java.lang.String startTestRun() throws java.rmi.RemoteException;
    public java.lang.String endTestRun(java.lang.String testRunKey) throws java.rmi.RemoteException;
    public org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.StructMap executeTestCase(java.lang.String componentName, java.lang.String methodNames, java.lang.String testRunKey) throws java.rmi.RemoteException;
    public java.lang.Object[] getComponentMethods(java.lang.String componentName) throws java.rmi.RemoteException;
}
