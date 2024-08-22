<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<%
	// 현재 게시판 번호 -> JS에서 사용
	int currentBoardNo = 0;
	if(request.getAttribute("boardNo") != null) {
		currentBoardNo = (int)request.getAttribute("boardNo");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/classroom.css">
	<script defer src="resources/js/classroom.js"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<script>
		// 별도 js 파일로 값을 가져가기 위함. 
		const loginUserJson = `<%= loginUserJson %>`; // json 형태
		const contextPath = `<%= contextPath %>`;
		const currentBoardNo = `<%= currentBoardNo %>`;
	</script>

	<!-- ======================================== 사이드바 ======================================== -->
	<aside>

		<!-- -------------- 수업게시판 타이틀 -------------- -->
		<div class="title-div">
			<h2 class="classroom-title">수업 게시판</h2>
			<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
				<div class="icon add-icon material-symbols-rounded">add</div>
			<% } %>
		</div>

		<!-- -------------- 게시판 목록 -------------- -->
		<ul>

			<!-- ------- 모든게시글 ------- -->
			<li>
				<div class="board-div">
					<input type="hidden" name="boardNo" value="0">
					<span class="icon folder-icon material-icons-outlined">source</span>
					<span class="boardName">모든 게시글</span>
					<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					<% } %>
				</div>
			</li>

			<!-- ------- 임시게시글 ------- -->
			<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
			<li>
				<div class="board-div">
					<span class="icon folder-icon material-icons-outlined">note</span>
					<span class="boardName">임시 게시글</span>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					</div>
				</li>
			<% } %>

			<!-- ------- 하나의 게시판 ------- -->
			<li>
				<div class="board-div">
					<span class="icon folder-icon material-icons-round">folder</span>
					<span class="boardName">게시판이름</span>
					<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					<% } %>
				</div>
			</li>

			<!-- ------- 하나의 게시판 ------- -->
			<li>
				<div class="board-div">
					<span class="icon folder-icon material-icons-round">folder</span>
					<span class="boardName">게시판이름</span>
					<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					<% } %>
				</div>
			</li>

			<!-- ------- 하나의 게시판 ------- -->
			<li>
				<div class="board-div">
					<span class="icon folder-icon material-icons-round">folder</span>
					<span class="boardName">게시판이름</span>
					<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					<% } %>
				</div>
			</li>

			<!-- ------- 하나의 게시판 ------- -->
			<li>
				<div class="board-div">
					<span class="icon folder-icon material-icons-round">folder</span>
					<span class="boardName">게시판이름</span>
					<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					<% } %>
				</div>
			</li>

			<!-- ------- 하나의 게시판 ------- -->
			<li>
				<div class="board-div">
					<span class="icon folder-icon material-icons-round">folder</span>
					<span class="boardName">게시판이름</span>
					<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					<% } %>
				</div>
			</li>



		</ul>
	</aside>

	<!-- ======================================== 메인 ======================================== -->
	<main>

		<!-- --------------------- 첫번째 헤더 --------------------- -->
		<section class="first-header">
			<div class="writeBtn-div">
				<button class="writeBtn">게시글 작성</button>
			</div>

			<div class="search-bar-div">
				<select class="search-option" name="search-option">
					<option value="postTitle">제목</option>
					<option value="postContent">내용</option>
				</select>
				<input type="text" class="search-bar" name="keyword" placeholder="검색어를 입력해주세요." maxlength="20">
				<span class="icon search-icon material-symbols-outlined">search</span>
			</div>
		</section>

		<!-- --------------------- 두번째 헤더 --------------------- -->
		<section class="second-header">
			<ul>
				<li class="sameWidth boardName">게시판</li>
				<li class="sameWidth postTitle">게시글 제목</li>
				<li class="sameWidth attachment">첨부파일</li>
				<li class="sameWidth comment">댓글</li>
				<li class="sameWidth writer">작성자</li>
				<li class="sameWidth createDate">작성일시</li>
			</ul>
		</section>
		
		<!-- --------------------- 게시글 목록 --------------------- -->
		<section class="post-list">
			<ul>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

				<!-- ------- 하나의 게시글 ------- -->
				<li class="post">
					<div class="sameWidth boardName text-left">
						<span>Front-end</span>
					</div>

					<div class="sameWidth postTitle text-left">제목이 들어올 자리입니다.</div>

					<div class="sameWidth attachment">
						<span class="icon attach-icon material-symbols-outlined">attachment</span>
						<span class="fileName">첨부파일명1234.jpg</span>
					</div>

					<div class="sameWidth comment">
						<span class="icon comment-icon material-symbols-outlined">comment</span>
						<span class="comment-count">3</span>
					</div>

					<div class="sameWidth writer">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>

					<div class="sameWidth createDate text-center">2024-05-09  00:00</div>
				</li>

			</ul>
		</section>

		<!-- --------------------- 페이징 --------------------- -->
		<section class="paging-area">
			<span class="icon material-icons-outlined">first_page</span>
			<span class="icon material-icons-outlined">navigate_before</span>

			<span class="page">1</span>
			<span class="page">2</span>
			<span class="page">3</span>
			<span class="page">4</span>

			<span class="icon material-icons-outlined">navigate_next</span>
			<span class="icon material-icons-outlined">last_page</span>
		</section>

	</main>
</body>
</html>
