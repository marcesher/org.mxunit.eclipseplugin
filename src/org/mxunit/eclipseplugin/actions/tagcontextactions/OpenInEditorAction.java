package org.mxunit.eclipseplugin.actions.tagcontextactions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.mxunit.eclipseplugin.MXUnitPluginLog;
import org.mxunit.eclipseplugin.model.FailureTrace;
import org.mxunit.eclipseplugin.views.MXUnitView;

public final class OpenInEditorAction extends Action {
	
	private MXUnitView view;
	private IWorkspace workspace = ResourcesPlugin.getWorkspace();
    private IWorkspaceRoot root = workspace.getRoot();
    
	public OpenInEditorAction(MXUnitView view){
		this.view = view;
	}
	
	public void run(){
		TableItem[] rows = view.getDetailsViewer().getSelection();
		if(rows == null || rows.length == 0){
			return;
		}
		
		Object data = rows[0].getData();
		IFile file;
		IPath path = null;
		int line = 1;
		
		if(data instanceof FailureTrace){
			FailureTrace trace = (FailureTrace)data;
			path = new Path(trace.getFilePath());
			line = trace.getFileLine();
		//this happens when a blank line is clicked
		}else{	
			System.out.println(data);
			System.out.println("don't know what to do with data");
			return;
		}
        
        try {        
            file = root.getFileForLocation(path);
            if(file==null){
                MessageDialog
                .openInformation(
                        null,
                        "File not in workspace",
                        "Could not open file named ["+path+"]. Most likely this file is not in any project in your workspace");
                return;
            }
            IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());        
            
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            Map map = new HashMap();
            map.put(IMarker.LINE_NUMBER, line);
            IMarker marker = file.createMarker(IMarker.TEXT);
            marker.setAttributes(map);
            IDE.openEditor(page,marker);      
            marker.delete();           
        
        } catch(Exception e){
        	System.out.println("inside error");
        	MXUnitPluginLog.logError("Exception opening file in OpenInEditorAction",e);
        }
	}
}
