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
	private String profilePath; // sql에서 프로필파일의 경로+파일명으로 합쳐서 문자열 형태로 가져옴 ex) /resources/images/profile_img/user17.jpg
	private int questionNo;
	private String questionAnswer;
	private String userAuth;
	private String status;
	private String githubUrl;
	private String notionUrl;
	private String role; // 주소록에서 유저조회시 필요한 변수 - '역할'
	private String star; // 주소록에서 유저조회시 필요한 변수 - '별'
	private int contactsNo; // 주소록에서 유저조회시 필요한 변수 - '주소록번호'
	private String contactsName; // 주소록에서 유저조회시 필요한 변수 - '주소록이름'

	public User() {}

    // 모든 필드용 생성자 (USER DB 기본정보 전체 데이터 + 주소록에 사용될 추가 데이터(역할, 별)
	public User(int userNo, String userId, String userPwd, String userName, String phone, String phonePublic,
			String birthday, String email, String address, String enrollDate, String modifyDate, int profileFileNo,
			String profilePath, int questionNo, String questionAnswer, String userAuth, String status, String githubUrl,
			String notionUrl, String role, String star, int contactsNo, String contactsName) {
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
		this.profilePath = profilePath;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.userAuth = userAuth;
		this.status = status;
		this.githubUrl = githubUrl;
		this.notionUrl = notionUrl;
		this.role = role;
		this.star = star;
		this.contactsNo = contactsNo;
		this.contactsName = contactsName;
	}
	
	// USER DB 기본정보 전체 데이터 (주소록에 사용될 role, star를 제외한 생성자)
	public User(int userNo, String userId, String userPwd, String userName, String phone, String phonePublic,
			String birthday, String email, String address, String enrollDate, String modifyDate, String profilePath,
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
		this.profilePath = profilePath;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.userAuth = userAuth;
		this.status = status;
		this.githubUrl = githubUrl;
		this.notionUrl = notionUrl;
	}

	

	// 회원가입에 사용될 생성자
	public User(String userId, String userPwd, String userName, String phone, String birthday, String email, String address,
		int questionNo, String questionAnswer) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
	}

	// 주소록에 사용할 생성자1 - 카테고리 전체구성원 조회시 사용
	public User(int userNo, String userId, String userName, String phone, String birthday, String profilePath,
			String role, String star, int contactsNo, String contactsName) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.profilePath = profilePath;
		this.role = role;
		this.star = star;
		this.contactsNo = contactsNo;
		this.contactsName = contactsName;
	}
	
	// 주소록에 사용할 생성자2 - 일반 주소록구성원 조회시 사용
	public User(int userNo, String userId, String userName, String phone, String birthday,
			String profilePath, String role, String star) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.profilePath = profilePath;
		this.role = role;
		this.star = star;
	}
	

	public User(int userNo, String userId, String userPwd, String userName, String phone, String birthday, String email,
			String address, String enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
	}

	public User(String userName, String phone) {
		super();
		this.userName = userName;
		this.phone = phone;
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

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
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
				+ ", profileFileNo=" + profileFileNo + ", profilePath=" + profilePath + ", questionNo=" + questionNo
				+ ", questionAnswer=" + questionAnswer + ", userAuth=" + userAuth + ", status=" + status
				+ ", githubUrl=" + githubUrl + ", notionUrl=" + notionUrl + ", role=" + role + ", star=" + star
				+ ", contactsNo=" + contactsNo + ", contactsName=" + contactsName + "]";
	}
	
}
