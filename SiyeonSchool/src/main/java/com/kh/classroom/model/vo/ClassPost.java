package com.kh.classroom.model.vo;

import java.sql.Date;

public class ClassPost {
	
	private int postNo;
	private int boardNo;
	private String boardName;	// 게시판이름
	private int userNo;
	private String userName; 	// 작성자이름
	private String userId;		// 작성자 아이디
	private String postTitle;
	private String postContent;
	private Date createDate;
	private int commentCount;	// 댓글수
	private int fileCount;		// 첨부파일수
	private String status;
	
	public ClassPost() {}

	public ClassPost(int postNo, int boardNo, String boardName, int userNo, String userName, String userId,
			String postTitle, String postContent, Date createDate, int commentCount, int fileCount, String status) {
		super();
		this.postNo = postNo;
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.userNo = userNo;
		this.userName = userName;
		this.userId = userId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createDate = createDate;
		this.commentCount = commentCount;
		this.fileCount = fileCount;
		this.status = status;
	}

	public ClassPost(int postNo, int boardNo, String boardName, int userNo, String userName, String userId,
			String postTitle, Date createDate, int commentCount, int fileCount) {
		super();
		this.postNo = postNo;
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.userNo = userNo;
		this.userName = userName;
		this.userId = userId;
		this.postTitle = postTitle;
		this.createDate = createDate;
		this.commentCount = commentCount;
		this.fileCount = fileCount;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
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
				+ userNo + ", userName=" + userName + ", userId=" + userId + ", postTitle=" + postTitle
				+ ", postContent=" + postContent + ", createDate=" + createDate + ", commentCount=" + commentCount
				+ ", fileCount=" + fileCount + ", status=" + status + "]";
	};
	
}
