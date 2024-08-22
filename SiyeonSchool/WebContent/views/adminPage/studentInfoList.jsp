<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<%
	ArrayList<User> list = (ArrayList<User>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/studentInfoList.css">
</head>
<body>

	<%@ include file="../common/adminPageMenu.jsp" %>
	<div id="studentList-area">
        <ul>
            <li><div>학생 정보</div></li>
            <% for (User u : list) { %>
                <li>
                    <div><%= u.getUserName() %></div>
                    <div><%= u.getPhone() %></div>
                    <div><a href="<%= contextPath %>/studentInfo.dt" class="btn">자세히</a></div>
                </li>
            <% } %>
        </ul>
    </div>
	

</body>
</html>