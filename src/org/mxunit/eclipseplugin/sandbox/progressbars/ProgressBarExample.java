package org.mxunit.eclipseplugin.sandbox.progressbars;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class ProgressBarExample {

public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	ProgressBar bar = new ProgressBar (shell, SWT.SMOOTH);
	bar.setBounds (10, 10, 200, 32);
	shell.open ();
	for (int i=0; i<=bar.getMaximum (); i++) {
		try {Thread.sleep (100);} catch (Throwable th) {}
		bar.setSelection (i);
	}
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
}
}