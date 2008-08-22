package org.mxunit.eclipseplugin.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.mxunit.eclipseplugin.model.TestHistory;
import org.mxunit.eclipseplugin.model.TestMethod;
import org.mxunit.eclipseplugin.model.TestSuite;
import org.mxunit.eclipseplugin.views.MXUnitView;

/**
 * constructs/manages the "test view history" dropdown action on the view
 * @author mesher
 *
 */
public class HistoryDropdownAction extends Action {
	
	private MXUnitView view;
	private TestHistory history;
	private Menu menu;

	
	public HistoryDropdownAction (MXUnitView view, TestHistory history){		
		super("Test Run History...", IAction.AS_DROP_DOWN_MENU);
		this.view = view;
		this.history = history;
		setMenuCreator(new HistoryMenuCreator());
	}
	
	private class HistoryAction extends Action {
		private final TestSuite suite;
		public HistoryAction(TestSuite s, int position){
			super("",AS_RADIO_BUTTON);
			suite = s;
			
			StringBuffer text = new StringBuffer()
				.append(" ")
				.append(s.getName())
				.append(" (")
				.append(s.getFormattedStartTime())
				.append(")");
			if(position < 10){
				text.insert(0, position);
			}
			setText(text.toString());
			setImageDescriptor(history.getImageDescriptor(suite));
		}
		
		public void run(){		
			
			if(!history.isActiveEntry(suite)){
				history.setActiveEntry(suite);			
				view.getTestsViewer().setInput(suite);
				List<TestMethod> methods = suite.getAllTestMethods();
				System.out.println(suite.getName());
				System.out.println("inside run... methods size = " + methods.size());
				view.resetCounts(methods.size());
	
				for (Iterator<TestMethod> methIter = methods.iterator(); methIter.hasNext();) {
					view.addTestResult(methIter.next().getStatus());					
				}
				
				new SelectAllInTreeAction(view).run();
				view.getTestsViewer().expandAll();
			}
		}
	}
	
	private class HistoryMenuCreator implements IMenuCreator {

		public void dispose() {
			history = null;
			if(menu != null){
				menu.dispose();
				menu = null;
			}
		}
		public Menu getMenu(Menu parent) {
			return null;
		}
		
		public Menu getMenu(Control parent) {
			
			if(menu != null){
				menu.dispose();
			}
			
			final MenuManager manager = new MenuManager();
			
			manager.setRemoveAllWhenShown(true);
			final IMenuListener listener = new IMenuListener(){
				public void menuAboutToShow(IMenuManager iMenuManager) {
					int position = 1;
					for (Iterator<TestSuite> iterator = history.getHistory().iterator(); iterator.hasNext();) {
						TestSuite suite =  iterator.next();
						HistoryAction action = new HistoryAction(suite, position);
						if(history.isActiveEntry(suite)){
							action.setChecked(true);
						}
						iMenuManager.add(action);
						position++;
					}
					
					iMenuManager.add(new Separator());
					iMenuManager.add(new Action("Clear History"){
						public void run(){
							history.removeAllSuites();
						}
					});
				}
			};
			
			manager.addMenuListener(listener);
			menu = manager.createContextMenu(parent);
			
			//need to do this to avoid memory leaks
			//shamelessly ripped off from here: http://kickjava.com/src/org/eclipse/jdt/internal/ui/viewsupport/HistoryDropDownAction.java.htm
			final Display display= parent.getDisplay();
			menu.addMenuListener(new MenuAdapter() {
			  public void menuHidden(final MenuEvent e) {
			       display.asyncExec(new Runnable() {
			           public void run() {
			               manager.removeAll();
			               if (menu != null) {
			                   menu.dispose();
			                   menu= null;
			               }
			          }
			       });
			   }
			});
			
			return menu;	
		}
	}
}
