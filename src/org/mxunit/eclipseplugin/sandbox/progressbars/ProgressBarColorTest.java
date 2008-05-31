package org.mxunit.eclipseplugin.sandbox.progressbars;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

/**********************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * $Id$
 * 
 * Contributors: 
 * IBM - Initial API and implementation
 **********************************************************************/

/**
 * ProgressBar color test for Microsoft Windows Vista.
 * <p>
 * 
 * 
 * @author  Paul E. Slauenwhite
 * @version December 20, 2006
 * @since   December 20, 2006
 */
public class ProgressBarColorTest {

	private Display display = null;
	
	private Shell window = null;
	
	public static void main(String[] args) {
		new ProgressBarColorTest().open();
	}
	
	public ProgressBarColorTest(){
		
		display = new Display();
		
		window = new Shell(display, (SWT.ON_TOP | SWT.SYSTEM_MODAL | SWT.CLOSE));

		buildWindow();
	}

	public void open(){
		
		Display display = window.getDisplay();
		
		window.open();
		
		while (!window.isDisposed()) {
			
			if (!display.readAndDispatch()){ 
				display.sleep();
			}
		}				
	}

	private void buildWindow(){
		
		//Build frame: 
		window.setText("ProgressBar Color Test");
		window.setLayout(getGridLayout(1,true,10,10,10,10));
		window.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		window.setSize(300, 100);
		
		Rectangle screenSize = window.getDisplay().getClientArea();
		window.setLocation(((screenSize.width - window.getSize().x) / 2),((screenSize.height - window.getSize().y) / 2));
		
		Composite progressBarPane = new Composite(window, SWT.SHADOW_NONE);
		progressBarPane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
		progressBarPane.setLayout(getGridLayout(1,true,0,0,0,0));
	    
		final ProgressBar progressBar = new ProgressBar(progressBarPane, SWT.SMOOTH);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		
		Composite buttonPane = new Composite(window, SWT.SHADOW_NONE);
		buttonPane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
		buttonPane.setLayout(getGridLayout(4,true,0,0,10,10));

	    Button redButton = new Button(buttonPane, (SWT.PUSH | SWT.CENTER));
	    redButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
	    redButton.setText("Red");
	    redButton.addListener(SWT.Selection, new Listener(){
			
			public void handleEvent(Event event) {
				
				//Step 1: Update the progress:
				progressBar.setSelection(progressBar.getSelection() + 1);

				//Step 2: Update the color:		
				progressBar.setForeground(new Color(null, 255, 0, 0));	    		
			}			
		});

	    Button greenButton = new Button(buttonPane, (SWT.PUSH | SWT.CENTER));
	    greenButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
	    greenButton.setText("Green");
	    greenButton.addListener(SWT.Selection, new Listener(){
			
			public void handleEvent(Event event) {
				
				//Step 1: Update the progress:
				progressBar.setSelection(progressBar.getSelection() + 1);

				//Step 2: Update the color:		
				progressBar.setForeground(new Color(null, 0, 255, 0));	    		
			}			
		});

	    Button blueButton = new Button(buttonPane, (SWT.PUSH | SWT.CENTER));
	    blueButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
	    blueButton.setText("Blue");
	    blueButton.addListener(SWT.Selection, new Listener(){
			
			public void handleEvent(Event event) {
				
				//Step 1: Update the progress:
				progressBar.setSelection(progressBar.getSelection() + 1);

				//Step 2: Update the color:		
				progressBar.setForeground(new Color(null, 0, 0, 255));	    		
			}			
		});
	    
	    Button defaultSystemtColorButton = new Button(buttonPane, (SWT.PUSH | SWT.CENTER));
	    defaultSystemtColorButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	 
	    defaultSystemtColorButton.setText("Default");
	    defaultSystemtColorButton.addListener(SWT.Selection, new Listener(){
			
			public void handleEvent(Event event) {
				
				//Step 1: Update the progress:
				progressBar.setSelection(progressBar.getSelection() + 1);

				//Step 2: Reset the color to the default system color:		
				progressBar.setForeground(null);	    		
			}			
		});

	    window.layout();
	}
		
	private GridLayout getGridLayout(int numColumns, boolean makeColumnsEqualWidth, int marginWidth, int marginHeight, int horizontalSpacing, int verticalSpacing){
		
		GridLayout gridLayout = new GridLayout(numColumns, makeColumnsEqualWidth);
		
		gridLayout.marginWidth = marginWidth;
		gridLayout.marginHeight = marginHeight;
				
		gridLayout.horizontalSpacing = horizontalSpacing;
		gridLayout.verticalSpacing = verticalSpacing;
		
		return gridLayout;
	}
}

