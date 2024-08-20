<%@page import="com.kh.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath2 = request.getContextPath();
	String currentPage = (String)request.getSession().getAttribute("currentPage");
	User loginUser = (User)session.getAttribute("loginUser"); // 로그인 한 유저 정보
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/adminPageMenu.css">
</head>
<body>

	<div id="side-area">
		<header>
            <div class="adminPage-title"><p>관리자페이지</p></div>
        </header>

		<div id="img-area"> <!-- 관리자페이지 메뉴 프로필 -->
			<img src="resources/images/myPage/account_circle_24dp_B7B7B7.png" id="profile-img">
			<p align="center"><%= loginUser.getUserName() %></p>
		</div>

		<div id="menu-area"> <!-- 관리자페이지 메뉴바 영역 -->
            <% if(currentPage.equals("sigInRequest")) { %>
                <a href="<%= contextPath2 %>/sigInRequest.list"  class="adminPage-menu selected">
                    <p>회원가입 관리</p>
                </a>
            <% } else { %>
                <a href="<%= contextPath2 %>/sigInRequest.list"  class="adminPage-menu">
                    <p>회원가입 관리</p>
                </a>
            <% } %>

            <% if(currentPage.equals("studentInfoList")) { %>
                <a href="<%= contextPath2 %>/studentInfo.list"  class="adminPage-menu selected">
                    <p>학생 정보</p>
                </a>
            <% } else { %>
                <a href="<%= contextPath2 %>/studentInfo.list"  class="adminPage-menu">
                    <p>학생 정보</p>
                </a>
            <% } %>

            <% if(currentPage.equals("studentATD")) { %>
                <a href="<%= contextPath2 %>/studentATD.list"  class="adminPage-menu selected">
                    <p>학생 출결</p>
                </a>
            <% } else { %>
                <a href="<%= contextPath2 %>/studentATD.list"  class="adminPage-menu">
                    <p>학생 출결</p>
                </a>
            <% } %>

            <% if(currentPage.equals("studentScore")){ %>
                <a href="<%= contextPath2 %>/studentScore.list" class="adminPage-menu selected">
                    <p>학생 성적 관리</p>
                </a>
            <% } else { %>
                <a href="<%= contextPath2 %>/studentScore.list" class="adminPage-menu">
                    <p>학생 성적 관리</p>
                </a>
            <% } %>
		</div>
        
    </div>

</body>
</html>