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
            <ul>
                <li>
                    <% if(currentPage.equals("mypageInfo")) { %>
                        <a href="<%= contextPath2 %>/myInfo.list"  class="myPage-menu selected">
                            <span>내정보</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    <% } else { %>
                        <a href="<%= contextPath2 %>/myInfo.list"  class="myPage-menu">
                            <span>내정보</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    <% } %>
                </li>
                <li>
                    <% if(currentPage.equals("myATD")) { %>
                        <a href="<%= contextPath2 %>/atd.list"  class="myPage-menu selected">
                            <span>출결/휴가</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                        <% } else { %>
                        <a href="<%= contextPath2 %>/atd.list"  class="myPage-menu">
                            <span>출결/휴가</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                        <% } %>
                </li>
                <li>
                    <% if(currentPage.equals("myScore")) { %>
                        <a href="<%= contextPath2 %>/myScore.list"  class="myPage-menu selected">
                            <span>시험점수</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                        <% } else { %>
                        <a href="<%= contextPath2 %>/myScore.list"  class="myPage-menu">
                            <span>시험점수</span>
                            <span class="material-icons">navigate_next</span>
                        </a>
                    <% } %>
                </li>
            </ul>
        </div>
        </div>
</body>
</html>