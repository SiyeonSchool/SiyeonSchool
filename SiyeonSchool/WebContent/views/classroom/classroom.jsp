<%@page import="com.kh.classroom.model.vo.ClassPost"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<ClassPost> list = (ArrayList<ClassPost>)request.getAttribute("list");
	
	int cPage = pi.getcPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
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
			<li class="active">
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
					<input type="hidden" name="boardNo" value="1">
					<span class="icon folder-icon material-icons-outlined">note</span>
					<span class="boardName">임시 게시글</span>
						<span class="icon edit-icon material-symbols-rounded">edit</span>
					</div>
				</li>
			<% } %>

			<!-- ------- 하나의 게시판 ------- -->
			<li>
				<div class="board-div">
					<input type="hidden" name="boardNo" value="">
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
					<input type="hidden" name="boardNo" value="">
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
					<input type="hidden" name="boardNo" value="">
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
				<li class="post-column boardName">게시판</li>
				<li class="post-column postTitle">게시글 제목</li>
				<li class="post-column attachment">첨부</li>
				<li class="post-column comment">댓글</li>
				<li class="post-column writer">작성자</li>
				<li class="post-column createDate">작성일시</li>
			</ul>
		</section>
		
		<!-- --------------------- 게시글 목록 --------------------- -->
		<section class="post-list">
			<ul>
				<% if(list.isEmpty()) { %>
					<li>조회된 게시글이 없습니다.</li>
				<% }else { %>
					<% for(ClassPost p : list) { %>
						<!-- ------- 하나의 게시글 ------- -->
						<li class="post">
							<div class="post-column boardName jc-center">
								<span><%= p.getBoardName() %></span>
							</div>
		
							<div class="post-column postTitle text-left">
								<span><%= p.getPostTitle() %></span>
							</div>
		
							<div class="post-column attachment jc-center">
								<span class="icon attach-icon material-symbols-outlined">attachment</span>
								<span class="file-count"><%= p.getFileCount() %></span>
							</div>
		
							<div class="post-column comment jc-center">
								<span class="icon comment-icon material-symbols-outlined">comment</span>
								<span class="comment-count"><%= p.getCommentCount() %></span>
							</div>
		
							<div class="post-column writer jc-center">

					            <% if(p.getProfilePath() != null){ %>
					                <img src="<%= contextPath %>/<%= p.getProfilePath() %>" class="profile-img">
					            <% }else { %>
					                <span class="material-symbols-rounded icon profile-icon">account_circle</span>
					            <% } %>
								
								<span class="userNameText"><%= p.getUserName() %></span>
								<span class="userId">(<%= p.getUserId() %>)</span>
							</div>
		
							<div class="post-column createDate jc-center">
								<span><%= p.getCreateDate() %></span>
							</div>
						</li>
					<% } %>
				<% } %>

			</ul>
		</section>

		<!-- --------------------- 페이징 --------------------- -->
		<section class="paging-area">

			<!-- ------- 이전 페이지로 ------- -->
			<% if(cPage != 1) { %>
				<!-- 왼쪽 끝 페이지로 -->
				<% if(cPage == startPage) { %>
					<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= startPage - 10 %>'">first_page</span>
				<% } else { %>
					<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= startPage %>'">first_page</span>
				<% } %>

				<!-- 왼쪽 바로 이전 페이지로 -->
				<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= cPage -1 %>'">navigate_before</span>
			
			<% } else { %> <!-- 첫페이지면 버튼 숨기기-->
				<!-- 왼쪽 끝 페이지로 -->
				<% if(cPage == startPage) { %>
					<span class="icon material-icons-outlined hidden">first_page</span>
				<% } else { %>
					<span class="icon material-icons-outlined hidden" >first_page</span>
				<% } %>

				<!-- 왼쪽 바로 이전 페이지로 -->
				<span class="icon material-icons-outlined hidden">navigate_before</span>
			<% } %>
			

			<!-- ------- 페이지 번호 처리 ------- -->
			<% for(int p=startPage; p<=endPage; p++) { %>
				<% if(p == cPage) { %>
					<span class="page currentPage"><%= p %></span>
				<% } else { %>
					<span class="page" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= p %>'"><%= p %></span>
				<% } %>
			<% } %>
			

			<!-- ------- 다음 페이지로 ------- -->
			<% if(cPage != maxPage) { %>
				<!-- 오른쪽 바로 다음 페이지로 -->
				<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= cPage + 1 %>'">navigate_next</span>
				
				<!-- 오른쪽 끝 페이지로 -->
				<% if(cPage == endPage) { %>
					<% if(endPage + 10 < maxPage) { %>
						<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= endPage + 10 %>'">last_page</span>
					<% } else { %>
						<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= maxPage %>'">last_page</span>
					<% } %>
				<% } else { %>
					<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/classroom?cpage=<%= endPage %>'">last_page</span>
				<% } %>

			<% } else { %>  <!-- 마지막 페이지면 버튼 숨기기-->
				<!-- 오른쪽 바로 다음 페이지로 -->
				<span class="icon material-icons-outlined hidden">navigate_next</span>
				
				<!-- 오른쪽 끝 페이지로 -->
				<% if(cPage == endPage) { %>
					<span class="icon material-icons-outlined hidden">last_page</span>
				<% } else { %>
					<span class="icon material-icons-outlined hidden">last_page</span>
				<% } %>
			<% } %>

		</section>

	</main>
	
</body>
</html>
