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

    <div id="myInfo-area">
        <div class="header">내정보</div>
        <div class="body">
           <table>
            <tr>
                <th>아이디</th>
                <td>${sessionScope.loginUser.id}</td>
            </tr>
            <tr>
                <th>이름</th>
                <td>${sessionScope.loginUser.name}</td>
            </tr>
            <tr>
                <th>생년월일</th>
                <td>${sessionScope.loginUser.birth}</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${sessionScope.loginUser.gender}</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${sessionScope.loginUser.email}</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${sessionScope.loginUser.phone}</td>
            </tr>
            <tr>
                <th>
           </table>
        </div>
    </div>

</body>
</html>