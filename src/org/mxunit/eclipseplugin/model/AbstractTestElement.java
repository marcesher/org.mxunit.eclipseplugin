package org.mxunit.eclipseplugin.model;

import java.util.Observable;

public abstract class AbstractTestElement extends Observable implements ITest{

	protected String name = "";
	protected TestStatus status = TestStatus.BLANK;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
