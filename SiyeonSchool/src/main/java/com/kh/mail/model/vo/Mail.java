package com.kh.mail.model.vo;

public class Mail {
	
	private String mailNo;
	private String mailStar;
	private String isRead;
	private String userName;
	private String userId;
	private String profilePath;
	private String mailTitle;
	private String mailContent;
	private String receiverType;
	private String isSent;
	private String sendDate; // sql에서 문자열형태로 받아옴.
	
	public Mail() {}

	public Mail(String mailNo, String mailStar, String isRead, String userName, String userId, String profilePath,
			String mailTitle, String mailContent, String receiverType, String isSent, String sendDate) {
		super();
		this.mailNo = mailNo;
		this.mailStar = mailStar;
		this.isRead = isRead;
		this.userName = userName;
		this.userId = userId;
		this.profilePath = profilePath;
		this.mailTitle = mailTitle;
		this.mailContent = mailContent;
		this.receiverType = receiverType;
		this.isSent = isSent;
		this.sendDate = sendDate;
	}

	// "받은메일함 메일목록" 조회에 사용됨.
	public Mail(String mailNo, String mailStar, String isRead, String userName, String userId, String profilePath,
			String mailTitle, String receiverType, String sendDate) {
		super();
		this.mailNo = mailNo;
		this.mailStar = mailStar;
		this.isRead = isRead;
		this.userName = userName;
		this.userId = userId;
		this.profilePath = profilePath;
		this.mailTitle = mailTitle;
		this.receiverType = receiverType;
		this.sendDate = sendDate;
	}

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public String getMailStar() {
		return mailStar;
	}

	public void setMailStar(String mailStar) {
		this.mailStar = mailStar;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getIsSent() {
		return isSent;
	}

	public void setIsSent(String isSent) {
		this.isSent = isSent;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return "Mail [mailNo=" + mailNo + ", mailStar=" + mailStar + ", isRead=" + isRead + ", userName=" + userName
				+ ", userId=" + userId + ", profilePath=" + profilePath + ", mailTitle=" + mailTitle + ", mailContent="
				+ mailContent + ", receiverType=" + receiverType + ", isSent=" + isSent + ", sendDate=" + sendDate
				+ "]";
	}

}
