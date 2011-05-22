package org.mxunit.eclipseplugin.model;

public class TestMethodDataProviderElement extends AbstractTestElement {

	private TestMethod parent;
	
	public TestMethodDataProviderElement(){
		
	}
	
	public void setParent(TestMethod parent) {
		this.parent = parent;
	}
	
	public String getName(){
		return "Ima dataprovider row" + Math.random();
	}
	
	public boolean hasChildren(){
		return false;
	}
	
	public int hashCode(){
		return this.getParent().hashCode() + this.getName().hashCode();
	}
	
	public String toString(){
		return getName();
	}

	public TestSuite getSuite() {
		return getParent().getSuite();
	}

	@Override
	public ITest getParent() {
		return this.parent;
	}

	@Override
	public TestElementType getTestElementType() {
		// TODO Auto-generated method stub
		return TestElementType.TESTMETHODDATAPROVIDER;
	}
}
