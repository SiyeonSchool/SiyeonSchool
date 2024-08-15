package com.kh.user.model.vo;

public class User {
   
   private int userNo;
   private String userId;
   private String userPwd;
   private String userName;
   private String phone;
   private String phonePublic;
   private String birthday; // sql에서 날짜양식을 지정하여 문자열로 가져옴
   private String email;
   private String address;
   private String enrollDate; // sql에서 날짜양식을 지정하여 문자열로 가져옴
   private String modifyDate; // sql에서 날짜양식을 지정하여 문자열로 가져옴
   private int profileFileNo;
   private int questionNo;
   private String questionAnswer;
   private String userAuth;
   private String status;
   private String githubUrl;
   private String notionUrl;
   private String role; // 주소록에서 유저조회시 필요한 '역할'을 담는 변수
   private String star; // 주소록에서 유저조회시 필요한 '별'을 담는 변수
   
   public User() {}

    // 모든 필드용 생성자
	public User(int userNo, String userId, String userPwd, String userName, String phone, String phonePublic,
			String birthday, String email, String address, String enrollDate, String modifyDate, int profileFileNo,
			int questionNo, String questionAnswer, String userAuth, String status, String githubUrl, String notionUrl,
			String role, String star) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.phonePublic = phonePublic;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.profileFileNo = profileFileNo;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.userAuth = userAuth;
		this.status = status;
		this.githubUrl = githubUrl;
		this.notionUrl = notionUrl;
		this.role = role;
		this.star = star;
	}
	
	// role, star를 제외한 생성자
	public User(int userNo, String userId, String userPwd, String userName, String phone, String phonePublic,
			String birthday, String email, String address, String enrollDate, String modifyDate, int profileFileNo,
			int questionNo, String questionAnswer, String userAuth, String status, String githubUrl, String notionUrl) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.phonePublic = phonePublic;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.profileFileNo = profileFileNo;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.userAuth = userAuth;
		this.status = status;
		this.githubUrl = githubUrl;
		this.notionUrl = notionUrl;
	}
	
	// 주소록에 사용할 생성자
	public User(int userNo, String userId, String userName, String phone, String birthday,
			int profileFileNo, String role, String star) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.profileFileNo = profileFileNo;
		this.role = role;
		this.star = star;
	}

	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhonePublic() {
		return phonePublic;
	}
	
	public void setPhonePublic(String phonePublic) {
		this.phonePublic = phonePublic;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEnrollDate() {
		return enrollDate;
	}
	
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public int getProfileFileNo() {
		return profileFileNo;
	}
	
	public void setProfileFileNo(int profileFileNo) {
		this.profileFileNo = profileFileNo;
	}
	
	public int getQuestionNo() {
		return questionNo;
	}
	
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	
	public String getUserAuth() {
		return userAuth;
	}
	
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getGithubUrl() {
		return githubUrl;
	}
	
	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}
	
	public String getNotionUrl() {
		return notionUrl;
	}
	
	public void setNotionUrl(String notionUrl) {
		this.notionUrl = notionUrl;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", phone=" + phone + ", phonePublic=" + phonePublic + ", birthday=" + birthday + ", email=" + email
				+ ", address=" + address + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate
				+ ", profileFileNo=" + profileFileNo + ", questionNo=" + questionNo + ", questionAnswer="
				+ questionAnswer + ", userAuth=" + userAuth + ", status=" + status + ", githubUrl=" + githubUrl
				+ ", notionUrl=" + notionUrl + ", role=" + role + ", star=" + star + "]";
	}

}
