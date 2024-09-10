<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/menubar.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="resources/css/homework.css">
	</head>
	<body>
	<div class="container">
		<%@ include file="sidebar.jsp" %>
		<div id="homework">
			<div class="content" id="header">
				<ul id="list-header">
					<li id="title">제목</li>
					<li id="endDate">제출기한</li>
					<li id="status">제출현황</li>
					<li id="reply">댓글</li>
					<li id="writer">작성자</li>
					<li id="createDate">작성일시</li>
				</ul>
			</div>
			<div class="content" id="content">
				<a href="<%=contextPath%>/detailView.homework">
					<ul id="list">
						<li id="title">[subject]homework_title</li>
						<li id="endDate">homework_end_date</li>
						<li id="status">status</li>
						<li id="reply">reply</li>
						<li id="writer">writer</li>
						<li id="createDate">create_date</li>
					</ul>
				</a>
			</div>
		</div>
	</div>
	
</body>
</html>