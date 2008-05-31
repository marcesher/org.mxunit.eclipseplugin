package org.mxunit.eclipseplugin.sandbox;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.map.CaseInsensitiveMap;

public class StructReturnInvoker {
    private final Service service = new Service();
    private Call call;
    private String endpoint = "";
    private final String qn = "http://objectstuff.sandbox";

    boolean isInitialized = false;
    
    public void initializeCall(){        
        try {
            endpoint = "http://localhost/sandbox/ObjectStuff/RemoteThingie.cfc?wsdl";
            call = (Call)service.createCall();
            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            isInitialized = true;
        } catch (Exception e) {
            isInitialized = false;
            System.out.println("Error creating webservice call to endpoint " + endpoint);
            System.out.println(e.toString());
        }
    }
    
    public Map getSingleTestResult(){
        HashMap results = new HashMap();
        String[] params = {"com.argus.blah"};
        try {        
            initializeCall();
            call.setOperationName(new QName(qn, "returnStruct"));
            
            results = (HashMap)call.invoke(params);
            System.out.println(results);
        } catch (IOException e) {
                        System.out.println(e);
        }
        
        return results;
    }
    
    public Map[] getResults(){
        Map[] results;
        String[] names = {"com.argus.blah","com.argus.blah2","com.argus.blah3"};
        Object[] params = {names};
        try {        
            initializeCall();
            call.setOperationName(new QName(qn, "returnArrayOfStructs"));
            
            Object[] tmpresult = (Object[])call.invoke(params);
            results = new Map[tmpresult.length];
            //System.out.println(tmpresult);
            for (int i = 0; i < tmpresult.length; i++) {
                Map thisResult = (Map)tmpresult[i];
                CaseInsensitiveMap cimap = new CaseInsensitiveMap(thisResult);
                results[i] = cimap;              
            }
            System.out.println(results);
        } catch (IOException e) {
            results = new HashMap[1];
            e.printStackTrace();
            System.out.println(e);
        }
        
        return results;
    }
    
    public Map getResultsAsMapOfMaps(){
    	Map results;
        String[] names = {"com.argus.blah","com.argus.blah.sub","com.argus.blah2","com.argus.blah3"};
        Object[] params = {names};
        try {        
            initializeCall();
            call.setOperationName(new QName(qn, "returnStructOfStructs"));
            
            Map tmpresult = (Map)call.invoke(params);
            results = new CaseInsensitiveMap(tmpresult);
            /*
            //System.out.println(tmpresult);
            for (int i = 0; i < tmpresult.length; i++) {
                Map thisResult = (Map)tmpresult[i];
                CaseInsensitiveMap cimap = new CaseInsensitiveMap(thisResult);
                results[i] = cimap;              
            }
            */
            System.out.println(results);
        } catch (IOException e) {
            results = new HashMap();
            e.printStackTrace();
            System.out.println(e);
        }
        
        return results;
    }
    
    public Map getResultsAsMapOfMapofMaps(){
    	Map results;
    	
    	Map comp1 = new HashMap();
    	
    	Map methods1 = new HashMap();
    	Map methods2 = new HashMap();
    	
    	methods1.put("testSomething", new HashMap());
    	methods1.put("testSomethingElse", new HashMap());
    	methods2.put("testThis",new HashMap());
    	methods2.put("testThat", new HashMap());
    	
    	comp1.put("com.blah.blah", methods1);
    	comp1.put("com.blah.blah.sub", methods2);
    	
        
        Object[] params = {comp1};
        try {        
            initializeCall();
            call.setOperationName(new QName(qn, "returnStructOfStructs2"));
            
            Map tmpresult = (Map)call.invoke(params);
            results = new TreeMap(tmpresult);
            
            System.out.println(results);
        } catch (IOException e) {
            results = new HashMap();
            e.printStackTrace();
            System.out.println(e);
        }
        
        return results;
    }
    
    public Map getResultsWithTagContext(){
        HashMap results = new HashMap();
        
        try {        
            initializeCall();
            call.setOperationName(new QName(qn, "returnStructWithTagContext"));
            
            results = (HashMap)call.invoke(new Object[0]);
            System.out.println(results);
            Object[] tmp = (Object[]) results.get("TAGCONTEXT");
            Map[] newMap = new HashMap[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                Map trace = (Map) tmp[i];
                System.out.println(trace);
                newMap[i] = trace;
            }
            Map[] tc = newMap;
            System.out.println(newMap);
            
        } catch (IOException e) {
                        System.out.println(e);
        }
        
        return results;
    }

}
