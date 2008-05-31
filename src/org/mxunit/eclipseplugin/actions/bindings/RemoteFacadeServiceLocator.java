/**
 * RemoteFacadeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings;

import org.mxunit.eclipseplugin.model.RemoteServerType;


public class RemoteFacadeServiceLocator extends org.apache.axis.client.Service implements org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeService {

    // Use to get a proxy class for RemoteFacadeCfc
    private java.lang.String RemoteFacadeCfc_address = "";
    private RemoteServerType remoteServerType = RemoteServerType.COLDFUSION;
    private java.lang.String username = null;
    private java.lang.String password = null;
    
    public RemoteFacadeServiceLocator(String url){
    	RemoteFacadeCfc_address = url;    
    }
    public RemoteFacadeServiceLocator(String url, String username, String password){
    	RemoteFacadeCfc_address = url;  
    	if(username != null && username.trim().length()>0){
    		System.out.println("Setting username and password.");
	    	this.username = username;
	    	this.password = password;
    	}
    }
    
    
    public java.lang.String getRemoteFacadeCfcAddress() {
        return RemoteFacadeCfc_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RemoteFacadeCfcWSDDServiceName = "RemoteFacadeCfc";

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
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getRemoteFacadeCfc(endpoint);
    }

    public org.mxunit.eclipseplugin.actions.bindings.RemoteFacade getRemoteFacadeCfc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	RemoteFacadeImpl _stub = new RemoteFacadeImpl(portAddress, this);
        	//warning: super lame-o alert
        	if(remoteServerType == RemoteServerType.BLUEDRAGON){
        		_stub = new RemoteFacadeBlueDragonImpl(portAddress, this);
        	}
        	
            _stub.setPortName(getRemoteFacadeCfcWSDDServiceName());
            _stub.setUsername(username);
            _stub.setPassword(password);
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.mxunit.eclipseplugin.actions.bindings.RemoteFacade.class.isAssignableFrom(serviceEndpointInterface)) {
                org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeImpl _stub = new org.mxunit.eclipseplugin.actions.bindings.RemoteFacadeImpl(new java.net.URL(RemoteFacadeCfc_address), this);
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
        String inputPortName = portName.getLocalPart();
        if ("RemoteFacadeCfc".equals(inputPortName)) {
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
            ports.add(new javax.xml.namespace.QName("RemoteFacadeCfc"));
        }
        return ports.iterator();
    }
	public void setRemoteServerType(RemoteServerType remoteServerType) {
		this.remoteServerType = remoteServerType;
		
	}


	

}
