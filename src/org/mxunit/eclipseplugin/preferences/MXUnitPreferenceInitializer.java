package org.mxunit.eclipseplugin.preferences;

import java.io.File;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;

import org.mxunit.eclipseplugin.MXUnitPlugin;

/**
 * Class used to initialize default preference values.
 */
public class MXUnitPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = MXUnitPlugin.getDefault()
				.getPreferenceStore();
		
		store.setDefault(MXUnitPreferenceConstants.P_FACADEURL,
				"http://localhost/mxunit/framework/RemoteFacade.cfc");
		store.setDefault(MXUnitPreferenceConstants.P_MAX_HISTORY, 30);
		store.setDefault(MXUnitPreferenceConstants.P_REMOTE_CALL_TIMEOUT, 30);
		store.setDefault(MXUnitPreferenceConstants.P_COLOR_PASS, "95,191,95");
		store.setDefault(MXUnitPreferenceConstants.P_COLOR_FAIL, "159,63,63");
		store.setDefault(MXUnitPreferenceConstants.P_COLOR_STOPPED, "120,120,120");
		store.setDefault(MXUnitPreferenceConstants.P_TEST_ORDERING, true);
	}
	
	/**
	 * lame-and-dirty way of setting a reasonable default. I don't own a mac, don't have a linux
	 * box, and don't have the wherewithal right now to make this friendlier for our
	 * non-windows friends. 
	 * @return best possible match for webroot
	 */
	public static String getReasonableDefaultWebroot(char separatorChar){
		
		String foundRoot = "";
		String decidedRoot = "";
		
		String[] windowsRoots = new String[]{"C:\\inetpub\\wwwroot","C:\\coldfusion8\\wwwroot","C:\\cfusionmx7\\wwwroot","C:\\cfusionmx\\wwwroot"};
		String[] macRoots = new String[]{"/Library/Web/Server/Documents","/var/www/html"};
		String[] finalRoots;
		
		if(separatorChar == '/'){
			finalRoots = macRoots;
		}else{
			finalRoots = windowsRoots;
		}
		
		for (int i = 0; i < finalRoots.length; i++) {
			File root = new File(finalRoots[i]);
			if(root.exists()){
				foundRoot = finalRoots[i];
				break;
			}
		}	
		//if no root is found, return an empty string. this ensures that the appropriate popup will display when the user
		//tries to search for tests the first time!
		decidedRoot = foundRoot.length()>0 ? foundRoot : "";
		System.out.println("Returning root " + decidedRoot);
		return decidedRoot;
	}

}
