package org.mxunit.eclipseplugin.actions.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * This class uses a web browser to display html
 */
public class HTMLViewer {
  /**
   * Runs the application
   */
  public void display(String html) {
    Display display = PlatformUI.getWorkbench().getDisplay();
    
    Shell shell = new Shell(display,SWT.APPLICATION_MODAL | SWT.CLOSE);
    shell.setText("Server Response");
    createContents(shell, html);
    shell.open();
    shell.setFocus();
    
  }
  

  /**
   * Creates the main window's contents
   * 
   * @param shell the main window
 * @param html 
   */
  private void createContents(Shell shell, String html) {
    shell.setLayout(new FillLayout());

    // Create a web browser
    Browser browser = new Browser(shell, SWT.NONE);
    browser.setText(html);
  }

}