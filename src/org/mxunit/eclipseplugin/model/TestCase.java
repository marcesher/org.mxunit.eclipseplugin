package org.mxunit.eclipseplugin.model;

import java.util.ArrayList;
import java.util.List;

public final class TestCase extends AbstractTestElement {
	
	/** list of methods */
	private List<TestMethod> methods = new ArrayList<TestMethod>();
	/** the suite to which this testcase belongs. Suites are kind of a virtual thing and are really
	 * just an abstract container
	 */
	private TestSuite parent;
	/** the full file path for this TestCase */
	private String filePath = "";
	
	public TestSuite getSuite() {
		return parent;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<TestMethod> getMethods(){
		return methods;
	}
	
	public void addMethod(TestMethod method){
		method.setParent(this);
		if(methods.indexOf(method)>=0){
			methods.set(methods.indexOf(method), method);
		}else{
			methods.add(method);
		}
		setChanged();
	}
	public void setMethods(List<TestMethod> methods) {
		this.methods = methods;
		setChanged();
	}
    public void clearMethods(){
        methods = new ArrayList<TestMethod>();
        setChanged();
    }
	public TestMethod[] getMethodsAsArray(){	
		return methods.toArray( new TestMethod[methods.size()] );
	}
	
	public boolean hasChildren(){
		return methods.size()>0;
	}

	public TestSuite getParent() {
		return parent;
	}

	public void setParent(TestSuite parent) {
		this.parent = parent;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public TestStatus getStatus() {
		return status;
	}
	
	public String getType(){
		return "TestCase";
	}

	public void setStatus(TestStatus status) {
		this.status = status;
		/*
		parent.updateStatus(status);
		if(status == TestStatus.ERROR || status == TestStatus.FAIL){
			parent.setStatus(status);
		}
		if(status == TestStatus.PASS && parent.getStatus() == TestStatus.BLANK){
			parent.setStatus(TestStatus.PASS);
		}
		*/
		setChanged();
	}
	
	/**
	 * given the passed-in status, updateStatus will determine
	 * whether to accept it or reject it based on the status
	 * of the rest of its methods.
	 * 
	 * The contract here is that Error trumps Failure. If
	 * the passed status is PASS, then we have to look at
	 * all other methods in the test case to make sure none
	 * of them have failures or errors. only in that case
	 * will we accept the PASS and set our status to it. i'm well aware
	 * that this incurs a slight performance penalty because of the
	 * duplication it entails; however, for now, as I start this
	 * project, it feels the safest way to ensure that the status
	 * is always accurate, considering the whacky things i'm
	 * allowing in the test runner (i.e. multi-selectable method-level testing
	 * TODO: down the road, if performance becomes an issue, refactor
	 * to make less dumb/brute-force
	 * @param status the status to consider
	 */
	protected void updateStatus(TestStatus status){	
		boolean pass = true;
		if(status == TestStatus.ERROR){
			this.status = status;
		}else if(status == TestStatus.FAIL && this.status != TestStatus.ERROR){
			this.status = status;			
		}else if(status == TestStatus.PASS){
			for(TestMethod method : methods){
				if(method.getStatus() == TestStatus.ERROR || method.getStatus() == TestStatus.FAIL){
					pass = false;
					break;
				}
			}
			if(pass){
				this.status = status;
			}			
		}
		//the "else" case here can only be TestStatus.BLANK, and we don't care
		//about responding to it.
		
		//now, make sure our parent does the same thing
		if(parent!=null){
			parent.updateStatus(this.status);
		}
		setChanged();
	}
	
	public void clearStatus(){
		this.status = TestStatus.BLANK;
		setChanged();
	}
	
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if( obj==null || !(obj instanceof TestCase) ){
			return false;
		}
		TestCase tc = (TestCase)obj;
		if(filePath.length()>0 && filePath.equalsIgnoreCase(tc.getFilePath())){
			return true;
		}
		return false;
	}
	
	public int hashCode(){
		return getFilePath().hashCode();
	}
	
	public String toString(){
		return name;
	}

	public TestElementType getTestElementType() {
		return TestElementType.TESTCASE;
	}
}
