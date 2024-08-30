<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="resources/css/sidebar.css">
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
						<span class="material-symbols-rounded icon">add</span>
					</li>
				</ul>
				<ul>
					<li class="sideList">
						<span class="material-symbols-outlined">folder</span>
						<span class="subject">Java</span>
						<span class="material-symbols-rounded icon edit">edit</span>
						<span class="material-symbols-outlined">delete</span>
					</li>
					<li class="sideList">
						<span class="material-symbols-outlined">folder</span>
						<span class="subject">Oracle</span>
						<span class="material-symbols-rounded icon edit">edit</span>
						<span class="material-symbols-outlined">delete</span>
					</li>
					<li class="sideList">
						<span class="material-symbols-outlined" class="folder">folder</span>
						<span class="subject">Front-end</span>
						<span class="material-symbols-rounded icon edit">edit</span>
						<span class="material-symbols-outlined" class="folder">delete</span>
					</li>
					<li class="sideList">
						<span class="material-symbols-outlined">folder</span>
						<span class="subject">세미 프로젝트</span>
						<span class="material-symbols-rounded icon edit">edit</span>
						<span class="material-symbols-outlined">delete</span>
					</li>
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
	</body>

	</html>