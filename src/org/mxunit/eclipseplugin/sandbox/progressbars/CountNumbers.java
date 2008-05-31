package org.mxunit.eclipseplugin.sandbox.progressbars;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class CountNumbers {
  Display display = new Display();
  Shell shell = new Shell(display);

  Button button;
  ProgressBar progressBar;
  
  public CountNumbers() {
    GridLayout gridLayout = new GridLayout(1, true);
    shell.setLayout(gridLayout);
    
    button = new Button(shell, SWT.BORDER);
    button.setText("Start to count");    
    
    progressBar = new ProgressBar(shell, SWT.SMOOTH);
    progressBar.setMinimum(0);
    progressBar.setMaximum(10);
    
    
    final Thread countThread = new Thread(){
      public void run() {
        for(int i=0; i<=10; i++) {
          final int num = i;
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          
          shell.getDisplay().asyncExec(new Runnable(){
            public void run() {
              if(button.isDisposed() || progressBar.isDisposed())
                return;
              button.setText("Counting: " + num);
              progressBar.setSelection(num);
              //progressBar.redraw();
            }
          });
        }
      }
    };
    
    button.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        button.setEnabled(false);
        countThread.start();
      }
    });
    
    progressBar.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        System.out.println("PAINT");
        // string to draw. 
        String string = (progressBar.getSelection() * 1.0 /(progressBar.getMaximum()-progressBar.getMinimum()) * 100) + "%";
        
        Point point = progressBar.getSize();
        Font font = new Font(shell.getDisplay(),"Courier",10,SWT.BOLD);
        e.gc.setFont(font);
        e.gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        
        FontMetrics fontMetrics = e.gc.getFontMetrics();
        int stringWidth = fontMetrics.getAverageCharWidth() * string.length();
        int stringHeight = fontMetrics.getHeight();
        
        e.gc.drawString(string, (point.x-stringWidth)/2 , (point.y-stringHeight)/2, true);
        font.dispose();
      }
    });
  
    button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    progressBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    shell.setSize(300, 100);
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
    new CountNumbers();
  }
}