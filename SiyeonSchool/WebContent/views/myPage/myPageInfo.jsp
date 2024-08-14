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
    %>

    <div id="myInfo-area">
        <div class="header">내정보</div>
        <div class="body">
           <table>
            <tr>
                <th>이름</th>
                <td></td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td></td>
            </tr>
            <tr>
                <th>생년월일</th>
                <td></td>
            </tr>
            <tr>
                <th>주소</th>
                <td></td>
            </tr>
            <tr>
                <th>이메일</th>
                <td></td>
            </tr>
            <tr>
                <th>나의 깃허브</th>
                <td></td>
            </tr>
            <tr>
            	<th>나의 노션</th>
            	<td></td>
            </tr>
            <tr>
            	<th>나의 아이디</th>
            	<td></td>
            </tr>
           </table>
        </div>
    </div>

</body>
</html>