package org.mxunit.eclipseplugin.sandbox;

import org.eclipse.compare.CompareUI;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mxunit.eclipseplugin.model.ResultCompareInput;
import org.mxunit.eclipseplugin.model.ResultCompareItem;

public class CompareEditorExample {
	public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setBounds(500,500,500,500);
        shell.setLayout(new FillLayout());
      
        
        ResultCompareItem expected = new ResultCompareItem("expected","1.0");
        ResultCompareItem actual = new ResultCompareItem("expected","2.0");
        ResultCompareInput input = new ResultCompareInput(expected,actual);
        
        CompareUI.openCompareDialog(input);
        
        
        
        shell.open();
        while(!shell.isDisposed()){
            if(!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
