package org.mxunit.eclipseplugin.actions.searchactions;


import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.actions.TestLoadAction;
import org.mxunit.eclipseplugin.actions.TestSuiteCreator;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * pops up the search box and loads the selected resource as a test suite into the mxunit view
 * @author Marc Esher *
 */
public final class ComponentSearchAction extends Action {
	
	private MXUnitView view;
	private TestLoadAction loadAction;
	
	
	public ComponentSearchAction(MXUnitView view){
		this.view = view;
		loadAction = new TestLoadAction(view,false);
	}
	
	public void run(){						
		
		ResourceListSelectionDialog selection = null;
		
		try {
			selection = new ResourceListSelectionDialog(
				view.getSite().getShell(),
				ResourcesPlugin.getWorkspace().getRoot(), 
				IResource.FILE | IResource.FOLDER | IResource.PROJECT
			);
			
		} catch (Exception e) {
			MXUnitPluginLog.logError("Exception in ComponentSearchAction",e);
		}		
		selection.setTitle("Find Test or Test Directory");	
		
		if(selection.open() == Window.OK) {
			TestSuiteCreator testSuiteCreator = new TestSuiteCreator();	       
			Object[] result = selection.getResult();			
			IResource res = (IResource)result[0];//safe since we only allow single selection			
						
			if(res.getType() == IResource.FILE && !res.getFileExtension().equalsIgnoreCase("cfc")){
				MessageDialog.openInformation( null, "Whooops....not a CFC", "Selected file ["+ res.getRawLocation() +"] is not a CFC");
			}else{
				IResource[] resources = new IResource[result.length];
				for (int i = 0; i < result.length; i++) {
					resources[i] = (IResource) result[i];
					System.out.println("Adding resource to test suite: " + resources[i].getFullPath());
				}
				
				TestSuite suite = testSuiteCreator.createSuite(resources);
				view.getTestsViewer().setInput(suite);
                view.getTestsViewer().getTree().selectAll();      
                view.enableActions();
                loadAction.run();                
			}		
			
		}
	}
}
