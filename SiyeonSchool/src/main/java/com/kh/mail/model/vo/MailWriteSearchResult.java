package com.kh.mail.model.vo;

public class MailWriteSearchResult {
	// 메일쓰기 페이지에서 수신인 검색에 필요한 데이터를 담는 vo
	// -> 사용자목록조회 or 주소록목록조회 후 값을 담아올때 사용됨.
	
	private int pkNo;		// 사용자번호 or 주소록번호
	private String name;	// 사용자이름 or 주소록이름
	private String userId;	// 사용자아이디
	private boolean isUser; // true:사용자 / false:주소록
	
	public MailWriteSearchResult() {}

	// 사용자목록조회에 사용
	public MailWriteSearchResult(int pkNo, String name, String userId, boolean isUser) {
		super();
		this.pkNo = pkNo;
		this.name = name;
		this.userId = userId;
		this.isUser = isUser;
	}
	
	// 주소록목록조회에 사용
	public MailWriteSearchResult(int pkNo, String name, boolean isUser) {
		super();
		this.pkNo = pkNo;
		this.name = name;
		this.isUser = isUser;
	}

	public int getPkNo() {
		return pkNo;
	}

	public void setPkNo(int pkNo) {
		this.pkNo = pkNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	@Override
	public String toString() {
		return "MailWriteSearchResult [pkNo=" + pkNo + ", name=" + name + ", userId=" + userId + ", isUser=" + isUser
				+ "]";
	}

}
