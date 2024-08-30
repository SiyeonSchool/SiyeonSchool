<%@page import="com.google.gson.Gson"%>
<%@page import="com.kh.user.model.vo.User"%>
<%@page import="com.kh.user.controller.LoginUserController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String contextPath2 = request.getContextPath(); // "/SiS" // common.jsp와 중복을 피하기위해 뒤에 숫자2 추가함. (common.jsp를 사용해도 되긴하지만, IDE에서는 변수로 인식을 못함.)

	String currentPage = (String)request.getSession().getAttribute("currentPage"); // 현재 어느 페이지(메뉴)에 있는지 표기하기 위한 변수
	
	User loginUser = (User)session.getAttribute("loginUser"); // 로그인한 유저정보
	
	String loginUserJson = new Gson().toJson(loginUser); // 로그인한 유저정보를 jsp에서 js로 보내서 사용할 수 있게끔 json으로 변환함.
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/menubar.css">
	<script defer src="resources/js/menubar.js"></script>
</head>
<body>

	<!-- ==================== 헤더 전체 ==================== -->
    <header>

		<!-- ==================== 왼쪽 로고 ==================== -->
        <div class="logo">
			<a href="<%= contextPath2 %>/home">
            	<img src="resources/images/sis_logo.png" alt="logo" height="50px;"> <!-- 로고 -->
			</a>
        </div>

		<!-- ==================== 가운데 메인메뉴  ==================== -->
        <div class="main-menu">

			<!-- 홈 -->
			<a href="<%= contextPath2 %>/home">
				<% if(currentPage.equals("home")) { %>
					<!-- 메뉴 아이콘 이미지를 사용하기엔 크기를 맞추기 어려워서, 우선은 테두리 없는 아이콘으로 사용했습니다. 기존 이미지들은 주석처리함.-->
 	  	            <!-- <img src="resources/images/menubar/home_sel.png" alt="홈버튼" style="height:45px; padding: 5px;"> -->
					<div class="menu-icon material-icons-round" style="color: var(--darkSkyBlue);">home</div>
            	<% } else { %>
		            <div class="menu-icon material-icons-round">home</div>
            	<% } %>
            </a>

			<!-- 메일 -->
            <a href="<%= contextPath2 %>/mail?mb=i&cpage=1">
            	<% if(currentPage.equals("mail")) { %>
	                <!-- <img src="resources/images/menubar/mail_sel.png" alt="메일버튼" style="height:48px; padding: 3px;"> -->
	                <div class="menu-icon material-icons-round" style="color: var(--darkPurple);">email</div>
            	<% } else { %>
	                <div class="menu-icon material-icons-round">email</div>
            	<% } %>
            </a>

			<!-- 일정 -->
            <a href="<%= contextPath2 %>/calendar">
                <% if(currentPage.equals("calendar")) { %>
	                <!-- <img src="resources/images/menubar/todo_sel.png" alt="일정버튼"> -->
	                <div class="menu-icon material-icons-round" style="color: var(--darkGreen);">edit_calendar</div>
            	<% } else { %>
	                <div class="menu-icon material-icons-round">edit_calendar</div>
            	<% } %>
            </a>

			<!-- 주소록 -->
            <a href="<%= contextPath2 %>/contacts">
                <% if(currentPage.equals("contacts")) { %>
                	<!-- <img src="resources/images/menubar/add_sel.png" alt="주소록버튼"> -->
	                <div class="menu-icon material-icons-round" style="color: var(--darkOrange);">people</div>
            	<% } else { %>
	                <div class="menu-icon material-icons-round">people</div>
            	<% } %>
            </a>

			<!-- 수업 -->
            <a href="<%= contextPath2 %>/classroom?cpage=1">
                <% if(currentPage.equals("classroom")) { %>
	                <!-- <img src="resources/images/menubar/class_sel.png" alt="수업자료버튼" style="height:49px; padding: 3px;"> -->
	                <div class="menu-icon material-symbols-outlined" style="color: var(--darkRed);">book_2</div>
            	<% } else { %>
	                <div class="menu-icon material-symbols-outlined">book_2</div>
            	<% } %>
            </a>

			<!-- 과제 -->
            <a href="<%= contextPath2 %>/homework">
                <% if(currentPage.equals("homework")) { %>
					<!-- <img src="resources/images/menubar/homework_sel.png" alt="과제버튼" style="height:47px; padding: 4px;"> -->
	                <div class="menu-icon material-icons-outlined" style="color: var(--darkPink);">assignment</div>
            	<% } else { %>
	                <div class="menu-icon material-icons-outlined">assignment</div>
            	<% } %>
            </a>

			<!-- 도구 -->
            <a href="<%= contextPath2 %>/tool">
                <% if(currentPage.equals("tool")) { %>
	                <!-- <img src="resources/images/menubar/equipment_sel.png" alt="도구버튼"> -->
	                <div class="menu-icon material-icons-round" style="color: var(--darkBlue);">category</div>
            	<% } else { %>
	                <div class="menu-icon material-icons-round">category</div>
            	<% } %>
            </a>
        </div>

		<!-- ==================== 오른쪽 프로필쪽 메뉴 ==================== -->
        <div class="right-menu">
			<!-- 알림 -->
            <!-- <div id="notification" class="material-icons-outlined">notifications</div> -->
			<div id="notification" class="material-symbols-outlined">notifications</div>

			<!-- 프로필 -->
			<div class="profile">
				<% if(loginUser.getProfilePath() == null) { %>
					<div class="material-icons">account_circle</div>
				<% } else { %>
					<img src="<%= contextPath2 %>/<%= loginUser.getProfilePath() %>">
				<% }%>
				<div class="profile-name"><%= loginUser.getUserName() %></div>
				<div class="material-symbols-rounded">keyboard_arrow_down</div>
			</div>
        </div>

		<!-- ==================== 숨겨진 오른쪽 메뉴 ==================== -->
		<div class="hidden-right-menu">

			<!-- 숨겨진 알림 -->
			<div class="hidden-notification hidden">
				<ul>
					<li>
						<p>장원영 <span class="userId">(wyjang)</span>님이 댓글을 남겼습니다.</p>
						<p class="time-and-cross">2024-07-30 13:00
							<span class="material-icons">clear</span>
						</p>
					</li>
					<li>
						<p>손흥민 <span class="userId">(hmson)</span>님이 댓글을 남겼습니다.</p>
						<p class="time-and-cross">2024-07-30 13:00
							<span class="material-icons">clear</span>
						</p>
					</li>
					<li>
						<p>황희찬 <span class="userId">(hchwang)</span>님이 댓글을 남겼습니다.</p>
						<p class="time-and-cross">2024-07-30 13:00
							<span class="material-icons">clear</span>
						</p>
					</li>
					<li>
						<p>나관리 <span class="userId">(admin)</span>님이 설문조사를 요청하였습니다.</p>
						<p class="time-and-cross">2024-07-30 13:00
							<span class="material-icons">clear</span>
						</p>
					</li>

				</ul>
			</div>

			<!-- 숨겨진 프로필메뉴 -->
			<div class="hidden-profile hidden">
				<ul>
					
						<li>
							<% if(loginUser.getUserAuth().equals("U")) { %>
								<span class="material-icons">account_circle</span>
								<span><a href="<%= contextPath2 %>/mypageInfo">마이페이지</a></span>
							<% } else{ %>
								<span class="material-icons">account_circle</span>
								<span><a href="<%= contextPath2 %>/adminpage">관리자페이지</a></span>
							<% } %>		
						</li>
					
					<li>
						<span class="material-icons">logout</span>
						<span><a href="<%= contextPath2 %>/logout.user">로그아웃</a></span>
					</li>
				</ul>
			</div>

		</div>

    </header>

</body>
</html>