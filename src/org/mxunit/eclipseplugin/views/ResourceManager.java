package org.mxunit.eclipseplugin.views;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.mxunit.eclipseplugin.MXUnitPlugin;

public final class ResourceManager {
	
	
	private final static String PATH = "icons/";
	
	public final static String BLANK = "mxunit_labeliconBlank.png";
	public final static String OK = "mxunit_labeliconOK.png";
	public final static String ERROR = "mxunit_labeliconError.png";
	public final static String FAIL = "mxunit_labeliconFail.png";
	public final static String MAIN = "mxunit.png";
	public final static String INVOCATION_EXCEPTION = "mxunit_labeliconError.png";
	public final static String RUN = "runtests.png";
	public final static String STOP = "stop.png";
	public final static String RUNFAILURES = "runfailures.png";
	public final static String FIND = "findtests.png";
	public final static String EXPANDCOLLAPSEALL = "expandcollapse.png";
	public final static String REFRESH = "refresh.png";
	public final static String BROWSER = "internalbrowser.png";
	public final static String CFMSTACKFRAME = "stackframe_cfm.png";
	public final static String CFCSTACKFRAME = "stackframe_cfc.png";
	public final static String CIRCLE_FAIL = "fail_circle.png";
	public final static String CIRCLE_ERROR = "error_circle.png";
	public final static String HELP = "help.png";
	public final static String TOGGLE_FAILURES = "mxunit_toggleErrorFailIcon.png";
	public final static String HISTORY = "mxunit_history.png";
	
	public static Image getImage(String image){
		String url = PATH + image;
		Image registeredImage = MXUnitPlugin.getDefault().getImageRegistry().get(url);
		if(registeredImage == null){
			URL imageURL = MXUnitPlugin.getDefault().getBundle().getEntry(url);
			ImageDescriptor descriptor = ImageDescriptor.createFromURL(imageURL);
			registeredImage = descriptor.createImage();
			MXUnitPlugin.getDefault().getImageRegistry().put(url, registeredImage);
		}
		//return MXUnitPlugin.getImageDescriptor(PATH + image).createImage();
		return registeredImage;
		
	}
	
	public static ImageDescriptor getImageDescriptor(String image){
	    String url = PATH + image;
        //ensure it's in the registry
	    getImage(image);
	    return MXUnitPlugin.getDefault().getImageRegistry().getDescriptor(url);
	}

	public static void dispose() {
		MXUnitPlugin.getDefault().getImageRegistry().dispose();		
	}

}
