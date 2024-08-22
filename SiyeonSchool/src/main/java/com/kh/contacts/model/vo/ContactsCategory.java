package com.kh.contacts.model.vo;

public class ContactsCategory {
	
	private int categoryNo;
	private String categoryName;
	private String status;
	
	public ContactsCategory() {}

	public ContactsCategory(int categoryNo, String categoryName, String status) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.status = status;
	}

	public ContactsCategory(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ContactsCategory [categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", status=" + status
				+ "]";
	}
	
}
