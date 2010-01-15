package org.mxunit.eclipseplugin.model;

import java.text.SimpleDateFormat;
import java.util.Observable;

import org.eclipse.core.resources.IResource;

public abstract class AbstractTestElement extends Observable implements ITest{

	protected String name = "";
	protected TestStatus status = TestStatus.BLANK;
	protected long startTime = System.currentTimeMillis();
	protected long endTime = System.currentTimeMillis();
	protected long totalServerTime = 0;
	protected IResource resource = null;
	
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
	
	public void setTotalServerTime(long totalServerTime) {
		this.totalServerTime = totalServerTime;
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
	
	public long getTotalServerTime(){
		return totalServerTime;
	}

	public TestStatus getStatus() {
		return status;
	}
    
    public void clearStatus(){
        status = TestStatus.BLANK;
    }
    
    public boolean isComparableFailure(){
    	return false;
    }
    
    
    public IResource getResource(){
    	return resource;
    }
    
    public void setResource(IResource resource){
    	this.resource = resource;
    }
    
    public boolean hasChildren(){
    	return getChildren().length > 0;
    }
    
    public ITest[] getChildren(){
    	return new ITest[0];
    }

	public abstract ITest getParent();
	
	public abstract TestElementType getTestElementType();
	
	

}
