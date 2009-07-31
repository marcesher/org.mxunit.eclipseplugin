package org.mxunit.eclipseplugin.model;

public interface ITest {
	public TestElementType getTestElementType();
	public String getName();
	public TestSuite getSuite();
	public void setName(String name);
	public void setStartTime(long startTime);
	public void setEndTime(long endTime);
	public void setTotalServerTime(long totalServerTime);
	public long getStartTime();
	public long getEndTime();
	public long getTotalServerTime();
	public TestStatus getStatus();
	public ITest getParent();
    public void clearStatus();
    public boolean isComparableFailure();
    
}

