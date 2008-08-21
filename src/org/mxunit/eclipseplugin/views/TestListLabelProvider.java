package org.mxunit.eclipseplugin.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.mxunit.eclipseplugin.model.ITest;
import org.mxunit.eclipseplugin.model.TestStatus;

public class TestListLabelProvider extends LabelProvider {
	
	public String getText(Object obj) {
		return ((ITest)obj).getName();
		
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
