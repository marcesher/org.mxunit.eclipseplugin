package org.mxunit.eclipseplugin.sandbox.progressbars;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressBarExamples {
  Display display = new Display();
  Shell shell = new Shell(display);

  public ProgressBarExamples() {
    init();
    
    ProgressBar pb1 = new ProgressBar(shell, SWT.NULL);
    final ProgressBar pb2 = new ProgressBar(shell, SWT.SMOOTH);
    ProgressBar pb3 = new ProgressBar(shell, SWT.INDETERMINATE);
    
//    pb2.addPaintListener(new PaintListener() {
//      public void paintControl(PaintEvent e) {
//        Point point = pb2.getSize();
//        
//        Font font = new Font(shell.getDisplay(),"Courier",10,SWT.BOLD);
//        e.gc.setFont(font);
//        e.gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
//        
//        FontMetrics fontMetrics = e.gc.getFontMetrics();
//        int stringWidth = fontMetrics.getAverageCharWidth() * 4;
//        int stringHeight = fontMetrics.getHeight();
//        
//        e.gc.drawString("60%", (point.x-stringWidth)/2 , (point.y-stringHeight)/2, true);
//        font.dispose();
//      }
//    });
    
    pb1.setSelection(88);
    pb2.setSelection(80);
    
    pb1.setBounds(100, 10, 200, 20);
    pb2.setBounds(100, 40, 200, 20);
    //pb3.setBounds(100, 70, 200, 20);
    
    Label label = new Label(shell, SWT.NULL);
    label.setText("(default)");
    Label label2 = new Label(shell, SWT.NULL);
    label2.setText("SWT.SMOOTH");
    
    label.setAlignment(SWT.RIGHT);
    label2.setAlignment(SWT.RIGHT);

    label.setBounds(10, 10, 80, 20);
    label2.setBounds(10, 40, 80, 20);
    
    shell.pack();
    shell.open();
    //textUser.forceFocus();

    // Set up the event loop.
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        // If no more entries in event queue
        display.sleep();
      }
    }

    display.dispose();
  }

  private void init() {

  }

  public static void main(String[] args) {
    new ProgressBarExamples();
  }
}