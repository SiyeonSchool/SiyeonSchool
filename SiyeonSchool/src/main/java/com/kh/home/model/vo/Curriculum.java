package com.kh.home.model.vo;

public class Curriculum {
	
	private String cbName;
	private String cbState;
	
	public Curriculum() {}

	public Curriculum(String cbName, String cbState) {
		super();
		this.cbName = cbName;
		this.cbState = cbState;
	}

	public String getCbName() {
		return cbName;
	}

	public void setCbName(String cbName) {
		this.cbName = cbName;
	}

	public String getCbState() {
		return cbState;
	}

	public void setCbState(String cbState) {
		this.cbState = cbState;
	}

	@Override
	public String toString() {
		return "Curriculum [cbName=" + cbName + ", cbState=" + cbState + "]";
	}
	
	

}
