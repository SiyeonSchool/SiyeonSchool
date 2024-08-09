<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String contextPath2 = request.getContextPath(); // "/SiS" // common.jsp와 중복을 피하기위해 뒤에 숫자2 추가함.

	String currentPage = (String)request.getSession().getAttribute("currentPage");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<!-- 메뉴바 CSS -->
	<link rel="stylesheet" href="resources/css/menubar.css">
</head>
<body>

    <header>
        <div class="logo">
            <img src="resources/images/sis_logo.png" alt="" height="60px;"> <!-- 로고 넣기-->
        </div>

        <div class="main-menu">
            <!-- 아이콘 이미지 크기가 다른것은 인라인 스타일로 조정함 -->
            <a href="<%= contextPath2 %>/home">
            	<% if(currentPage.equals("home")) { %>
 	  	            <img src="resources/images/menubar/home_sel.png" alt="홈버튼" style="height:45px; padding: 5px;">
					<!-- <div class="grey-menu material-icons-round" style="color: var(--darkSkyBlue);">home</div> -->
            	<% } else { %>
		            <div class="grey-menu material-icons-round">home</div>
            	<% } %>
            </a>
            <a href="<%= contextPath2 %>/mail">
            	<% if(currentPage.equals("mail")) { %>
	                <img src="resources/images/menubar/mail_sel.png" alt="메일버튼" style="height:48px; padding: 3px;">
            	<% } else { %>
	                <div class="grey-menu material-icons-round">email</div>
            	<% } %>
            </a>
            <a href="<%= contextPath2 %>/calendar">
                <% if(currentPage.equals("calendar")) { %>
	                <img src="resources/images/menubar/todo_sel.png" alt="일정버튼">
            	<% } else { %>
	                <div class="grey-menu material-icons-round">edit_calendar</div>
            	<% } %>
            </a>
            <a href="<%= contextPath2 %>/contacts">
                <% if(currentPage.equals("contacts")) { %>
                	<img src="resources/images/menubar/add_sel.png" alt="주소록버튼">
            	<% } else { %>
	                <div class="grey-menu material-icons-round">people</div>
            	<% } %>
            </a>
            <a href="<%= contextPath2 %>/class">
                <% if(currentPage.equals("class")) { %>
	                <img src="resources/images/menubar/class_sel.png" alt="수업자료버튼" style="height:49px; padding: 3px;">
            	<% } else { %>
	                <div class="grey-menu material-symbols-outlined">book_2</div>
            	<% } %>
            </a>
            <a href="<%= contextPath2 %>/homework">
                <% if(currentPage.equals("homework")) { %>
              	  <img src="resources/images/menubar/homework_sel.png" alt="과제버튼" style="height:47px; padding: 4px;">
            	<% } else { %>
	                <div class="grey-menu material-icons-outlined">assignment</div>
            	<% } %>
            </a>
            <a href="<%= contextPath2 %>/tool">
                <% if(currentPage.equals("tool")) { %>
	                <img src="resources/images/menubar/equipment_sel.png" alt="도구버튼">
            	<% } else { %>
	                <div class="grey-menu material-icons-round">category</div>
            	<% } %>
            </a>
        </div>

        <div class="right-menu">
            <div class="material-icons-outlined">notifications</div>
			<div class="profile">
				<div class="material-icons">account_circle</div>
				<div class="profile-name">차은우</div>
				<div class="material-symbols-rounded">keyboard_arrow_down</div>
			</div>
        </div>
    </header>
    

</body>
</html>