/**
 * RemoteFacadeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings;

import org.mxunit.eclipseplugin.model.RemoteServerType;

public interface RemoteFacadeService extends javax.xml.rpc.Service {
    public java.lang.String getRemoteFacadeCfcAddress();

    public org.mxunit.eclipseplugin.actions.bindings.RemoteFacade getRemoteFacadeCfc() throws javax.xml.rpc.ServiceException;

    public org.mxunit.eclipseplugin.actions.bindings.RemoteFacade getRemoteFacadeCfc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    
    public void setRemoteServerType(RemoteServerType remoteServerType);
}
