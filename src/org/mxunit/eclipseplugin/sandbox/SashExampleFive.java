package org.mxunit.eclipseplugin.sandbox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SashExampleFive {
    
    public static void main (String [] args) {
        final Display display = new Display ();
        Shell shell = new Shell(display);
        shell.setLayout (new FillLayout());

        SashForm form = new SashForm(shell,SWT.VERTICAL);
        form.setLayout(new FillLayout());

        Composite child1 = new Composite(form,SWT.BORDER);
        child1.setLayout(new GridLayout(2,false));
        new Label(child1,SWT.BORDER).setText("Label in pane 1");
        Button button1 = new Button(child1,SWT.PUSH);
        button1.setText("Button 1");
        button1.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL |
    GridData.FILL_HORIZONTAL));

        Composite child2 = new Composite(form,SWT.BORDER);
        child2.setLayout(new GridLayout(2,false));
        new Label(child2,SWT.BORDER).setText("Label in pane 2");
        Button button2 = new Button(child2,SWT.PUSH);
        button2.setText("Button 2");
        button2.setLayoutData(new GridData(GridData.GRAB_VERTICAL |
    GridData.FILL_VERTICAL));

        Composite child3 = new Composite(form,SWT.BORDER);
        child3.setLayout(new GridLayout(2,false));
        new Label(child3,SWT.BORDER).setText("Label in pane3");
        Button button3 = new Button(child3,SWT.PUSH);
        button3.setText("Button 3");
        button3.setLayoutData(new GridData(80,80));   // <---

        form.setWeights(new int[] {30,40,30});
        shell.open ();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }

}
