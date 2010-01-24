/**
 * RemoteFacadeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings;

public class RemoteFacadeServiceLocator extends org.apache.axis.client.Service implements org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeService {

/**
 * Main default interface into MXUnit framework from the MXUnit Ecplise
 * Plugin.
 */

    public RemoteFacadeServiceLocator() {
    }


    public RemoteFacadeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RemoteFacadeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RemoteFacadeCfc
    private java.lang.String RemoteFacadeCfc_address = "http://localhost/mxunit/framework/RemoteFacade.cfc";

    public java.lang.String getRemoteFacadeCfcAddress() {
        return RemoteFacadeCfc_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RemoteFacadeCfcWSDDServiceName = "RemoteFacade.cfc";

    public java.lang.String getRemoteFacadeCfcWSDDServiceName() {
        return RemoteFacadeCfcWSDDServiceName;
    }

    public void setRemoteFacadeCfcWSDDServiceName(java.lang.String name) {
        RemoteFacadeCfcWSDDServiceName = name;
    }

    public org.mxunit.eclipseplugin.actions.bindings.RemoteFacade getRemoteFacadeCfc() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RemoteFacadeCfc_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRemoteFacadeCfc(endpoint);
    }

    public org.mxunit.eclipseplugin.actions.bindings.RemoteFacade getRemoteFacadeCfc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeCfcSoapBindingStub _stub = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeCfcSoapBindingStub(portAddress, this);
            _stub.setPortName(getRemoteFacadeCfcWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRemoteFacadeCfcEndpointAddress(java.lang.String address) {
        RemoteFacadeCfc_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.mxunit.eclipseplugin.actions.bindings.RemoteFacade.class.isAssignableFrom(serviceEndpointInterface)) {
                org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeCfcSoapBindingStub _stub = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeCfcSoapBindingStub(new java.net.URL(RemoteFacadeCfc_address), this);
                _stub.setPortName(getRemoteFacadeCfcWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RemoteFacade.cfc".equals(inputPortName)) {
            return getRemoteFacadeCfc();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://framework.mxunit", "RemoteFacadeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://framework.mxunit", "RemoteFacade.cfc"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RemoteFacadeCfc".equals(portName)) {
            setRemoteFacadeCfcEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
