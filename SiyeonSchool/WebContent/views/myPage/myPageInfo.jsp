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
            <%@ include file="../common/myPageSide.jsp" %>

            <div class="myInfo__body">
                <div class="myInfo__header"><p>내정보</p></div>
                <div class="myInfo__transBtn_area"><a href="#" class="btn btn-sm btn-primary myInfo__transBtn">정보변경</a></div>
                <div class="myInfo__content">
                    <table>
                        <tr>
                            <td>이름</td>
                            <td><%= userName %></td>
                        </tr>
                        <tr>
                            <td>전화번호</td>
                            <td><%= phone %></td>
                        </tr>
                        <tr>
                            <td>생년월일</td>
                            <td><%= birthday %></td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td><%= address %></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td><%= email %></td>
                        </tr>
                        <tr>
                            <td>깃허브주소</td>
                            <% if(githubUrl == null) { %>
                            <td>등록된 깃허브 주소가 없습니다.</td>
                            <% }else{ %>
                            <td><a href="<%= githubUrl %>">나의 깃허브</a></td>
                            <% } %>
                        </tr>
                        <tr>
                            <td>노션주소</td>
                            <% if(notionUrl == null){ %>
                            <td>등록된 노션 주소가 없습니다.</td>
                            <% }else{ %>
                            <td><a href="<%= notionUrl %>">나의 노션</a></td>
                            <% } %>
                        </tr>
                        <tr>
                            <td>아이디</td>
                            <td><%= userId %></td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
</body>
</html>