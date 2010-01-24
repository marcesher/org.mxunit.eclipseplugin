package org.mxunit.eclipseplugin.actions.bindings;

import java.net.URL;
import java.util.Hashtable;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeCfcSoapBindingStub;
import org.mxunit.eclipseplugin.actions.bindings.generated.RemoteFacadeServiceLocator;

public class Custom_RemoteFacadeImpl extends RemoteFacadeCfcSoapBindingStub {

	public Custom_RemoteFacadeImpl() throws AxisFault {
		super();
		// TODO Auto-generated constructor stub
	}

	public Custom_RemoteFacadeImpl(URL portAddress,
			RemoteFacadeServiceLocator remoteFacadeServiceLocator)
			throws AxisFault {
		super(portAddress,remoteFacadeServiceLocator);
	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			Call _call =  super.createCall();

			// only use this if we need to send credentials; it slows it down
			// otherwise

			if (super.cachedUsername != null) {
				System.out
						.println("Username is not empty. Using CommonsHTTPSender.");
				org.apache.axis.transport.http.CommonsHTTPSender requestConnectionHandler = new org.apache.axis.transport.http.CommonsHTTPSender();
				org.apache.axis.transport.http.CommonsHTTPSender responseConnectionHandler = new org.apache.axis.transport.http.CommonsHTTPSender();
				_call.setClientHandlers(requestConnectionHandler,
						responseConnectionHandler);

				// without this, it can throw a "content not allowed in prolog"
				// message when using the CommonsHTTPSender;
				// I had this happen to me when running against the built-in CF
				// webserver; it did not behave this way with IIS.
				// found the fix here:
				// http://tjordahl.blogspot.com/2007/03/apache-axis-and-commons-httpclient.html
				Hashtable headers = new Hashtable();
				headers.put("chunked", "false");
				headers.put("Connection", "keep-alive");
				_call.setProperty("HTTP-Request-Headers", headers);
			}
			return _call;
		} catch (java.lang.Throwable t) {
			throw new org.apache.axis.AxisFault(
					"Failure trying to get the Call object", t);
		}
	}

}
