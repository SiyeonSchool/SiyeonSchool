<%@page import="com.kh.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/adminPageMenu.css">
</head>
<body>

	<div class="adminPage__header"><h1 class="content__title">관리자페이지</h1></div>
        <div class="side-area">
            <!-- 관리자페이지 메뉴 프로필 -->
            <div id="img-area"> 
                <% if(loginUser.getProfilePath() == null) { %>
                    <div class="material-icons">account_circle</div>
                <% } else { %>
                    <img src="<%= contextPath2 %>/<%= loginUser.getProfilePath() %>" id="profile-img">
                <% } %>
                <div class="profile-name"><p><%= loginUser.getUserName() %></p></div>
            </div>
            <!-- 관리자페이지 메뉴바 영역 -->
            <div id="menu-area">
                <ul>
                    <li>
                        <% if(currentPage.equals("sigInRequest")) { %>
                            <a href="<%= contextPath2 %>/sigInRequest.list"  class="admin-menu selected">
                                <span>회원가입 관리</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                        <% } else { %>
                            <a href="<%= contextPath2 %>/sigInRequest.list"  class="admin-menu">
                                <span>회원가입 관리</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                        <% } %>
                    </li>
                    <li>
                        <% if(currentPage.equals("myATD")) { %>
                            <a href="<%= contextPath2 %>/atd.list"  class="admin-menu selected">
                                <span>학생정보</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                            <% } else { %>
                            <a href="<%= contextPath2 %>/atd.list"  class="admin-menu">
                                <span>학생정보</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                            <% } %>
                    </li>
                    <li>
                        <% if(currentPage.equals("myScore")) { %>
                            <a href="<%= contextPath2 %>/myScore.list"  class="admin-menu selected">
                                <span>학생출결</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                            <% } else { %>
                            <a href="<%= contextPath2 %>/myScore.list"  class="admin-menu">
                                <span>학생출결</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                        <% } %>
                    </li>
                    <li>
                        <% if(currentPage.equals("myScore")) { %>
                            <a href="<%= contextPath2 %>/myScore.list"  class="admin-menu selected">
                                <span>학생 성적 관리</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                            <% } else { %>
                            <a href="<%= contextPath2 %>/myScore.list"  class="admin-menu">
                                <span>학생 성적 관리</span>
                                <span class="material-icons">navigate_next</span>
                            </a>
                        <% } %>
                    </li>
                </ul>
            </div>
            </div>

</body>
</html>