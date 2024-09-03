<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kh.homework.model.vo.Subject" %>
<%@ page import="com.kh.homework.model.service.HomeworkService" %>
<%
    HomeworkService homeworkService = new HomeworkService();
    ArrayList<Subject> subjectList  = homeworkService.selectSubject();
%>

	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="resources/css/sidebar.css">
		<script defer src="resources/js/homework_sidebar.js"></script>
	</head>

	<body>
	
		<div id="side">
			<div class="sidebar" id="side1">
				<ul>
					<li>
						<span>곧 마감되는 과제</span><br>
					</li>
					<li>
						<a href=""><span>기한 : 요일</span></a>
					</li>
				</ul>
			</div>
			<div class="sidebar" id="side2">
				<ul>
					<li id="title">
						<span id="listTitle">과제</span>
						<span class="material-symbols-rounded icon" id="addIcon">add</span>
					</li>
				</ul>
				<ul id="assignmentList">
					<% for(Subject subject : subjectList) { %>
					<li class="sideList">
						<span class="material-symbols-outlined">folder</span>
						<span class="subject"><%= subject.getSubjectName() %></span>
						<span class="material-symbols-rounded icon edit">edit</span>
						<span class="material-symbols-outlined">delete</span>
					</li>
					<% } %>
				</ul>
			</div>
			<div class="sidebar" id="side3">
				<ul>
					<li>
						<span class="icon mailboxNo-icon material-icons-outlined">note</span>
						<span>임시보관함</span><span>(0)</span>
					</li>
				</ul>
				<ul>
					<li id="create">
						<a href="<%=contextPath%>/create.homework">과제 만들기</a>
					</li>
				</ul>
			</div>
		</div>
	
		<div id="myModal" class="modal">
			<div class="modal-content">
				<span class="close">&times;</span>
				<h3>새 폴더 추가</h3>
				<input type="text" id="newFolderName" placeholder="폴더명을 입력하세요">
				<button id="addFolderButton">추가</button>
			</div>
		</div>
	</body>

	</html>