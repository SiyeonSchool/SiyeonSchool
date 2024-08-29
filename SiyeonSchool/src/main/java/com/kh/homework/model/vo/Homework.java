package com.kh.homework.model.vo;

import java.sql.Date;

public class Homework {
	
	private String homeworkNo; 			// 게시글 번호
	private String fileNo;				// 첨부파일 번호
	private int userNo;		
	private String subjectNo;			// 과목번호
	private String subjectName;			// 과목명
	private String homeworkTitle;       // 과제명
	private Date homeworkEndDate;		// 제출기한
	private String description;			// 과제설명
	private Date writeDate;				// 작성일
	private String homeworkSubmitNo;	// 과제제출번호
	private Date submitDate;			// 과제 제출일
	private String status;				// 제출상태
	
	public Homework() {}

	public Homework(String homeworkNo, String fileNo, int userNo, String subjectNo, String subjectName,
			String homeworkTitle, Date homeworkEndDate, String description, Date writeDate, String homeworkSubmitNo,
			Date submitDate, String status) {
		super();
		this.homeworkNo = homeworkNo;				
		this.fileNo = fileNo;					
		this.userNo = userNo;						
		this.subjectNo = subjectNo; 		
		this.subjectName = subjectName;			
		this.homeworkTitle = homeworkTitle;
		this.homeworkEndDate = homeworkEndDate;
		this.description = description;
		this.writeDate = writeDate;
		this.homeworkSubmitNo = homeworkSubmitNo;
		this.submitDate = submitDate;
		this.status = status;
	}

	public String getHomeworkNo() {
		return homeworkNo;
	}

	public void setHomeworkNo(String homeworkNo) {
		this.homeworkNo = homeworkNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public void setSunjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getHomeworkTitle() {
		return homeworkTitle;
	}

	public void setHomeworkTitle(String homeworkTitle) {
		this.homeworkTitle = homeworkTitle;
	}

	public Date getHomeworkEndDate() {
		return homeworkEndDate;
	}

	public void setHomeworkEndDate(Date homeworkEndDate) {
		this.homeworkEndDate = homeworkEndDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getHomeworkSubmitNo() {
		return homeworkSubmitNo;
	}

	public void setHomeworkSubmitNo(String homeworkSubmitNo) {
		this.homeworkSubmitNo = homeworkSubmitNo;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Homework [homeworkNo=" + homeworkNo + ", fileNo=" + fileNo + ", userNo=" + userNo + ", subjectNo="
				+ subjectNo + ", subjectName=" + subjectName + ", homeworkTitle=" + homeworkTitle + ", homeworkEndDate="
				+ homeworkEndDate + ", description=" + description + ", writeDate=" + writeDate + ", homeworkSubmitNo="
				+ homeworkSubmitNo + ", submitDate=" + submitDate + ", status=" + status + "]";
	}
	
	
	
}
