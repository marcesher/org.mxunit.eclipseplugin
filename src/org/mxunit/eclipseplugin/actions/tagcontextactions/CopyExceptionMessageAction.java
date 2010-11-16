package org.mxunit.eclipseplugin.actions.tagcontextactions;

import org.eclipse.swt.dnd.Clipboard;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class CopyExceptionMessageAction extends AbstractFailureTraceCopyAction {
	
	public CopyExceptionMessageAction(MXUnitView view, Clipboard clipboard) {
		super(view, clipboard);
	}
	
	@Override
	protected String getClipboardText() {
		return getTrace().getMethod().getException();
	}
}
