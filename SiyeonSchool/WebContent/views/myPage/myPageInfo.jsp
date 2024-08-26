<%@page import="com.kh.user.controller.LoginUserController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="resources/css/myPageInfo.css">
    </head>
    <body>
        <%@ include file="../common/menubar.jsp" %>

        <%
            String userName = loginUser.getUserName();
            String phone = loginUser.getPhone();
            String birthday = loginUser.getBirthday();
            String address = loginUser.getAddress();
            String email = loginUser.getEmail();
            String githubUrl = loginUser.getGithubUrl();
            String notionUrl = loginUser.getNotionUrl();
            String userId = loginUser.getUserId();
        %>
        <div class="wrapper">
            <section class="body__wrapper">
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
                                        <p>내정보</p>
                                    </a>
                                <% } else { %>
                                    <a href="<%= contextPath2 %>/myInfo.list"  class="myPage-menu">
                                        <p>내정보</p>
                                    </a>
                                <% } %>
                            </li>
                            <li>
                                <% if(currentPage.equals("myATD")) { %>
                                    <a href="<%= contextPath2 %>/atd.list"  class="myPage-menu selected">
                                        <p>출결/휴가</p>
                                    </a>
                                    <% } else { %>
                                    <a href="<%= contextPath2 %>/atd.list"  class="myPage-menu">
                                        <p>출결/휴가</p>
                                    </a>
                                    <% } %>
                            </li>
                            <li>
                                <% if(currentPage.equals("myScore")) { %>
                                    <a href="<%= contextPath2 %>/myScore.list"  class="myPage-menu selected">
                                        <p>시험점수</p>
                                    </a>
                                    <% } else { %>
                                    <a href="<%= contextPath2 %>/myScore.list"  class="myPage-menu">
                                        <p>시험점수</p>
                                    </a>
                                <% } %>
                            </li>
                        </ul>
                    </div>
                 </div>
        </section>
    </div>
</body>
</html>