package org.mxunit.eclipseplugin.actions.bindings;

import java.io.IOException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


public class HttpClientTest  extends junit.framework.TestCase{
	
	public void testSomething() throws HttpException, IOException{
		String url = "http://localhost/mxunit/framework/RemoteFacade.cfc?wsdl";
		String username = "marctest";
		String password = "marctest";
		String domain = "";
		String host = "";
		
		HttpClient client = new HttpClient();
		Credentials cred = new NTCredentials(username,password,host,domain);
		
		client.getState().setCredentials(AuthScope.ANY, cred);
		HttpMethod method = new GetMethod(url);
		client.executeMethod(method);
		System.out.println(new String(method.getResponseBody()));
		method.releaseConnection();
	}
	
	
}