package org.mxunit.eclipseplugin.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.mxunit.eclipseplugin.MXUnitPlugin;



/**
 * Preferences for MXUnit Plugin;
 * @author Marc Esher
 */

public class MXUnitPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public MXUnitPreferencePage() {
		super(GRID);
		setPreferenceStore(MXUnitPlugin.getDefault().getPreferenceStore());
		setDescription("MXUnit Preferences");		
	}
	
	
	/**
	 * setusup some preference fields
	 */
	public void createFieldEditors() {
		//set up the container
		Composite container = createContainer();
		
		addHelpLink(container);	
		addURLGroup(container);	
		addWebrootGroup(container);	
		addAuthGroup(container);
	}


	private Composite createContainer() {
		Composite container = new Composite(this.getFieldEditorParent(),SWT.NONE);
		
		GridData containerData = new GridData(GridData.FILL,GridData.FILL,true,false);
		containerData.horizontalSpan = 1;		
		container.setLayoutData(containerData);
		
		GridLayout layout = new GridLayout(1,false);
		container.setLayout(layout);
		return container;
	}


	private void addHelpLink(Composite container) {
		Link helpLink = new Link(container,SWT.NONE);
		helpLink.setText("View <a>MXUnit Help</a>\n");
		helpLink.addListener(
			SWT.Selection, new Listener(){					
				public void handleEvent(Event event){
					final IWorkbenchHelpSystem helpSystem = PlatformUI.getWorkbench().getHelpSystem();
					helpSystem.displayHelpResource("/org.mxunit.eclipseplugin/help/html/configureGlobalPreferences.html");
					
				}
			}
		);
	}


	private void addURLGroup(Composite container) {
		Group urlGroup = new Group(container,SWT.NONE);		
		urlGroup.setText("Test Runner URL");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		gd.horizontalSpan = 3;
		urlGroup.setLayoutData(gd);
		urlGroup.setLayout(new GridLayout(3,false));
		
		StringFieldEditor urlEditor = new StringFieldEditor(MXUnitPreferenceConstants.P_FACADEURL, "  &URL to remote facade:",60, urlGroup);
		addField(urlEditor);		
		
		Label empty = new Label(urlGroup, SWT.NONE);
		empty.setText("");
		
		Label urlLabel = new Label(urlGroup, SWT.NONE);
		urlLabel.setText("Example: http://localhost/mxunit/framework/RemoteFacade.cfc"
				+ "\n\nYou can override this setting at the project level if necessary. This\n"
				+ "is useful when you have multiple environments for the same project,\n"
				+ "e.g. local, dev, staging, etc. or need to provide custom functionality\n" 
				+ "in the test runner.\n\n");
				
		//have to modify the layout AFTER the addField calls because addField is monkeying with any preset layout junk we do
		GridLayout urlLayout = (GridLayout)urlGroup.getLayout();
		urlLayout.numColumns = 3;
	}


	

	private void addWebrootGroup(Composite container) {
		Group webrootGroup = new Group(container, SWT.NONE);
		webrootGroup.setText("Your web root");
		GridData gd3 = new GridData(GridData.FILL,GridData.FILL,false,false);
		gd3.horizontalSpan = 3;
		webrootGroup.setLayoutData(gd3);
		webrootGroup.setLayout(new GridLayout(3,false));		
		
		DirectoryFieldEditor webrootDir = new DirectoryFieldEditor(MXUnitPreferenceConstants.P_WEBROOTPATH, 
				"  &Web root directory:",  webrootGroup);		
		addField(webrootDir);
				
		Label empty3 = new Label(webrootGroup, SWT.NONE);
		empty3.setText("");

		Label webrootLabel = new Label(webrootGroup, SWT.NONE);
		webrootLabel.setText("Example: C:\\inetpub\\wwwroot" 
				+ "\n\nSetting this makes it easier to determine the component path for your unit test CFCs. You can\n"
				+ "ignore the webroot at the project level by setting the component root in the project properties. This\n"
				+ "is useful for projects whose test CFCs live outside the webroot. The MXUnit plugin will attempt to\n"
				+ "derive the component root in this order:\n\n"
				+ "  1. Project Properties ---> MXUnit --> component root\n"
				+ "  2. MXUnit Preferences ---> web root directory\n\n"
				+ "If all your test components live under the webroot, simply set the webroot here and forget the rest.");
	}
	
	
	private void addAuthGroup(Composite container) {
		Group authGroup = new Group(container, SWT.NONE);
		authGroup.setText("Authentication");
		authGroup.setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,false));
		authGroup.setLayout(new GridLayout(3,false));
		
		
		Label unameLabel = new Label(authGroup, SWT.NONE);
		unameLabel.setText("Username and password for authentication mechanisms (basic, NTLM) can be set at the Project level only.\n" +
				"In the Navigator, right-click on a project, select 'Properties', select 'MXUnit', and add your authentication\n" +
				"credentials on that property page.");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}