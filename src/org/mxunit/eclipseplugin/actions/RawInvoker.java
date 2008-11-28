package org.mxunit.eclipseplugin.actions;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class RawInvoker {
    private final Service service = new Service();
    private Call call;
    private String endpoint = "";
    private final String qn = "http://objectstuff.sandbox";

    boolean isInitialized = false;
    
    public void initializeCall(){      
        
        try {
            endpoint = "http://localhost:80/mxunit/framework/RemoteFacade.cfc?wsdl";
            call = (Call)service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            
            /*if you get errors, comment this out and see if that fixes it! 
            Hashtable headers = new Hashtable();
            headers.put(HTTPConstants.HEADER_USER_AGENT, "Mozilla/5.0(Axis-MXUnit)");
            call.removeProperty("HTTP-Request-Headers");
            call.setProperty("HTTP-Request-Headers", headers);   */
         
            
            isInitialized = true;
        } catch (Exception e) {
            isInitialized = false;
            System.out.println("Error creating webservice call to endpoint " + endpoint);
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    

    public void runPing(){
        try {        
            initializeCall();
            call.setOperationName(new QName(qn, "PING"));
           
           Object results = call.invoke(new Object[0]);
            System.out.println(results);
        } catch (IOException e) {            
            System.out.println(e);
            e.printStackTrace();
        }
    }
}