package org.mxunit.eclipseplugin.actions.bindings;

import java.net.URL;
import java.util.HashMap;

import org.apache.axis.AxisFault;
import org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeServiceLocator;
import org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.StructMap;
import org.mxunit.eclipseplugin.actions.util.StructMapConverter;

/**
 * Performs a BlueDragon-specific version of executeTestCase. This is necessitated
 * by BD's returning a StructMap bean instead of a HashMap. We let Axis do the StructMap
 * deserialization, then convert the StructMap to a HashMap for return
 * @author marc esher
 * May 14, 2008
 *
 */
public class Custom_RemoteFacadeBlueDragonImpl extends	org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub {

	public Custom_RemoteFacadeBlueDragonImpl() throws AxisFault {
		super();
	}
	
	public Custom_RemoteFacadeBlueDragonImpl(URL portAddress, RemoteFacadeServiceLocator remoteFacadeServiceLocator) throws AxisFault {
		super(portAddress, remoteFacadeServiceLocator);
	}
	
	
	
	/*public HashMap executeTestCase(java.lang.String componentName, java.lang.String methodNames, java.lang.String testRunKey) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
        OperationDesc oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTestCase");
        ParameterDesc param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "componentName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "methodNames"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "TestRunKey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wstypes.newatlanta.com", "StructMap"));
        oper.setReturnClass(org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.StructMap.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "executeTestCaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
       
               
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(oper);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://framework.mxunit.na_svr", "executeTestCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
		 try {        
			 
			 java.lang.Object _resp = _call.invoke(new java.lang.Object[] {componentName, methodNames, testRunKey});
		
		        if (_resp instanceof java.rmi.RemoteException) {
		            throw (java.rmi.RemoteException)_resp;
		        }
		        else {
		        	 extractAttachments(_call);
		        	 
		        	//right here are the only lines I wrote! Everything else is Axis boilerplate gen code
		        	HashMap<String, Object> converted = new HashMap<String, Object>();
		            StructMapConverter converter = new StructMapConverter();
		            converted = converter.convertToHashMap((StructMap) _resp);
		            return converted;
		        }
		  } catch (org.apache.axis.AxisFault axisFaultException) {
		  throw axisFaultException;
		}
    }*/

	
	
	public HashMap executeTestCase(java.lang.String componentName, java.lang.String methodNames, java.lang.String testRunKey) throws java.rmi.RemoteException {
       StructMap structMap = super.executeTestCase_internal(componentName, methodNames, testRunKey);
       HashMap<String, Object> converted = new HashMap<String, Object>();
       StructMapConverter converter = new StructMapConverter();
       converted = converter.convertToHashMap((StructMap) structMap);
       return converted;
    }
}
