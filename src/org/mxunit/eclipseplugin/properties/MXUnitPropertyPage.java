package org.mxunit.eclipseplugin.properties;


import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.util.RemoteCallCreator;
import org.mxunit.eclipseplugin.preferences.MXUnitPreferenceConstants;

/**
 * Stores the few project-level mxunit settings
 * 
 * @author Marc Esher
 */
public class MXUnitPropertyPage extends PropertyPage implements
		IWorkbenchPropertyPage {

	//ye olde manager
	private MXUnitPropertyManager propManager;
	
	//field objects
	private StringFieldEditor facadeURLField;
	private StringFieldEditor componentRootField;
	private StringFieldEditor unameField;
	//private StringFieldEditor pwField;
	private Text pwText;

	private Button testUrlButton;

	private RemoteCallCreator fRemoteCallCreator;
	
	//labels
	private static final String URL_LABEL = "  URL:";
	private static final String COMPONENT_LABEL = "  cfc path:";
	
	
	public MXUnitPropertyPage() {
		propManager = new MXUnitPropertyManager();
	}

	/**
	 * all your mxunit project properties are belong to us
	 * @param parent
	 * @return the Control
	 */
	protected Control createContents(Composite parent) {
		/**/
		Composite panel = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		panel.setLayout(layout);
		GridData data = new GridData(GridData.FILL,GridData.FILL,true,false);
		data.grabExcessHorizontalSpace = true;
		
		fRemoteCallCreator = new RemoteCallCreator();
		panel.setLayoutData(data);
		
		addFacadeURL(panel);	
		addComponentRoot(panel);
		addAuthGroup(panel);
		testUrlButton = addButton(panel, "Test Facade URL",10,1);
		testUrlButton.addSelectionListener(fTestButtonListener);
		
		return panel;
	}


	/**
	 * add the listener to the help link text for opening the help page
	 * @param helpLink
	 */
	private void activateHelpContents(Link helpLink, final String htmlPath) {
		helpLink.addListener(
			SWT.Selection, new Listener(){					
				public void handleEvent(Event event){
					final IWorkbenchHelpSystem helpSystem = PlatformUI.getWorkbench().getHelpSystem();
					helpSystem.displayHelpResource("/org.mxunit.eclipseplugin/help/html/"+htmlPath);
					
				}
			}
		);
	}

	
	/**
	 * test the facade URL button listener
	 */

	private SelectionListener fTestButtonListener = new SelectionListener() {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			String facadeUrl = facadeURLField.getStringValue();
			String username = unameField.getStringValue();
			String password = pwText.getText();
			if(facadeUrl.length() < 4) {
				MessageDialog
				.openInformation(
						null,
						"No URL specified",
				"Um, there's no custom facade URL to test here, bro!  Let me set the default for ya...");
				facadeURLField.setStringValue( fRemoteCallCreator.determineURL( (IResource)getElement().getAdapter(IResource.class) ) );				
				facadeURLField.setFocus();
				return;
			}
			if (fRemoteCallCreator.canPingFacade(facadeUrl, username, password)) {
				MessageDialog
				.openInformation(
						null,
						"Ping facade URL Success",
				"Woohoo!  The ping succeeded!");
			}
		}
	};
	
	/**
	 * add the facade URL field to the page
	 * @param parent
	 */
	private void addFacadeURL(Composite parent){
		Composite composite = parent;
		Group group = new Group(composite,SWT.NONE);
		group.setText("Override Test Runner URL");
		
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		
		group.setLayoutData(gd);
		
		facadeURLField = new StringFieldEditor(MXUnitPreferenceConstants.P_PROJECT_FACADEURL, 
				URL_LABEL,  group);
		
		facadeURLField.setStringValue( propManager.getURLPropertyValue( (IResource)getElement().getAdapter(IResource.class) ) );
		
		addBlankLabel(group);
		String currentUrl = fRemoteCallCreator.determineURL( (IResource)getElement().getAdapter(IResource.class) );
		addDescriptionLabel(group,currentUrl + "\n\nUse this field to override the facade URL preference for\nthis resource.\n\nIf you're simply pointing to MXUnit on a different port, you can\nend the URL at the port (http://localhost:8888, for example)");
		addBlankLabel(group);
		Link helpLink = new Link(group,SWT.NONE);
		helpLink.setText("See <a>Help</a> for details\n");
		activateHelpContents(helpLink,"project-specific-remotefacade.html");
		
	}
	
	/**
	 * add the component root field to the page
	 * @param parent
	 */
	private void addComponentRoot(Composite parent){
		Composite composite = parent;	
		Group group = new Group(composite,SWT.NONE);
		group.setText("Component root");
		
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		gd.verticalIndent = 10;
		group.setLayoutData(gd);
		
		componentRootField = new StringFieldEditor(MXUnitPreferenceConstants.P_PROJECT_COMPONENTROOT,
				COMPONENT_LABEL,  group);
		componentRootField.setStringValue(  propManager.getComponentPropertyValue( (IResource)getElement().getAdapter(IResource.class) )  );
		
		addBlankLabel(group);
		Link helpLink = new Link(group,SWT.NONE);
		helpLink.setText("e.g. com.mybusiness.test " +
				"\n\nSee <a>Help</a> for details\n");
		activateHelpContents(helpLink,"cfcpath.html");
	}

	
	
	private void addAuthGroup(Composite parent){
		
		Group group = new Group(parent,SWT.NONE);
		group.setText("Authentication");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		gd.verticalIndent = 10;
		group.setLayoutData(gd);
		
		unameField = new StringFieldEditor(MXUnitPreferenceConstants.P_PROJECT_USERNAME, "  Username:", 20, group);
		//pwField = new StringFieldEditor(MXUnitPreferenceConstants.P_PROJECT_PASSWORD, "  Password:", 20, group);
		
		unameField.setStringValue(propManager.getUsernamePropertyValue( (IResource)getElement().getAdapter(IResource.class) ));
		//pwField.setStringValue(propManager.getPasswordPropertyValue( (IResource)getElement().getAdapter(IResource.class) ));
		
		addDescriptionLabel(group,"  Password: ");
		pwText = new Text(group,SWT.PASSWORD|SWT.SINGLE|SWT.BORDER);
		pwText.setText(propManager.getPasswordPropertyValue( (IResource)getElement().getAdapter(IResource.class) ));
		//pwText.setTextLimit(20);	
		
		GridData textGD = new GridData();
        textGD.widthHint = 120;
        pwText.setLayoutData(textGD);
		
		addBlankLabel(group);
		addDescriptionLabel(group,
				"Use the username and password fields to set credentials for accessing the\n" +
				"test runner URL; this should work for basic and digest authentication.\n" +
				"With NTLM, your mileage may vary.");
		
		addBlankLabel(group);
		Link helpLink = new Link(group,SWT.NONE);
		helpLink.setText("See <a>Help</a> for details\n");
		activateHelpContents(helpLink,"authentication.html");
		
	}
	
	private void addBlankLabel(Composite parent) {
		Label blank = new Label(parent,SWT.NONE);
		blank.setText("");
	}
	
	private void addDescriptionLabel(Composite parent, String text){
		Label label = new Label(parent,SWT.NONE);
		label.setText(text);
	}
	
	protected Button addButton(Composite parent, String labelText, int indentation, int horizontalSpan) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(labelText);
		button.setFont(parent.getFont());
		return button;
	}	
	/**
	 * Store these suckers
	 */
	public boolean performOk(){	
		IResource resource = (IResource)getElement().getAdapter(IResource.class);
		propManager.setURLPropertyValue(resource,facadeURLField.getStringValue().trim());
		propManager.setComponentPropertyValue(resource,componentRootField.getStringValue().trim());	
		propManager.setUsernamePropertyValue(resource,unameField.getStringValue().trim());
		propManager.setPasswordPropertyValue(resource,pwText.getText().trim());
		return super.performOk();
	}
	
	/**
	 * set the preferences to defaults. as of now, these are just empty
	 */
	public void performDefaults(){
		facadeURLField.setStringValue("");
		componentRootField.setStringValue("");
		unameField.setStringValue("");		
		pwText.setText("");
	}


}
