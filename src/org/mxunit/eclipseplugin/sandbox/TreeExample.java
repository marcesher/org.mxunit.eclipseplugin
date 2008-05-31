package org.mxunit.eclipseplugin.sandbox;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class TreeExample {
    
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setBounds(500,500,500,500);
        shell.setLayout(new FillLayout());
        final Tree tree = new Tree(shell,SWT.MULTI);
        for(int i = 1; i < 4; i ++){
            TreeItem grandparent = new TreeItem(tree,0);
            grandparent.setText("GrandParent - " + i);
            for(int j = 1; j<=4; j++){
                TreeItem parent = new TreeItem(grandparent,0);
                parent.setText("Parent - " + j);
                for (int k = 0; k <= 4; k++) {
                    TreeItem child = new TreeItem(parent,0);
                    child.setText("Child - "  + k);
                   
                }
            }
        }
        
        
        
        shell.open();
        while(!shell.isDisposed()){
            if(!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
        
        
    }
    
}
