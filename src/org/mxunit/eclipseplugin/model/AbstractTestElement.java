package org.mxunit.eclipseplugin.model;

import java.text.SimpleDateFormat;
import java.util.Observable;

public abstract class AbstractTestElement extends Observable implements ITest{

	protected String name = "";
	protected TestStatus status = TestStatus.BLANK;
	protected long startTime = System.currentTimeMillis();
	protected long endTime = System.currentTimeMillis();
	private SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy h:mm:ss aa");
	
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
	
	public String getFormattedStartTime(){
		return sdf.format(startTime);
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
