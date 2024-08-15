package com.kh.contacts.model.vo;

public class Contacts {
	private int contactsNo;
	private String contactsName;
	private String contactsType;
	private String status;
	private int categoryNo;
	private int userCount; // 해당 주소록의 인원수 
	
	public Contacts() {}

	public Contacts(int contactsNo, String contactsName, String contactsType, String status, int categoryNo,
			int userCount) {
		super();
		this.contactsNo = contactsNo;
		this.contactsName = contactsName;
		this.contactsType = contactsType;
		this.status = status;
		this.categoryNo = categoryNo;
		this.userCount = userCount;
	}

	public Contacts(int contactsNo, String contactsName, int userCount) {
		super();
		this.contactsNo = contactsNo;
		this.contactsName = contactsName;
		this.userCount = userCount;
	}

	public int getContactsNo() {
		return contactsNo;
	}

	public void setContactsNo(int contactsNo) {
		this.contactsNo = contactsNo;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsType() {
		return contactsType;
	}

	public void setContactsType(String contactsType) {
		this.contactsType = contactsType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	@Override
	public String toString() {
		return "Contacts [contactsNo=" + contactsNo + ", contactsName=" + contactsName + ", contactsType="
				+ contactsType + ", status=" + status + ", categoryNo=" + categoryNo + ", userCount=" + userCount + "]";
	}

}
