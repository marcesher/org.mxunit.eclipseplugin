package org.mxunit.eclipseplugin.model;

import java.util.Map;

public final class TestMethod extends AbstractTestElement {

	
	/** String returned from the test runner, e.g. pass, fail, error*/
	private String result = "";
	private String expected = "";
	private String actual = "";
	/** any generated output (html) */
	private String output = "";
	/** exception string */
	private String exception = "";
	/** tag context array */
	private Map[] tagcontext;
	/** the TestCase to which this method belongs */
	private TestCase parent;
	/** line number in the file of the error or failure */
	private int failLineNum = 0;
	
	public TestSuite getSuite() {
		return getParent().getSuite();
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TestCase getParent() {
		return parent;
	}

	public void setParent(TestCase parent) {
		this.parent = parent;
	}

	public int getFailLineNum() {
		return failLineNum;
	}

	public void setFailLineNum(int failLineNum) {
		this.failLineNum = failLineNum;
	}
	
	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
		parent.updateStatus(this.status);	
		setChanged();
	}
	
	public void setStatusFromString(String statusString){
		TestStatus status = TestStatus.BLANK;
		statusString = statusString.toLowerCase();
        if (statusString.indexOf("pass") > -1) {
            status = TestStatus.PASS;
        } else if (statusString.indexOf("error") > -1) {
        	status =  TestStatus.ERROR;
        } else if (statusString.indexOf("fail") > -1) {
        	status =  TestStatus.FAIL;
        } 
		setStatus(status);
	}
	
	public void clearStatus(){
		this.status = TestStatus.BLANK;
		setChanged();
	}
	
	
	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}
	
	public boolean isComparableFailure(){
		return expected.length() + actual.length() > 0;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	/**
     * @return the exception
     */
    public String getException() {
        return exception;
    }

    /**
     * @param exception the exception to set
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * @return the tagcontext
     */
    public Map[] getTagcontext() {
        return tagcontext;
    }

    /**
     * @param tagcontext the tagcontext to set
     */
    public void setTagcontext(Map[] tagcontext) {
        this.tagcontext = tagcontext;
    }

    public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if( obj==null || !(obj instanceof TestMethod)){
			return false;
		}
		TestMethod tm = (TestMethod)obj;
		if( this.getName().equals(tm.getName())
			&&
			this.getParent().equals(tm.getParent())
		){
			return true;
		}
		return false;
	}
	
	public int hashCode(){
		return this.getParent().getFilePath().hashCode() + this.getName().hashCode();
	}
	
	public String toString(){
		return getName();
	}

	public TestElementType getTestElementType() {
		return TestElementType.TESTMETHOD;
	}
}
