package org.mxunit.eclipseplugin.actions.bindings;

import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator;
import org.mxunit.eclipseplugin.model.RemoteServerType;

public class Custom_RemoteFacadeServiceLocator extends	RemoteFacadeServiceLocator {
	
    private RemoteServerType remoteServerType = RemoteServerType.COLDFUSION;

	public Custom_RemoteFacadeServiceLocator(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	public void setRemoteServerType(RemoteServerType remoteServerType) {
		this.remoteServerType = remoteServerType;
		
	}
	
    public org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade getRemoteFacadeCfc(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	RemoteFacadeCfcSoapBindingStub _stub = new Custom_RemoteFacadeImpl(portAddress, this);
        	//warning: super lame-o alert. Fuck You, BlueDragon.
        	if(remoteServerType == RemoteServerType.BLUEDRAGON){
        		_stub = new Custom_RemoteFacadeBlueDragonImpl(portAddress, this);
        	}
        	
            _stub.setPortName(getRemoteFacadeCfcWSDDServiceName());
          
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

}
