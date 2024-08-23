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

    <%@ include file="../common/myPageMenu.jsp" %>
    
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

    <section id="myInfo-area">
        <div class="header">내정보</div>
        <div class="body">
           <table>
            <tr>
                <th>이름</th>
                <td><%= userName %></td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td><%= phone %></td>
            </tr>
            <tr>
                <th>생년월일</th>
                <td><%= birthday %></td>
            </tr>
            <tr>
                <th>주소</th>
                <td><%= address %></td>
            </tr>
            <tr>
                <th>이메일</th>
                <td><%= email %></td>
            </tr>
            <tr>
                <th>나의 깃허브</th>
                <td>
                <% if(githubUrl==null){ %>
                등록된 깃허브주소가 없습니다.
                <% }else{ %>                
                <%= githubUrl %>
                <% } %>
                </td>
            </tr>
            <tr>
            	<th>나의 노션</th>
            	<td><%= notionUrl %></td>
            </tr>
            <tr>
            	<th>나의 아이디</th>
            	<td><%= userId %></td>
            </tr>
           </table>
        </div>
    </div>

</body>
</html>