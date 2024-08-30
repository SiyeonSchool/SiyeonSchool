package com.kh.mail.model.vo;

public class Mailbox {
	
	private String mailboxNo;
	private int ownerNo;
	private String mailboxName;
	private String mailboxType;
	private String mailboxStatus;
	private int mailCount; // 해당 메일함의 메일수
	
	public Mailbox() {}

	public Mailbox(String mailboxNo, int ownerNo, String mailboxName, String mailboxType, String mailboxStatus,
			int mailCount) {
		super();
		this.mailboxNo = mailboxNo;
		this.ownerNo = ownerNo;
		this.mailboxName = mailboxName;
		this.mailboxType = mailboxType;
		this.mailboxStatus = mailboxStatus;
		this.mailCount = mailCount;
	}

	public Mailbox(String mailboxNo, String mailboxName, int mailCount) {
		super();
		this.mailboxNo = mailboxNo;
		this.mailboxName = mailboxName;
		this.mailCount = mailCount;
	}

	public String getMailboxNo() {
		return mailboxNo;
	}

	public void setMailboxNo(String mailboxNo) {
		this.mailboxNo = mailboxNo;
	}

	public int getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(int ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getMailboxName() {
		return mailboxName;
	}

	public void setMailboxName(String mailboxName) {
		this.mailboxName = mailboxName;
	}

	public String getMailboxType() {
		return mailboxType;
	}

	public void setMailboxType(String mailboxType) {
		this.mailboxType = mailboxType;
	}

	public String getMailboxStatus() {
		return mailboxStatus;
	}

	public void setMailboxStatus(String mailboxStatus) {
		this.mailboxStatus = mailboxStatus;
	}

	public int getMailCount() {
		return mailCount;
	}

	public void setMailCount(int mailCount) {
		this.mailCount = mailCount;
	}

	@Override
	public String toString() {
		return "Mailbox [mailboxNo=" + mailboxNo + ", ownerNo=" + ownerNo + ", mailboxName=" + mailboxName
				+ ", mailboxType=" + mailboxType + ", mailboxStatus=" + mailboxStatus + ", mailCount=" + mailCount
				+ "]";
	};
	
}
