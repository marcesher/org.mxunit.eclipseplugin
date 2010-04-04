
package org.mxunit.eclipseplugin.views;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestElementType;
import org.mxunit.eclipseplugin.model.TestStatus;

public class TestListLabelProvider extends StyledCellLabelProvider {
	
	public String getText(Object obj) {
		return ((ITest)obj).getName();
	}
	
	public void update(ViewerCell cell){
		ITest el = (ITest) cell.getElement();
		StyledString styledString = new StyledString(el.toString());
		NumberFormat fmt = DecimalFormat.getInstance();
		fmt.setParseIntegerOnly(false);
		styledString.append(" ("+ fmt.format( (double)el.getTotalServerTime()/1000d ) + " s)",
				StyledString.COUNTER_STYLER);
		cell.setText(styledString.toString());
		cell.setStyleRanges(styledString.getStyleRanges());
		cell.setImage(getImage(el));
		super.update(cell);
	}
	
	public Image getImage(Object obj) {
		return ResourceManager.getImage( getImageString(obj) );		
	}
	
	public String getImageString(Object obj){
		TestStatus status  = ((ITest)obj).getStatus();
		if(status == TestStatus.PASS){
			return ResourceManager.OK;
		}else if(status == TestStatus.ERROR){
			return ResourceManager.ERROR;
		}else if(status == TestStatus.FAIL){
			return ResourceManager.FAIL;
		}else if(status == TestStatus.INVOCATION_EXCEPTION){
			return ResourceManager.INVOCATION_EXCEPTION;
		}
		
		return ResourceManager.BLANK;
		
	}

}
