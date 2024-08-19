package com.kh.contacts.model.vo;

public class ContactsMember {
	
	private int contactsNo;
	private int userNo;
	private String role;
	
	public ContactsMember() {}

	public ContactsMember(int contactsNo, int userNo, String role) {
		super();
		this.contactsNo = contactsNo;
		this.userNo = userNo;
		this.role = role;
	}
	
	public ContactsMember(int contactsNo, int userNo) {
		super();
		this.contactsNo = contactsNo;
		this.userNo = userNo;
	}

	public int getContactsNo() {
		return contactsNo;
	}

	public void setContactsNo(int contactsNo) {
		this.contactsNo = contactsNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ContactsMember [contactsNo=" + contactsNo + ", userNo=" + userNo + ", role=" + role + "]";
	};
}
