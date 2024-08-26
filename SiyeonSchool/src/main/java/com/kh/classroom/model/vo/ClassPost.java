package com.kh.classroom.model.vo;

public class ClassPost {
	
	private String postNo;
	private int boardNo;
	private String boardName;	// 게시판이름
	private int userNo;
	private String userName; 	// 작성자 이름
	private String userId;		// 작성자 아이디
	private String profilePath; // 작성자 프로필경로
	private String postTitle;
	private String postContent;
	private String createDate;  // 작성일 (SQL에서 날짜를 문자열 형태로 바꿔서 가져옴)
	private int commentCount;	// 댓글수
	private int fileCount;		// 첨부파일수
	private String status;
	
	public ClassPost() {}

	// 모든필드 생성자
	public ClassPost(String postNo, int boardNo, String boardName, int userNo, String userName, String userId,
			String profilePath, String postTitle, String postContent, String createDate, int commentCount,
			int fileCount, String status) {
		super();
		this.postNo = postNo;
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.userNo = userNo;
		this.userName = userName;
		this.userId = userId;
		this.profilePath = profilePath;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createDate = createDate;
		this.commentCount = commentCount;
		this.fileCount = fileCount;
		this.status = status;
	}

	// 모든게시글 목록조회용
	public ClassPost(String postNo, String boardName, String userName, String userId, String profilePath,
			String postTitle, String createDate, int commentCount, int fileCount) {
		super();
		this.postNo = postNo;
		this.boardName = boardName;
		this.userName = userName;
		this.userId = userId;
		this.profilePath = profilePath;
		this.postTitle = postTitle;
		this.createDate = createDate;
		this.commentCount = commentCount;
		this.fileCount = fileCount;
	}

	public String getPostNo() {
		return postNo;
	}
	
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPost [postNo=" + postNo + ", boardNo=" + boardNo + ", boardName=" + boardName + ", userNo="
				+ userNo + ", userName=" + userName + ", userId=" + userId + ", profilePath=" + profilePath
				+ ", postTitle=" + postTitle + ", postContent=" + postContent + ", createDate=" + createDate
				+ ", commentCount=" + commentCount + ", fileCount=" + fileCount + ", status=" + status + "]";
	}
	
}
