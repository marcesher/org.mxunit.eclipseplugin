package org.mxunit.eclipseplugin.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

public class ResultCompareItem implements ITypedElement, IStreamContentAccessor {
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

	public InputStream getContents() throws CoreException {
		return new ByteArrayInputStream(contents.getBytes());
	}
	
	
}
