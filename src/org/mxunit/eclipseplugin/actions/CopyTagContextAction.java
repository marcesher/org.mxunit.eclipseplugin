package org.mxunit.eclipseplugin.actions;

import org.eclipse.swt.dnd.Clipboard;
import org.mxunit.eclipseplugin.views.MXUnitView;

public class CopyTagContextAction extends AbstractFailureTraceCopyAction {

	public CopyTagContextAction(MXUnitView view, Clipboard clipboard) {
		super(view, clipboard);
	}

	@Override
	protected String getClipboardText() {
		return getTrace().getMethod().getTagcontextAsString();
	}

}
