/**
 * RemoteFacadeServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.mxunit.eclipseplugin.actions.bindings.generated.CFCInvocationException;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacade;
import org.mxunit.eclipseplugin.actions.bindings.generated.TestMethodBean;
import org.mxunit.eclipseplugin.model.RemoteServerType;

import com.sun.corba.se.spi.activation.Locator;

public class RemoteFacadeServiceTestCase extends junit.framework.TestCase {
	
	String serviceURL = "http://localhost/mxunit/framework/RemoteFacade.cfc?wsdl";
	String obdServiceURL = "http://localhost:8090/mxunit/framework/RemoteFacade.cfc?wsdl";
	//String username = "marcnet\\marctest";
	//String password = "marctest";
	
	String username = "";
	String password = "";
	
	
	RemoteFacade cfBinding;
	RemoteFacade obdBinding;
	
	String testCase = "mxunit.PluginDemoTests.CompareDialogTest";
	String testMethod = "QueryCompareDifferences";
	
	/*NOTE TO SELF: to watch the wire, 
	 * 
	 * 1)add port 1234 to the service url
	 * 2) in a command line, CD to the lib directory of this plugin.
	 * 3) at the command line, type: java -cp axis.jar org.apache.axis.utils.tcpmon
	 * 4) in tcpmon app, set listenport=1234, targethostname=localhost, and targetport = 80. click "add". 
	 * 5) run a test. the calls will show up in the tcpmon app
	 */
	
	
    public RemoteFacadeServiceTestCase(java.lang.String name) {
        super(name);
    }
    
    public void setUp(){
    	Custom_RemoteFacadeServiceLocator locator = new Custom_RemoteFacadeServiceLocator();
    	locator.setRemoteFacadeCfcEndpointAddress(serviceURL);
    	Custom_RemoteFacadeServiceLocator obdlocator = new Custom_RemoteFacadeServiceLocator();
    	obdlocator.setRemoteFacadeCfcEndpointAddress(obdServiceURL);
    	obdlocator.setRemoteServerType(RemoteServerType.BLUEDRAGON);
		try {
			cfBinding = locator.getRemoteFacadeCfc();
			obdBinding =  obdlocator.getRemoteFacadeCfc();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    } 
    
    
    
    public void testPing() throws CFCInvocationException, RemoteException{
    	boolean response = cfBinding.ping();
    	assertTrue(response);
    }
    
    public void testPing_OBD() throws CFCInvocationException, RemoteException{
    	boolean response = obdBinding.ping();
    	assertTrue(response);
    }
    
    public void testGetFrameworkVersion() throws CFCInvocationException, RemoteException {
    	String version = cfBinding.getFrameworkVersion();
    	Calendar date = cfBinding.getFrameworkDate();
    	System.out.println(version);
    	System.out.println(date);
    }
    
    public void testGetServerType() throws CFCInvocationException, RemoteException{
    	String type = cfBinding.getServerType();
    	System.out.println(type);
    	assertTrue(type.contains("ColdFusion"));
    }
    public void testGetServerType_OBD() throws CFCInvocationException, RemoteException{
    	String type = obdBinding.getServerType();
    	System.out.println(type);
    	assertTrue(type.contains("BlueDragon"));
    }
   
    public void testExecuteTestCase() throws CFCInvocationException, RemoteException{
    	HashMap results = cfBinding.executeTestCase(testCase, testMethod, "");
    	System.out.println(results);
    }
    
    public void testExecuteTestCase_OBD() throws CFCInvocationException, RemoteException{
    	HashMap results = obdBinding.executeTestCase(testCase, testMethod, "");
    	System.out.println(results);
    }
    
    public void testGetComponentMethods() throws CFCInvocationException, RemoteException{
    	Object[] methods = cfBinding.getComponentMethods("mxunit.plugindemotests.HodgePodgeTest");
    	for (Object object : methods) {
			System.out.println(object);
		}
    }
    
    public void testGetMethodsAsBeans() throws CFCInvocationException, RemoteException{
    	Object[] beans = cfBinding.getComponentMethodsRich2();
    	System.out.println(beans);
    	for (Object object : beans) {
			System.out.println(object);
		}
    }

}
