package org.mxunit.eclipseplugin.actions.tagcontextactions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.mxunit.eclipseplugin.model.FailureTrace;
import org.mxunit.eclipseplugin.views.MXUnitView;

public abstract class AbstractFailureTraceCopyAction extends Action {
	
	private MXUnitView view;
	private Clipboard clipboard;
	public AbstractFailureTraceCopyAction(MXUnitView view, Clipboard clipboard){
		this.view = view;
		this.clipboard = clipboard;
	}
	
	public void run(){		
		TextTransfer textTransfer = TextTransfer.getInstance();
		String input = getClipboardText();
		clipboard.setContents(new Object[] {input.trim()}, new Transfer[]{textTransfer});		
	}
	
	protected FailureTrace getTrace(){
		return (FailureTrace) view.getDetailsViewer().getSelection()[0].getData();
	}
	
	protected abstract String getClipboardText();
}
