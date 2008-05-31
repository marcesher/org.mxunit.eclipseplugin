package org.mxunit.eclipseplugin.model;

import junit.framework.TestCase;

public class RemoteFacadeRegistryTest extends TestCase {
	
	RemoteFacadeRegistry registry;
	RemoteServerType cf = RemoteServerType.COLDFUSION;
	RemoteServerType bd = RemoteServerType.BLUEDRAGON;
	
	String cfURL1 = "http://localhost/mxunit/RemoteFacade.cfc";
	String cfURL2 = "http://localhost/custom/RemoteFacade.cfc";
	String bdURL1 = "http://localhost:8501/mxunit/RemoteFacade.cfc";
	
	public void setUp(){
		registry = RemoteFacadeRegistry.getRegistry();
	}
	
	private void registerSimple(){
		registry.registerFacade(cfURL1, "ColdFusion");
		registry.registerFacade(cfURL1, "ColdFusion");
		registry.registerFacade(cfURL2, "ColdFusion");
		registry.registerFacade(bdURL1, "BlueDragon");
	}
	
	public void testGetRegisteredFacadeType() {
		registerSimple();
		assertEquals(cf, registry.getRegisteredFacadeType(cfURL1)  );
		assertEquals(cf, registry.getRegisteredFacadeType(cfURL2)  );
		assertEquals(bd, registry.getRegisteredFacadeType(bdURL1)  );
	}
	
	public void testGetRegisteredFacadeTypeWhenNotRegistered() {
		assertNull(registry.getRegisteredFacadeType("blah")  );
	}

	public void testRegisterFacade() {
		RemoteServerType actual = registry.registerFacade(cfURL1, "ColdFusion");
		assertEquals(cf,actual);
		RemoteServerType fetched = registry.getRegisteredFacadeType(cfURL1);
		assertEquals(cf,fetched);
	}

	public void testConvertStringToTypeColdFusion() {
		RemoteServerType actual = registry.convertStringToType("ColdFusion Server");
		assertEquals(cf,actual);
	}
	
	public void testConvertStringToTypeBlueDragon() {		
		RemoteServerType actual = registry.convertStringToType("BlueDragon");
		assertEquals(bd,actual);
	}
	
	public void testConvertStringToTypeUnknown() {
		RemoteServerType actual = registry.convertStringToType("idunno");
		assertEquals(cf,actual);
	}

}
