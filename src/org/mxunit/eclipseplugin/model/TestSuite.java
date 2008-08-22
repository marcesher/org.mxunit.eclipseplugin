package org.mxunit.eclipseplugin.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TestSuite extends AbstractTestElement {

	
	/** list of TestCases */
	private List<TestCase> tests = new ArrayList<TestCase>();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<TestCase> getTests(){
		return tests;
	}
	public void setTests(List<TestCase> tests){
		this.tests = tests;
		setChanged();
	}
	
	public TestCase[] getTestsAsArray(){
		return tests.toArray( new TestCase[tests.size()] );
	}
	
	public boolean hasChildren(){
		return tests.size()>0;
	}
	
	public void addTest(TestCase testcase){
		testcase.setParent(this);
		tests.add(testcase);
		setChanged();
	}
	
	public void removeTest(TestCase testcase){
		tests.remove(testcase);
		setChanged();
	}

	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
		setChanged();
	}	
	
	public List<TestMethod> getAllTestMethods(){
		List<TestMethod> methods = new ArrayList<TestMethod>();
		List<TestCase> testCases = getTests();
		for (Iterator<TestCase> caseIter = testCases.iterator(); caseIter.hasNext();) {
			List<TestMethod> testMethods = caseIter.next().getMethods();
			for (Iterator<TestMethod> methIter = testMethods.iterator(); methIter
					.hasNext();) {
				methods.add(methIter.next());	
			}
		}
		return methods;
	}
	
	
	/**
	 * given the passed-in status, updateStatus will determine
	 * whether to accept it or reject it based on the status
	 * of the rest of its test cases
	 * @param status the status to consider
	 */
	protected void updateStatus(TestStatus status){
		boolean pass = true;
		if(status == TestStatus.ERROR){
			this.status = status;
		}else if(status == TestStatus.FAIL && this.status != TestStatus.ERROR){
			this.status = status;			
		}else if(status == TestStatus.PASS){
			for(TestCase test : tests){
				if(test.getStatus() == TestStatus.ERROR || test.getStatus() == TestStatus.FAIL){
					pass = false;
					break;
				}
			}
			if(pass){
				this.status = status;
			}			
		}
		setChanged();
	}
	
	public void clearStatus(){
		this.status = TestStatus.BLANK;
		setChanged();
	}
	
	
	public TestElementType getTestElementType(){
		return TestElementType.TESTSUITE;
	}

	public List<TestCase> example(){		
		List<TestCase> example = new ArrayList<TestCase>();		
		for (int i = 0; i < 5; i++) {
			TestCase tc = new TestCase();
			tc.setParent(this);
			tc.setName("testcase_" + i);
			tc.setFilePath(tc.getName() + ".cfc");
			for(int j = 0; j < 5; j++){
				TestMethod tm = new TestMethod();
				tm.setName("method_" + i + "_" + j);	
				tm.setResult(tm.getName() + ": result goes here result goes here result goes here result goes here result goes here ");
				tc.addMethod(tm);
			}
			example.add(tc);
		}		
		return example;
	}
	
	public TestCase[] exampleAsArray(){
		List<TestCase> e = example();
		System.out.println("calling exampleAsArray. Returning " + e.size() + " elements");
		return e.toArray( new TestCase[e.size()]  );
	}

	
	public ITest getParent() {
		return null;
	}

	public int hashCode(){
		return getName().hashCode();
	}
	
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}		
		if( obj==null || !(obj instanceof TestSuite) ){
			return false;
		}
		TestSuite suite = (TestSuite)obj;
		if(getName().length()>0 && getName().equals(suite.getName())){
			return true;
		}
		
		return false;
	}

}
