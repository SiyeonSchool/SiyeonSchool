package com.kh.myPage.model.vo;

public class Attendance {
	private int userNo;
	private String day;
	private String stateCode;
	private String stateName;
	private int uda;
	
	public Attendance() {}
	
	public Attendance(int userNo, String day, String stateCode, String stateName, int uda) {
		super();
		this.userNo = userNo;
		this.day = day;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.uda = uda;
	}

	public Attendance(int userNo, String day, String stateCode, int uda) {
		super();
		this.userNo = userNo;
		this.day = day;
		this.stateCode = stateCode;
		this.uda = uda;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getUda() {
		return uda;
	}

	public void setUda(int uda) {
		this.uda = uda;
	}

	@Override
	public String toString() {
		return "Attendance [userNo=" + userNo + ", day=" + day + ", stateCode=" + stateCode + ", stateName=" + stateName
				+ ", uda=" + uda + "]";
	}


	
	
}
