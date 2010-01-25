package org.mxunit.eclipseplugin.actions.bindings;

import java.net.MalformedURLException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator;
import org.mxunit.eclipseplugin.model.RemoteServerType;

public class Custom_RemoteFacadeServiceLocator extends	RemoteFacadeServiceLocator {
	
    private RemoteServerType remoteServerType = RemoteServerType.COLDFUSION;

	public void setRemoteServerType(RemoteServerType remoteServerType) {
		this.remoteServerType = remoteServerType;
		
	}
	
    public org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade getRemoteFacadeCfc() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
         try {
             endpoint = new java.net.URL(getRemoteFacadeCfcAddress());
         }
         catch (java.net.MalformedURLException e) {
             throw new javax.xml.rpc.ServiceException(e);
         }
         return getRemoteFacadeCfc(endpoint);
     }
    public RemoteFacade getRemoteFacadeCfc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	RemoteFacadeCfcSoapBindingStub _stub = new Custom_RemoteFacadeImpl(portAddress, this);
        	_stub.setPortName(getRemoteFacadeCfcWSDDServiceName());
        	
        	//warning: super lame-o alert. Fuck You, BlueDragon.
        	if(remoteServerType == RemoteServerType.BLUEDRAGON){
        		return getBlueDragonBinding(portAddress);
        	}
        	
          
            return (RemoteFacade) _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }
    
    
    public RemoteFacade getBlueDragonBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
    	return (RemoteFacade) getRawBlueDragonBinding(portAddress);
    }
    
    public Custom_RemoteFacadeBlueDragonImpl getRawBlueDragonBinding(java.net.URL portAddress){
    	try {
    		Custom_RemoteFacadeBlueDragonImpl _bdstub;
    		org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator
    			obdLocator = new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator();
			_bdstub = new Custom_RemoteFacadeBlueDragonImpl(portAddress, obdLocator);
			_bdstub.setPortName(getRemoteFacadeCfcWSDDServiceName());
			
			return _bdstub;
			
		} catch (AxisFault e) {
			return null;
		}
    }
    
    /*
     * for testing only!
     */
    public RemoteFacade getBlueDragonBinding(){
    	try {
    		org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator
			obdLocator = new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator();
			return getBlueDragonBinding( new java.net.URL(obdLocator.getRemoteFacadeCfcAddress()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    public Custom_RemoteFacadeBlueDragonImpl getRawBlueDragonBinding(){
    	try {
    		org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator
			obdLocator = new org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator();
			return getRawBlueDragonBinding( new java.net.URL(obdLocator.getRemoteFacadeCfcAddress()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }


}
