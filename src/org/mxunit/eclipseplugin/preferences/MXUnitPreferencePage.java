package org.mxunit.eclipseplugin.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
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
		addTimeoutGroup(container);
		addAuthGroup(container);
		addColorGroup(container);
		addTestOrderingGroup(container);
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
					helpSystem.displayHelpResource("/org.mxunit.eclipseplugin/help/html/intro.html");
					
				}
			}
		);
	}


	private void addURLGroup(Composite container) {
		Group urlGroup = new Group(container,SWT.NONE);		
		urlGroup.setText("Test Runner URL");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		gd.horizontalSpan = 2;
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
				+ "in the test runner.\n\n"
				+ "If you need your tests to run under the context of an Application.cfc,\n"
				+ "you will need to override the test runner URL. See the Help for details.");
		
		
		//have to modify the layout AFTER the addField calls because addField is monkeying with any preset layout junk we do
		GridLayout urlLayout = (GridLayout)urlGroup.getLayout();
		urlLayout.numColumns = 2;
	}
	
	private void addTimeoutGroup(Composite container){
		Group timeoutGroup = new Group(container,SWT.NONE);		
		timeoutGroup.setText("Method Timeout");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,false,false);	
		gd.horizontalSpan = 2;
		timeoutGroup.setLayoutData(gd);
		timeoutGroup.setLayout(new GridLayout(3,false));
		
		IntegerFieldEditor timeoutEditor = new IntegerFieldEditor(MXUnitPreferenceConstants.P_REMOTE_CALL_TIMEOUT, "  &Timeout (sec.):",timeoutGroup, 3);
		timeoutEditor.setValidRange(0, 180);
		addField(timeoutEditor);			
		
		Label empty = new Label(timeoutGroup, SWT.NONE);
		empty.setText("");
		
		Label timeoutLabel = new Label(timeoutGroup, SWT.NONE);
		timeoutLabel.setText("This controls the timeout, in seconds, of each single method call. Use 0 for unlimited.");
		
		
		//have to modify the layout AFTER the addField calls because addField is monkeying with any preset layout junk we do
		GridLayout timeoutLayout = (GridLayout)timeoutGroup.getLayout();
		timeoutLayout.numColumns = 2;
	}

	
	
	private void addAuthGroup(Composite container) {
		Group authGroup = new Group(container, SWT.NONE);
		authGroup.setText("Authentication");
		authGroup.setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,false));
		authGroup.setLayout(new GridLayout(2,false));
		
		
		Label unameLabel = new Label(authGroup, SWT.NONE);
		unameLabel.setText("Username and password for authentication mechanisms (basic, NTLM) can be set at the Project level only.\n" +
				"In the Navigator, right-click on a project, select 'Properties', select 'MXUnit', and add your authentication\n" +
				"credentials on that property page.");
	}
	
	private void addColorGroup(Composite container){
		Group colorGroup = new Group(container, SWT.NONE);
		colorGroup.setText("Colors");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		gd.horizontalSpan = 2;
		
		colorGroup.setLayoutData(gd);
		colorGroup.setLayout(new GridLayout(3,false));
		
		addField(new ColorFieldEditor(MXUnitPreferenceConstants.P_COLOR_PASS, " &Pass:", colorGroup));
		addField(new ColorFieldEditor(MXUnitPreferenceConstants.P_COLOR_FAIL, " &Fail:", colorGroup));
		addField(new ColorFieldEditor(MXUnitPreferenceConstants.P_COLOR_STOPPED, " &Stopped:", colorGroup));
		
	}
	
	private void addTestOrderingGroup(Composite container){
		Group testOrderingGroup = new Group(container, SWT.NONE);
		testOrderingGroup.setText("Test Ordering");
		GridData gd = new GridData(GridData.FILL,GridData.FILL,true,false);	
		gd.horizontalSpan = 2;
		testOrderingGroup.setLayoutData(gd);
		GridLayout layout = new GridLayout(3, false);
		layout.marginLeft = 5;
		testOrderingGroup.setLayout(layout);
		
		addField(new BooleanFieldEditor(MXUnitPreferenceConstants.P_TEST_ORDERING, " &Alphabetize Test Methods", testOrderingGroup));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}