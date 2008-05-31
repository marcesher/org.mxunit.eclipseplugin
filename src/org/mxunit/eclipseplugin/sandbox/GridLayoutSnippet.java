package org.mxunit.eclipseplugin.sandbox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GridLayoutSnippet
{
   public static void main(String[] args) {
     Display display = new Display();
     Shell shell = new Shell(display);
     shell.setLayout(new FillLayout());

     Composite counterPanel = new Composite(shell, SWT.BORDER);
     GridLayout layout = new GridLayout(6, true);
     layout.horizontalSpacing = 5;
     layout.verticalSpacing = 0;
     counterPanel.setLayout(layout);

     Label runlabel = new Label(counterPanel, SWT.NONE);
     runlabel.setText("Col 1:");
     runlabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

     Text runs = new Text(counterPanel, SWT.READ_ONLY);
     runs.setText("Col 2");
     runs.setLayoutData(new GridData(GridData.BEGINNING
         | GridData.FILL_HORIZONTAL));

     Label errorlabel = new Label(counterPanel, SWT.NONE);
     errorlabel.setText("Col 3:");
     errorlabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

     Text errors = new Text(counterPanel, SWT.READ_ONLY);
     errors.setText("Col 4");
     errors.setLayoutData(new GridData(GridData.CENTER
         | GridData.FILL_HORIZONTAL));

     Label faillabel = new Label(counterPanel, SWT.NONE);
     faillabel.setText("Col 5:");
     faillabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

     Text fails = new Text(counterPanel, SWT.READ_ONLY);
     fails.setText("Col 6");
     fails.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));



     shell.pack();
     shell.open();
     while (!shell.isDisposed()) {
       if (!display.readAndDispatch())
         display.sleep();
     }
     display.dispose();
   }
}
