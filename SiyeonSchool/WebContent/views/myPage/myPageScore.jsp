<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/myPageScore.css">
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

            <div id="myScore-area">
                <div class="header">시험점수</div>
                <div class="body"></div>
            </div>
        </section>
    </div>
	
</body>
</html>