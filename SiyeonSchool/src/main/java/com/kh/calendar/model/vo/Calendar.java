package com.kh.calendar.model.vo;

import java.sql.Date;

public class Calendar {

	private int calendarNo;		// 일정 글번호
	private int contactsNo;		// 주소록 번호
	private int writerNo;
	private String title;		// 일정 제목
	private String description;	// 일정 설명
	private Date startDate;		// 일정 시작날
	private Date endDate;		// 일정 종료날
	private String status;		// 삭제여부
	private int todoNo;			// 할일 글번호
	private String content;		// 할일 내용
	
	public Calendar() {}

	public Calendar(int calendarNo, int contactsNo, int writerNo, String title, String description, Date startDate,
			Date endDate, String status, int todoNo, String content) {
		super();
		this.calendarNo = calendarNo;
		this.contactsNo = contactsNo;
		this.writerNo = writerNo;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.todoNo = todoNo;
		this.content = content;
	}

	public int getCalendarNo() {
		return calendarNo;
	}

	public void setCalendarNo(int calendarNo) {
		this.calendarNo = calendarNo;
	}

	public int getContactsNo() {
		return contactsNo;
	}

	public void setContactsNo(int contactsNo) {
		this.contactsNo = contactsNo;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTodoNo() {
		return todoNo;
	}

	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Calendar [calendarNo=" + calendarNo + ", contactsNo=" + contactsNo + ", writerNo=" + writerNo
				+ ", title=" + title + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", status=" + status + ", todoNo=" + todoNo + ", content=" + content + "]";
	}
	
	
	
}
