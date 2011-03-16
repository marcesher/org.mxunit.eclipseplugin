package org.mxunit.eclipseplugin;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.mxunit.eclipseplugin.messages";//$NON-NLS-1$

	public static String IDE_noFileEditorFound;

	public static String OpenInEditorAction_title;
	public static String OpenInEditorAction_message_fileNotFound;
	public static String OpenInEditorAction_message_filesNotFound;
	public static String OpenInEditorAction_message_errorOnOpen;
	public static String OpenInEditorAction_title_selectWorkspaceFile;
	public static String OpenInEditorAction_message_fileLinkedToMultiple;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
