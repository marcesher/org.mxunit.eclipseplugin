package org.mxunit.eclipseplugin.actions.bindings;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.axis.AxisFault;
import org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacadeCfcSoapBindingStub;
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
public class Custom_RemoteFacadeBlueDragonImpl  implements org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade {
	
	//implements org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.RemoteFacade
	RemoteFacadeCfcSoapBindingStub stub;

	public Custom_RemoteFacadeBlueDragonImpl() throws AxisFault {
		super();
	}

	public void setBlueDragonStub( RemoteFacadeCfcSoapBindingStub stub ){
		this.stub = stub;
	}
	
	public RemoteFacadeCfcSoapBindingStub getBlueDragonStub(){
		return stub;
	}
	

	
	public HashMap executeTestCase(java.lang.String componentName, java.lang.String methodNames, java.lang.String testRunKey) throws java.rmi.RemoteException {
       StructMap structMap = stub.executeTestCase(componentName, methodNames, testRunKey);
       HashMap<String, Object> converted = new HashMap<String, Object>();
       StructMapConverter converter = new StructMapConverter();
       converted = converter.convertToHashMap((StructMap) structMap);
       return converted;
    }

	public boolean ping() throws RemoteException, CFCInvocationException {
		return stub.ping();
	}

	public String getServerType() throws RemoteException,
			CFCInvocationException {
		return stub.getServerType();
	}

	public void initializeSuitePool() throws RemoteException,
			CFCInvocationException {
		stub.initializeSuitePool();
		
	}

	public String endTestRun(String testRunKey) throws RemoteException,
			CFCInvocationException {
		return stub.endTestRun(testRunKey);
	}

	public Calendar getFrameworkDate() throws RemoteException,
			CFCInvocationException {
		return stub.getFrameworkDate();
	}

	public double purgeSuitePool() throws RemoteException,
			CFCInvocationException {
		return stub.purgeSuitePool();
	}

	public String getFrameworkVersion() throws RemoteException,
			CFCInvocationException {
		return stub.getFrameworkVersion();
	}

	public Object[] getComponentMethods(String componentName)
			throws RemoteException, CFCInvocationException {
		return stub.getComponentMethods(componentName);
	}

	public String startTestRun() throws RemoteException, CFCInvocationException {
		return stub.startTestRun();
	}

	public Object[] getComponentMethodsRich2() throws RemoteException,
			CFCInvocationException {
		return stub.getComponentMethodsRich2();
	}
}
