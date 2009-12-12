package org.mxunit.eclipseplugin.model;

import org.eclipse.core.resources.IResource;

public interface ITest {
	public TestElementType getTestElementType();
	public String getName();
	public TestSuite getSuite();
	public IResource getResource();
	public void setName(String name);
	public void setStartTime(long startTime);
	public void setEndTime(long endTime);
	public void setTotalServerTime(long totalServerTime);
	public void setResource(IResource resource);
	public long getStartTime();
	public long getEndTime();
	public long getTotalServerTime();
	public TestStatus getStatus();
	public ITest getParent();
    public void clearStatus();
    public boolean isComparableFailure();
    
    
}

