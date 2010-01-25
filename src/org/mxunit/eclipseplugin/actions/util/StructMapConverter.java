package org.mxunit.eclipseplugin.actions.util;

import java.util.HashMap;
import java.util.Map;

import org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.StructMap;
import org.mxunit.eclipseplugin.actions.bindings.generated.bluedragon.StructMapItem;

/**
 * for converting the BlueDragon structmap into a HashMap. This way, our RunTestsAction doesn't have to 
 * do any special bullshit. the BlueDragon binding can run executeTestCase, convert the returned
 * StructMap to a map with this converter, and return a Map just like the normal binding does
 * @author Marc Esher
 * May 15, 2008
 */
public class StructMapConverter {
	
	public HashMap<String, Object> convertToHashMap(StructMap structMap){
		HashMap<String, Object> converted = new HashMap<String, Object>();
		
		StructMapItem[] items = structMap.getItem();
		
		for (int i = 0; i < items.length; i++) {
			StructMapItem item = items[i];
			
			if(item.getVal() instanceof StructMap){
				converted.put(  (String) item.getKey(),  convertToHashMap((StructMap) item.getVal())  );
			}else if(item.getVal() instanceof Object[]){
				Object[] a_StructMaps = (Object[])item.getVal();
				Map[] a_convertedMaps = new HashMap[a_StructMaps.length];
				for (int j = 0; j < a_StructMaps.length; j++) {
					a_convertedMaps[j] = convertToHashMap( (StructMap) a_StructMaps[j] );
				}
				converted.put((String) item.getKey(), a_convertedMaps);
				
			}else{
				converted.put((String) item.getKey(), item.getVal());
			}
		}
		
		return converted;
	}

}
