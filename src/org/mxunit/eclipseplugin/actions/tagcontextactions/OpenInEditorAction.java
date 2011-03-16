package org.mxunit.eclipseplugin.actions.tagcontextactions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
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
import org.eclipse.ui.PartInitException;
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
			OpenInEditorHandler.handleOpenRequest(path.toOSString() +"|" + line, path.toOSString(), line, null, true);
        
        } catch(Exception e){
        	System.out.println("inside error");
        	MXUnitPluginLog.logError("Exception opening file in OpenInEditorAction",e);
        }
	}
}
