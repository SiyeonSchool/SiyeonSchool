<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/calendar.css">
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="container">
		<div class="side">
			<span id="menu"><span class="material-icons-outlined">edit_calendar</span> 일정 관리</span>
			<ul>
				<li>
					<button class="toggle-btn" onclick="toggleSubMenu('solo')">
						<span class="material-symbols-outlined">person</span>
						<span>개인</span>
						<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
					</button>
					<ul class="sub" id="solo">
						<li>일정표</li>
						<li>할 일</li>
					</ul>
				</li>
				<li>
					<button class="toggle-btn" onclick="toggleSubMenu('team')">
						<span class="material-symbols-outlined">groups</span>
						<span>팀</span>
						<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
					</button>
					<ul class="sub" id="team">
						<li>일정표</li>
						<li>할 일</li>
					</ul>
				</li>
				<li>
					<button>
						<span class="material-symbols-outlined">school</span>
						<span>클래스룸</span>
						<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
					</button>
					<ul>
						<li>할 일</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="header">
				<span>To Do list</span>
			</div>
			<div class="todo">
				<div class="todoList" id="leftToDo">
					<input type="text">
					<button id="toDo-add">
						<span class="material-icons-outlined">add_circle</span>
					</button>
				</div>
				<div class="todoList" id="rightToDo">

				</div>
			</div>
		</div>
		
	</div>

	<script>
		function toggleSubMenu(menuId) {
      const subMenu = document.getElementById(menuId);
      if (subMenu.style.display === 'block') {
        subMenu.style.display = 'none';
      } else {
        subMenu.style.display = 'block';
      }
    }
	</script>
	
</body>
</html>