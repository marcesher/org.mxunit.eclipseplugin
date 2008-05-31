package org.mxunit.eclipseplugin.sandbox.progressbars;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressBarWithRectangles {
	static int increment = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);

		shell.open();
		final Canvas canvas = new Canvas(shell, SWT.NO_REDRAW_RESIZE);
		canvas.setSize(shell.getSize());
		final Rectangle clientArea = canvas.getClientArea();

		new Thread() {
			public void run() {
				final int max = clientArea.width;
				final int steps = 50;
				final int origincrement = max / steps;
				increment = origincrement;

				for (final int[] i = new int[1]; i[0] <= steps; i[0]++) {

					if (display.isDisposed())
						return;
					display.asyncExec(new Runnable() {
						public void run() {
							
							final GC gc = new GC(canvas);
							System.out.println(increment);
							gc.setBackground(display
									.getSystemColor(SWT.COLOR_DARK_GRAY));
							gc.drawRectangle(0, 0, max, 20);
							
							
							gc.setBackground(display
									.getSystemColor(SWT.COLOR_WHITE));
							gc.setBackground(display
									.getSystemColor(SWT.COLOR_CYAN));
							gc.fillRectangle(1, 1, increment - 1, 18);

							increment += origincrement;

						}
					});

					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				display.asyncExec(new Runnable() {
					public void run() {
						final GC gc = new GC(canvas);
						gc.setBackground(display
								.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
						gc.fillRectangle(1, 1, max - 1, 18);
						
					}
				});
			}
		}.start();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
