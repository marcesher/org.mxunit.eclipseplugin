package org.mxunit.eclipseplugin.sandbox.progressbars;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class UpdateProgressBarFromAnotherThread {

  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    final ProgressBar bar = new ProgressBar(shell, SWT.SMOOTH);
    bar.setBounds(10, 10, 200, 32);
    shell.open();
    final int maximum = bar.getMaximum();
    new Thread() {
      public void run() {
        for (final int[] i = new int[1]; i[0] <= maximum; i[0]++) {
          try {
            Thread.sleep(100);
          } catch (Throwable th) {
          }
          if (display.isDisposed())
            return;
          display.asyncExec(new Runnable() {
            public void run() {
              if (bar.isDisposed())
                return;
              bar.setSelection(i[0]);
            }
          });
        }
      }
    }.start();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
