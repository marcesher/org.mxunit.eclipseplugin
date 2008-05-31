package org.mxunit.eclipseplugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/*
 * NOTE: every line of this was taken from the QualityEclipse plugins book
 */
public class MXUnitPluginLog {
	
		public static final boolean inDebug = Platform.inDebugMode();

	   /**
	    * Log the specified information.
	    * 
	    * @param message, a human-readable message, localized to the
	    *           current locale.
	    */
	   public static void logInfo(String message) {		  
		   logInfo(message,true);	      
	   }
	   
	   public static void logInfo(String message, boolean inDebugModeOnly){
		   if(!inDebugModeOnly || inDebug){
			   log(IStatus.INFO, IStatus.OK, message, null);
		   }
	   }

	   /**
	    * Log the specified error.
	    * 
	    * @param message, a human-readable message, localized to the
	    *           current locale.
	    * @param exception, a low-level exception, or <code>null</code>
	    *           if not applicable.
	    */
	  
	   public static void logError(String message, Throwable exception){		   
		log(IStatus.ERROR, IStatus.ERROR, message, exception);		   
		 
	   }
	   
	   public static void logWarning(String message){
		   logWarning(message, true);
	   }
	   
	   public static void logWarning(String message, boolean inDebugModeOnly){
		   if(!inDebugModeOnly || inDebug){
			   log(IStatus.WARNING, IStatus.WARNING, message, null);
		   }
	   }

	   /**
	    * Log the specified information.
	    * 
	    * @param severity, the severity; one of the following:
	    *           <code>IStatus.OK</code>,
	    *           <code>IStatus.ERROR</code>,
	    *           <code>IStatus.INFO</code>, or
	    *           <code>IStatus.WARNING</code>.
	    * @param pluginId. the unique identifier of the relevant
	    *           plug-in.
	    * @param code, the plug-in-specific status code, or
	    *           <code>OK</code>.
	    * @param message, a human-readable message, localized to the
	    *           current locale.
	    * @param exception, a low-level exception, or <code>null</code>
	    *           if not applicable.
	    */
	   public static void log(int severity, int code, String message,
	         Throwable exception) {

	      log(createStatus(severity, code, message, exception));
	   }

	   /**
	    * Create a status object representing the specified information.
	    * 
	    * @param severity, the severity; one of the following:
	    *           <code>IStatus.OK</code>,
	    *           <code>IStatus.ERROR</code>,
	    *           <code>IStatus.INFO</code>, or
	    *           <code>IStatus.WARNING</code>.
	    * @param pluginId, the unique identifier of the relevant
	    *           plug-in.
	    * @param code, the plug-in-specific status code, or
	    *           <code>OK</code>.
	    * @param message, a human-readable message, localized to the
	    *           current locale.
	    * @param exception, a low-level exception, or <code>null</code>
	    *           if not applicable.
	    * @return, the status object (not <code>null</code>).
	    */
	   public static IStatus createStatus(int severity, int code,
	         String message, Throwable exception) {

	      return new Status(severity, MXUnitPlugin.ID, code,
	            message, exception);
	   }

	   /**
	    * Log the given status.
	    * 
	    * @param status, the status to log.
	    */
	   public static void log(IStatus status) {
	      MXUnitPlugin.getDefault().getLog().log(status);
	   }

}
