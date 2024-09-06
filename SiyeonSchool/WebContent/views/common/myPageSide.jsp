<%@page import="com.google.gson.Gson"%>
<%@page import="com.kh.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/myPageSide.css">
<title>Insert title here</title>
</head>
<body>
	<div class="myPage__header"><h1 class="content__title">마이페이지</h1></div>
    <div class="side-area">
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
            <ul class="side_nav">
                <% if(currentPage.equals("mypageInfo")) { %>
                    <li class="myPage-menu selected">
                        <a href="<%= contextPath2 %>/myInfo.list" >
                            <span>내정보</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    </li>
                <% } else { %>
                    <li class="myPage-menu none">
                        <a href="<%= contextPath2 %>/myInfo.list">
                            <span>내정보</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    </li>
                <% } %>
                <% if(currentPage.equals("myATD")) { %>
                    <li class="myPage-menu selected">
                        <a href="<%= contextPath2 %>/atd.list">
                            <span>출결/휴가</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    </li>
                <% } else { %>
                    <li class="myPage-menu none">
                        <a href="<%= contextPath2 %>/atd.list">
                            <span>출결/휴가</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    </li>
                <% } %>
                <% if(currentPage.equals("myScore")) { %>
                    <li class="myPage-menu selected none">
                        <a href="<%= contextPath2 %>/myScore.list">
                            <span>시험점수</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    </li>
                <% } else { %>
                    <li class="myPage-menu none">
                        <a href="<%= contextPath2 %>/myScore.list">
                            <span>시험점수</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    </li>
                <% } %>
            </ul>
        </div>
        </div>
</body>
</html>