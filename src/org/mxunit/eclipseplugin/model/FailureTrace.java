package org.mxunit.eclipseplugin.model;

/**
 * simple wrapper for each element in the "details view" when a failed/errored test is selected
 * @author marc
 *
 */
public class FailureTrace {
	private TestMethod method;
	private String filePath;
	private Integer fileLine;
	
	public FailureTrace(TestMethod method, String filePath, Integer fileLine){
		this.method = method;
		this.filePath = filePath;
		this.fileLine = fileLine;
	}

	public TestMethod getMethod() {
		return method;
	}

	public String getFilePath() {
		return filePath;
	}

	public Integer getFileLine() {
		return fileLine;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileLine(Integer fileLine) {
		this.fileLine = fileLine;
	}
	
	
}
