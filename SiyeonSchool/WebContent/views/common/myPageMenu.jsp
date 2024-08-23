<%@page import="com.kh.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath2 = request.getContextPath();
	String currentPage = (String)request.getSession().getAttribute("currentPage");
	User loginUser = (User)session.getAttribute("loginUser"); // 로그인 한 유저 정보
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/css/myPageMenu.css">
</head>
<body>
    <div id="side-area">
        <!-- 로고 -->
        <div class="logo">
            <a href="<%= contextPath2 %>/home">
                <img src="resources/images/sis_logo.png" alt="logo" height="50px;">
            </a>
        </div>
        <!-- 마이페이지 헤더 -->
		<header>
            <div class="myPage-title"><p>마이페이지</p></div>
        </header>
        <!-- 마이페이지 메뉴 프로필 -->
		<div id="img-area"> 
			<% if(loginUser.getProfilePath() == null) { %>
					<div class="material-icons">account_circle</div>
			<% } else { %>
				<img src="<%= contextPath2 %>/<%= loginUser.getProfilePath() %>" id="profile-img">
			<% } %>
            <div class="profile-name"><p><%= loginUser.getUserName() %></p></div>
		</div>
        <!-- 마이페이지 메뉴바 영역 -->
		<div id="menu-area"> 
            <% if(currentPage.equals("mypageInfo")) { %>
            <a href="<%= contextPath2 %>/myInfo.list"  class="myPage-menu selected">
                <p>내정보</p>
            </a>
            <% } else { %>
            <a href="<%= contextPath2 %>/myInfo.list"  class="myPage-menu">
                <p>내정보</p>
            </a>
            <% } %>

            <% if(currentPage.equals("myATD")) { %>
            <a href="<%= contextPath2 %>/atd.list"  class="myPage-menu selected">
                <p>출결/휴가</p>
            </a>
            <% } else { %>
            <a href="<%= contextPath2 %>/atd.list"  class="myPage-menu">
                <p>출결/휴가</p>
            </a>
            <% } %>

            <% if(currentPage.equals("myScore")) { %>
            <a href="<%= contextPath2 %>/myScore.list"  class="myPage-menu selected">
                <p>시험점수</p>
            </a>
            <% } else { %>
            <a href="<%= contextPath2 %>/myScore.list"  class="myPage-menu">
                <p>시험점수</p>
            </a>
            <% } %>
		</div>
		
	</div>

</body>
</html>