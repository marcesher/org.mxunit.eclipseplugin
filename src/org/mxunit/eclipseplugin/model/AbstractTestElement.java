package org.mxunit.eclipseplugin.model;

import java.util.Observable;

public abstract class AbstractTestElement extends Observable implements ITest{

	protected String name = "";
	protected TestStatus status = TestStatus.BLANK;
	protected long startTime = System.currentTimeMillis();
	protected long endTime = System.currentTimeMillis();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStartTime(long startTime){
		this.startTime = startTime;
	}
	
	public void setEndTime(long endTime){
		this.endTime = endTime;
	}
	
	public long getStartTime(){
		return startTime;
	}
	
	public long getEndTime(){
		return endTime;
	}

	public TestStatus getStatus() {
		return status;
	}
    
    public void clearStatus(){
        status = TestStatus.BLANK;
    }

	public abstract ITest getParent();
	
	public abstract TestElementType getTestElementType();

}
