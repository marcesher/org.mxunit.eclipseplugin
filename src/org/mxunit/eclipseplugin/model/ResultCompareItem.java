package org.mxunit.eclipseplugin.model;

import org.eclipse.compare.ITypedElement;
import org.eclipse.swt.graphics.Image;

public class ResultCompareItem implements ITypedElement {
	private String contents, name;
	
	public ResultCompareItem(String name, String contents){
		this.name = name;
		this.contents = contents;
	}

	public Image getImage() {
		return null;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return ITypedElement.TEXT_TYPE;
	}
	
	
}
