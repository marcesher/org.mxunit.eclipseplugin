package org.mxunit.eclipseplugin.model;

import java.util.HashMap;

/**
 * Map of remotefacade URLs to RemoteServerTypes. This was all brought about because of the need
 * to use different "binding" objects for CF vs. BlueDragon, since the returned "type" for structs is different
 * coming out of those different servers. This is a singleton.
 * @author mesher
 * May 2008
 */
public class RemoteFacadeRegistry {

	private HashMap<String,RemoteServerType> registry = new HashMap<String,RemoteServerType>();
	private static RemoteFacadeRegistry instance = new RemoteFacadeRegistry();
	
	private RemoteFacadeRegistry(){
		
	}
	
	/**
	 * returns the RemoteFacadeRegistry instance
	 * @return RemoteFacadeRegistry
	 */
	public static RemoteFacadeRegistry getRegistry(){
		return instance;
	}
	
	/**
	 * fetches the RemoteServerType for a given URL
	 * @param facadeURL the url
	 * @return RemoteServerType
	 */
	public RemoteServerType getRegisteredFacadeType(String facadeURL){
		return registry.get(facadeURL.toLowerCase());
	}
	
	/**
	 * adds a URL to the registry, converting the serverTypeString to a proper RemoteServerType
	 * @param facadeURL
	 * @param serverTypeString
	 * @return the RemoteServerType for this facadeURL
	 */
	public RemoteServerType registerFacade(String facadeURL, String serverTypeString){
		RemoteServerType type = convertStringToType(serverTypeString);
		registry.put(facadeURL.toLowerCase(), type);
		return getRegisteredFacadeType(facadeURL);
	}
	
	/**
	 * Decides on a RemoteServerType to use based on the passed-in string. 
	 * If it can't figure it out, it chooses ColdFusion
	 * @param serverTypeString
	 * @return
	 */
	public RemoteServerType convertStringToType(String serverTypeString){
		RemoteServerType type = RemoteServerType.COLDFUSION;
		if(serverTypeString.toLowerCase().indexOf("dragon") >= 0){
			type = RemoteServerType.BLUEDRAGON;
		}
		return type;
	}
	
	
}
