package com.kh.homework.model.vo;

public class Subject {

	private String subjectNo;
	private String subjectName;
	
	public Subject() {}

	public Subject(String subjectNo, String subjectName) {
		super();
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Subject [subjectNo=" + subjectNo + ", subjectName=" + subjectName + "]";
	}
	
	
	
}
