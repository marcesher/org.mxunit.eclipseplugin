package org.mxunit.eclipseplugin.views;

import java.io.IOException;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.eclipse.ui.part.ViewPart;
import org.mxunit.eclipseplugin.actions.BrowserAction;
import org.mxunit.eclipseplugin.actions.ComponentSearchAction;
import org.mxunit.eclipseplugin.actions.LoadMethodsAction;
import org.mxunit.eclipseplugin.actions.OpenInEditorAction;
import org.mxunit.eclipseplugin.actions.RunFailuresOnlyAction;
import org.mxunit.eclipseplugin.actions.RunTestsAction;
import org.mxunit.eclipseplugin.actions.SelectAllInTreeAction;
import org.mxunit.eclipseplugin.actions.SpoofChangeModelAction;
import org.mxunit.eclipseplugin.actions.ToggleTreeItemsAction;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestElementType;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestStatus;

/**
 * The view, baby.
 */

public class MXUnitView extends ViewPart {

	public static final String ID = "org.mxunit.eclipseplugin.views.MXUnitView";

	TreeViewer testsViewer;
	private Table detailsViewer;
	private Text runs;
	private Text fails;
	private Text errors;
	private int totalCount;
	private int errorCount;
	private int failureCount;
	private int currentCount;
	private long viewRunID = 0;
	
	private JUnitProgressBar progressBar;

    private LoadMethodsAction loadMethodsAction;
	private RunTestsAction runTestsAction;
	private RunFailuresOnlyAction runFailuresOnlyAction;
	private ToggleTreeItemsAction toggleTreeItemsAction;
	private ComponentSearchAction componentSearchAction;
	private BrowserAction browserAction;	
	private SelectAllInTreeAction selectAllAction;
	private OpenInEditorAction openInEditorAction;
	private SpoofChangeModelAction spoofChangeModelAction;
	private Action stopAction;
	private Action helpAction;
	
	private MessageConsole console;
	private boolean consoleActivated = false;
	
	//private TestObserver testObserver = new TestObserver(); 

	/**
	 * The constructor.
	 */
	public MXUnitView() {	   
	    initializeConsole();
    }
	
	/**
	 * the destructor
	 */
	public void dispose(){
		ResourceManager.dispose();
	}
	/**
	 * set us up a console
	 */
	private void initializeConsole(){
	    IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager().getConsoles();
	    //This could happen if the subversion console, for example, is first on the console stack
	    if(consoles.length==0  ||  !(consoles[0] instanceof MessageConsole) ){
            console = new MessageConsole("MXUnit Console", null);
            ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ console });
        }else{
            console = (MessageConsole) consoles[0];
        }
	}
	
	/**
	 * the "guts" of the view
	 */
	public void createPartControl(Composite parent) {		
		GridLayout layout = new GridLayout();
		layout.marginWidth = 1;
		layout.numColumns = 1;
		parent.setLayout(layout);
		
		//the sash is the separator between the top and the bottom; it creates the resizer
		//the sash is the parent for the topHalf and bottomHalf composites; those composites
		//are the parents for the widgets that go in them
		SashForm sash = new SashForm(parent, SWT.VERTICAL);
		GridData sashData = new GridData(GridData.FILL_BOTH);
		sashData.horizontalSpan = 1;
		sash.setLayoutData(sashData);
		sash.setLayout(new FillLayout());
		
		//create top layout
		GridLayout topLayout = new GridLayout();
		topLayout.marginWidth = 0;
		topLayout.marginHeight = 0;
		//create top container
		Composite topHalf = new Composite(sash,SWT.BORDER);
		topHalf.setLayout(topLayout);
		topHalf.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//create bottom layout
		GridLayout bottomLayout = new GridLayout();
		bottomLayout.marginWidth = 0;
		bottomLayout.marginHeight = 0;
		//create bottom container
		Composite bottomHalf = new Composite(sash,SWT.BORDER);
		bottomHalf.setLayout(bottomLayout);
		bottomHalf.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//now create the widgets that go into each half
		createCounterPanel(topHalf);
		createProgressPanel(topHalf);
		createTestsViewer(topHalf);
		createTraceLabel(bottomHalf);
		createDetailsViewer(bottomHalf);
				
		//now make the view actually do stuff
		makeActions();
		createContextMenu();
		createDetailsContextMenu();
		hookDoubleClickAction();
		hookKeyboardActions();
		contributeToActionBars();
		setHelpContextIDs();
		
	}

	/**
	 * creates the runs/failures/error panel
	 */
	private void createCounterPanel(Composite parent) {
		Composite counterPanel = new Composite(parent, SWT.NONE);
		counterPanel.setLayout(new FillLayout());
		counterPanel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridLayout layout = new GridLayout();
		layout.numColumns = 6;		
		layout.horizontalSpacing = 2;
		layout.verticalSpacing = 0;
		counterPanel.setLayout(layout);

		Label runlabel = new Label(counterPanel, SWT.NONE);
		runlabel.setText("Runs:");
		runlabel.setLayoutData(new GridData());

		runs = new Text(counterPanel, SWT.READ_ONLY);
		runs.setLayoutData(new GridData(GridData.BEGINNING
				| GridData.FILL_HORIZONTAL));

		Label errorlabel = new Label(counterPanel, SWT.NONE);
		errorlabel.setText("Errors:");
		errorlabel.setLayoutData(new GridData());

		errors = new Text(counterPanel, SWT.READ_ONLY);
		errors.setLayoutData(new GridData(GridData.CENTER
				| GridData.FILL_HORIZONTAL));

		Label faillabel = new Label(counterPanel, SWT.NONE);
		faillabel.setText("Failures:");
		faillabel.setLayoutData(new GridData());

		fails = new Text(counterPanel, SWT.READ_ONLY);
		fails.setLayoutData(new GridData(GridData.CENTER
				| GridData.FILL_HORIZONTAL));	
		
		setCounterText();
	}
	
	protected Composite createProgressPanel(Composite parent) {		
		
		Composite composite= new Composite(parent, SWT.NONE);
		FillLayout layout= new FillLayout();
		layout.marginWidth = 3;
		composite.setLayout(layout);		
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		progressBar = new JUnitProgressBar(composite);
		
		return parent;		
	}

	/**
	 * Creates the tests tree
	 * 
	 * @param parent
	 */
	private void createTestsViewer(Composite parent) {
		// Tests tree
		Composite testsPanel = new Composite(parent, SWT.BORDER);
		testsPanel.setLayout(new FillLayout());
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 1;
		gridData.minimumHeight = 200;
		
		testsPanel.setLayoutData(gridData);

		testsViewer = new TreeViewer(testsPanel, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		// new DrillDownAdapter(testsViewer);
		testsViewer.setContentProvider(new TestListContentProvider());
		testsViewer.setLabelProvider(new TestListLabelProvider());
		testsViewer.setSorter(new TestListNameSorter());
		testsViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {						
						updateDetailsPanel();
					}
				});
		
	}

	/**
	 * this here thing creates the tiny little label in between the tests viewer and the details panel
	 * @param parent
	 */
	private void createTraceLabel(Composite parent) {
		Label traceLabel = new Label(parent, SWT.NONE);
		traceLabel.setText("Trace");
		traceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	/**
	 * creates the details panel
	 * @param parent
	 */
	private void createDetailsViewer(Composite parent) {
		Composite detailsPanel = new Composite(parent, SWT.BORDER);
		detailsPanel.setLayout(new FillLayout());
		GridData detailsData = new GridData(GridData.FILL_BOTH);
		//detailsData.grabExcessVerticalSpace = false;
		detailsData.minimumHeight=300;
		detailsPanel.setLayoutData(detailsData);
		detailsViewer = new Table(detailsPanel, SWT.SINGLE | SWT.FULL_SELECTION);
	}

	/**
	 * for updating the contents of the details viewer. public because this is used in certain view actions
	 * 
	 */
	public void updateDetailsPanel() {
		TreeItem[] items = testsViewer.getTree().getSelection();
		clearDetailsPanel();
		for (int i = 0; i < items.length; i++) {
			ITest testitem = (ITest) items[i].getData();
			if (testitem.getTestElementType() == TestElementType.TESTMETHOD) {
				TestMethod method = (TestMethod) testitem;
				if(method.getResult().trim().length()>0){
				    TableItem nameRow = newTableItem();
				
    				nameRow.setData(method);
    				nameRow.setText(method.getName());
    				detailsViewer.showItem(nameRow);
    				TableItem resultRow = newTableItem();
                    resultRow.setData(method);
    				resultRow.setText(method.getException());  
    				if(method.getStatus() == TestStatus.ERROR){
    				    resultRow.setImage(ResourceManager.getImage(ResourceManager.CIRCLE_ERROR));
    				}else{
    				    resultRow.setImage(ResourceManager.getImage(ResourceManager.CIRCLE_FAIL));
    				}
    				Map[] tc = method.getTagcontext();
    				if(tc != null){
    					//this way, the OpenInEditorAction can be stupid about opening up at the correct file line
                        nameRow.setData(tc[0]);
                        resultRow.setData(tc[0]);
                        
    				    for (int j = 0; j < method.getTagcontext().length; j++) {
    				        String fileName = (String) tc[j].get("FILE");
    				        //System.out.println(tc[j].get("LINE"));
    				        String fileLine = (String) Integer.toString((Integer)tc[j].get("LINE"));
                            TableItem traceRow = newTableItem();
                            traceRow.setData(tc[j]);
                            
                            if(fileName.toLowerCase().endsWith(".cfc")){
                                traceRow.setImage( ResourceManager.getImage(ResourceManager.CFCSTACKFRAME) );
                            }else{
                                traceRow.setImage( ResourceManager.getImage(ResourceManager.CFMSTACKFRAME) );
                            }                            
                            traceRow.setText(fileName + ": " + fileLine);
                            detailsViewer.showItem(traceRow);
                        }
    				}
    				
    				//little spacer
    				if (items.length > 1) {
    				    newTableItem().setText("");
    				}
				}
			}
		}		
		
		
		
	}

	/**
	 * resets the details table
	 */
	private void clearDetailsPanel() {
		detailsViewer.removeAll();
	}

	/**
	 * create the actions to contribute to the bars, context menu, etc
	 * 
	 */
	private void makeActions() {
        
        loadMethodsAction = new LoadMethodsAction(this);
        loadMethodsAction.setText("Load Methods");
        loadMethodsAction.setToolTipText("Load methods for Test (F5)");
        loadMethodsAction.setImageDescriptor(
                ResourceManager.getImageDescriptor(ResourceManager.REFRESH)
        );        
        
		runTestsAction = new RunTestsAction(this);
		runTestsAction.setText("Run");
		runTestsAction.setToolTipText("Run selected Tests (Enter)");		
		runTestsAction.setImageDescriptor(
		        ResourceManager.getImageDescriptor(ResourceManager.RUN)
		);
		
		runFailuresOnlyAction = new RunFailuresOnlyAction(this);
		runFailuresOnlyAction.setText("Run Failures Only");
		runFailuresOnlyAction.setToolTipText("Run Failures Only");
		runFailuresOnlyAction.setImageDescriptor(
		        ResourceManager.getImageDescriptor(ResourceManager.RUNFAILURES)
		);
		runFailuresOnlyAction.setEnabled(false);

		toggleTreeItemsAction = new ToggleTreeItemsAction(this);
		toggleTreeItemsAction.setText("Expand/Collapse All");
		toggleTreeItemsAction.setToolTipText("Expand/Collapse all Tree Items (Ctrl-+)");
		toggleTreeItemsAction.setImageDescriptor(
		        ResourceManager.getImageDescriptor(ResourceManager.EXPANDCOLLAPSEALL)
		);

		componentSearchAction = new ComponentSearchAction(this);
		componentSearchAction.setText("Search for Tests (Ctrl-F)");
		componentSearchAction.setImageDescriptor(
		        ResourceManager.getImageDescriptor(ResourceManager.FIND)
		);
		
		browserAction = new BrowserAction(this);
		browserAction.setText("Open test case results in browser (b or F8)");
		browserAction.setImageDescriptor(
		        ResourceManager.getImageDescriptor(ResourceManager.BROWSER)
		);
		
		selectAllAction = new SelectAllInTreeAction(this);
		selectAllAction.setText("Select all Tests (Ctrl-A)" +
				"");
		
		openInEditorAction = new OpenInEditorAction(this);
		openInEditorAction.setText("Open file");
		
		spoofChangeModelAction = new SpoofChangeModelAction(testsViewer.getTree());
		spoofChangeModelAction.setText("Spoof");
		
		stopAction = new Action() {
            public void run() {
               stop();
            }
        };
        stopAction.setText("Stop after the currently executing method");
        stopAction.setImageDescriptor(
                ResourceManager.getImageDescriptor(ResourceManager.STOP)
        );
        stopAction.setEnabled(false);
        
        final IWorkbenchHelpSystem helpSystem = getSite().getWorkbenchWindow().getWorkbench().getHelpSystem();
        helpAction = new Action(){
        	public void run(){
        		helpSystem.displayHelp("org.mxunit.eclipseplugin.mxunit_view");
        	}
        };
        helpAction.setText("Open Help (F1)");
        helpAction.setImageDescriptor(
                ResourceManager.getImageDescriptor(ResourceManager.HELP)
        );
	}
	
	private void setHelpContextIDs(){
		IWorkbenchHelpSystem helpSystem = getSite().getWorkbenchWindow().getWorkbench().getHelpSystem();
		helpSystem.setHelp(testsViewer.getControl(), "org.mxunit.eclipseplugin.mxunit_view");
	}

	/**
	 * adds the actions to the toolbar and the dropdown
	 * 
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		//fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	/**
	 * the little dropdown on the far right of the toolbar
	 * 
	 * @param manager
	 
	private void fillLocalPullDown(IMenuManager manager) {
        manager.add(loadMethodsAction);
        manager.add(new Separator());
        manager.add(runTestsAction);
		manager.add(new Separator());
		manager.add(toggleTreeItemsAction);
	}*/

	/**
	 * toolbar actions
	 * 
	 * @param manager
	 *            the toolbar manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(componentSearchAction);
		manager.add(new Separator());
        manager.add(loadMethodsAction);
		manager.add(runTestsAction);
		manager.add(runFailuresOnlyAction);
		manager.add(stopAction);
		manager.add(toggleTreeItemsAction);
		
		//manager.add(spoofChangeModelAction);
		manager.add(new Separator());
		manager.add(helpAction);
		manager.add(new Separator());
		// drillDownAdapter.addNavigationActions(manager);
	}

	/**
	 * creates the context menu; calls fillContextMenu to fill it *
	 */
	protected void createContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				MXUnitView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(testsViewer.getControl());
		testsViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, testsViewer);
	}
	
	protected void createDetailsContextMenu(){
	    MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager manager) {
                MXUnitView.this.fillDetailsContextMenu(manager);
            }
        });       
        Menu menu = menuMgr.createContextMenu(detailsViewer);
        detailsViewer.setMenu(menu);
	}

	/**
	 * populates the context menu
	 * 
	 * @param manager
	 */
	void fillContextMenu(IMenuManager manager) {
		
        manager.add(loadMethodsAction);
		manager.add(runTestsAction);
		manager.add(runFailuresOnlyAction);
		manager.add(browserAction);
		manager.add(selectAllAction);
		//manager.add(spoofChangeModelAction);
		manager.add(new Separator());
		manager.add(helpAction);
		manager.add(new Separator());
		// Other plug-ins can contribute their actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	void fillDetailsContextMenu(IMenuManager manager){
	    manager.add(openInEditorAction);
	}

	private void hookDoubleClickAction() {
		testsViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				runTestsAction.run();
			}
		});
		
		detailsViewer.addMouseListener(new MouseAdapter(){
			public void mouseDoubleClick(MouseEvent e){
				openInEditorAction.run();
			}
		});
	}
	
	private void hookKeyboardActions(){
	    testsViewer.getControl().addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent event){
                    handleKeyPressed(event);
                }
            }
	    );
	    
	    detailsViewer.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent event){
                    handleDetailKeyPressed(event);
                }
            }
        );
	}
	
	protected void handleKeyPressed(KeyEvent event) {
	    boolean ctrlPressed = (event.stateMask & SWT.CTRL) != 0;	
	    //CTRL-a: select all test cases
	    //97 is the keycode for 'a'	   
        if( ctrlPressed && event.keyCode == (int)'a' ){
            selectAllAction.run();
        }
        //refresh on F5
        else if(event.keyCode == SWT.F5){
            loadMethodsAction.run();
        }
        //these are the plus or minus characters. doesn't matter if ctrl is down
        else if(event.character == '=' || event.character == '-'){
            toggleTreeItemsAction.run();
        }
        //CTRL-f (102 is f): pop up the search box
        else if(ctrlPressed && event.keyCode == (int)'f'){
            componentSearchAction.run();
        }
        //CTRL-b or F8, open stuff in browser
        else if( (event.keyCode == (int)'b') || event.keyCode == SWT.F8 ){
        	browserAction.run();
        }
        
        //Enter or 'r' ('r' at bill's request so he didn't have to take his hands off the mouse)
        else if(!ctrlPressed && event.keyCode == (int)'r'){
        	runTestsAction.run();
        }
        
        //ctrl-r for run failures
        else if( runFailuresOnlyAction.isEnabled() && ctrlPressed && (event.keyCode == (int)'r' || event.character == '\r') ){
            runFailuresOnlyAction.run();
        }
        
        /*System.out.println(ctrlPressed);
        System.out.println(Integer.toHexString(event.character));
        System.out.println(event.keyCode + " " + event.character);*/
        
    }
	
	protected void handleDetailKeyPressed(KeyEvent event) { 
        if( event.character == '\r' ){
            openInEditorAction.run();
        }        
    }

	void showMessage(String message) {
		MessageDialog.openInformation(null, "MXUnit", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		testsViewer.getControl().setFocus();
	}

	/**
	 * make it easy for actions to act on our tree
	 */
	public TreeViewer getTestsViewer() {
		return testsViewer;
	}
	
	/**
	 * make it easy for actions to act with our trace table
	 * 
	 */
	public Table getDetailsViewer(){
	    return detailsViewer;
	}

	/**
	 * convenience for adding rows to the details table
	 * 
	 * @return TableItem a new table item (row)
	 */
	TableItem newTableItem() {
		return new TableItem(detailsViewer, SWT.NONE);
	}
	
	public void writeToConsole(String output){
		if(!consoleActivated){
			console.activate();
			consoleActivated = true;
		}
	    
	    MessageConsoleStream stream = console.newMessageStream();
        stream.println(output);
        try {
            stream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}	
	
	public void resetCounts(final int total){	
		totalCount = total;
		errorCount = 0;
		failureCount = 0;
		currentCount = 0;
		progressBar.getDisplay().asyncExec(
			new Runnable(){
				public void run() {
					progressBar.reset();
					progressBar.setMaximum(total);
					setCounterText();
				}            			
    		}
		);	
		
	}
	
	public void addTestResult(TestStatus status){		
		currentCount++;
		if(status==TestStatus.PASS){
			progressBar.step(0);			
		}else{
			progressBar.step(1);
			if(status==TestStatus.FAIL){
				failureCount++;
			}else if(status==TestStatus.ERROR){
				errorCount++;
			}
		}
		setCounterText();		
	}
	
	private void setCounterText(){
		runs.setText(currentCount + "/" + totalCount);
		fails.setText(""+failureCount);
		errors.setText(""+errorCount);
	}
	
	public void disableActions(){
		runTestsAction.setEnabled(false);
		loadMethodsAction.setEnabled(false);
		runFailuresOnlyAction.setEnabled(false);
		stopAction.setEnabled(true);
	}
	public void enableActions(){
		runTestsAction.setEnabled(true);
		loadMethodsAction.setEnabled(true);
		if(failureCount>0 || errorCount>0){
			runFailuresOnlyAction.setEnabled(true);
		}		
		stopAction.setEnabled(false);
	}
	
	/**
	 * An extremely weak implementation of stop functionality. The actions will look
	 * at isStopped and quit their loop when they can. But it's unbelievably
	 * inelegant.... it doesn't attempt to shut down currently executing threads
	 * or anything. So a long running test method will still continue to run on the 
	 * CF Server. Really, what this does is enable the action buttons again. That's
	 * its main utility
	 */
	public void stop(){
	    viewRunID = 0;
	    writeToConsole("Stopping... If a test method is currently executing, it will continue to run on your CF server, but the results will be ignored. No other selected methods will be run.");
	    progressBar.stopped();
	    enableActions();
	}	
	
	public void setRunID(long ID){
	    viewRunID = ID;
	}
	public long getRunID(){
	    return viewRunID;
	}
	
}