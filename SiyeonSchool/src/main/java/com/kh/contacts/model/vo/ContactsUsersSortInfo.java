package com.kh.contacts.model.vo;

public class ContactsUsersSortInfo { // 주소록 구성원조회시 필요한 정보를 담는 VO
	
	private int currentUserNo;	// 현재유저번호 (로그인유저번호)
	private int categoryNo;		// 주소록카테고리번호
	private int contactsNo;		// 주소록번호
	private String orderBy; 	// 정렬기준
	private boolean isDesc;		// 내림차순여부 (true:내림차순 / fasle:오름차순)
	
	public ContactsUsersSortInfo() {}

	public ContactsUsersSortInfo(int currentUserNo, int categoryNo, int contactsNo, String orderBy, boolean isDesc) {
		super();
		this.currentUserNo = currentUserNo;
		this.categoryNo = categoryNo;
		this.contactsNo = contactsNo;
		this.orderBy = orderBy;
		this.isDesc = isDesc;
	}

	public int getCurrentUserNo() {
		return currentUserNo;
	}

	public void setCurrentUserNo(int currentUserNo) {
		this.currentUserNo = currentUserNo;
	}

	public int getContactsNo() {
		return contactsNo;
	}

	public void setContactsNo(int contactsNo) {
		this.contactsNo = contactsNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isDesc() {
		return isDesc;
	}

	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "ContactsUsersSortInfo [currentUserNo=" + currentUserNo + ", categoryNo=" + categoryNo + ", contactsNo="
				+ contactsNo + ", orderBy=" + orderBy + ", isDesc=" + isDesc + "]";
	}
}
