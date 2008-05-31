package org.mxunit.eclipseplugin.model;

public interface ITest {
	public TestElementType getTestElementType();
	public String getName();
	public void setName(String name);
	public TestStatus getStatus();
	public ITest getParent();
    public void clearStatus();
}

