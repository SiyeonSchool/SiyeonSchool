<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/calendar.css">
	<script defer src="resources/js/calendar.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="container">
		<div class="side">
			<div id="menuName">
				<span id="menu"><span class="material-icons-outlined" style="margin-right: 33px;">edit_calendar</span>일정 관리</span>
			</div>
			<ul>
				<li>
					<button class="toggle-btn" onclick="toggleSubMenu('solo')">
						<span class="material-symbols-outlined">person</span>
						<span class="text">개인</span>
						<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
					</button>
					<ul class="sub" id="solo">
						<li>
							<button class="list"></button>
							<span>일정표</span>
						</li>
						<li>
							<button class="list"></button>
							<span>할 일</span>
						</li>
					</ul>
				</li>
				<li>
					<button class="toggle-btn" onclick="toggleSubMenu('team')">
						<span class="material-symbols-outlined">groups</span>
						<span class="text">팀</span>
						<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
					</button>
					<ul class="sub" id="team">
						<li>
							<button class="list"></button>
							<span>일정표</span>
						</li>
						<li>
							<button class="list"></button>
							<span>할 일</span>
						</li>
					</ul>
				</li>
				<li>
					<button class="toggle-btn" onclick="toggleSubMenu('class')">
						<span class="material-symbols-outlined">school</span>
						<span class="text">클래스룸</span>
						<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
					</button>
					<ul class="sub" id="class">
						<li>
							<button class="list"></button>
							<span>일정표</span>
						</li>
					</ul>
				</li>
			</ul>
			<button id="btn-today">Today</button>
		</div>
		<main id="main-content">
			<div id="calendar-view">
				
				<div class="calendar">
					<div class="month">
						<div class="prev">&#10094;</div>
						<div class="month-name">July 2024</div>
						<div class="next">&#10095;</div>
					  </div>
					  <div class="weekdays">
						<div>일</div>
						<div>월</div>
						<div>화</div>
						<div>수</div>
						<div>목</div>
						<div>금</div>
						<div>토</div>
					  </div>
					  <div class="days">
					  </div>
				</div>
			</div>
			<div id="todo-view" class="hidden">
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
		</main>
		
	</div>

	<!-- Modal -->
	<div id="eventModal" class="modal">
		<div class="modal-content">
		  <span class="close">&times;</span>
		  <h2>일정 추가</h2>
		  <form id="eventForm">
			<div class="form-group">
                <label for="eventTitle">제목 : </label>
                <input type="text" id="eventTitle" name="eventTitle" required>
            </div>
            <div class="form-group">
                <label for="eventDescription">설명 : </label>
                <textarea name="eventDescription" id="eventDescription" style="resize: none;" rows="5"></textarea>
            </div>
            <div class="form-group date-group">
				<div>
					<label for="eventStartDate">시작 날짜 : </label>
					<input type="date" id="eventStartDate" name="eventStartDate" required>
				</div>
				<div>
					<label for="eventEndDate">종료 날짜 : </label>
					<input type="date" id="eventEndDate" name="eventEndDate" required>
				</div>
			</div>
            <div class="form-group">
                <button type="submit">추가</button>
            </div>
		  </form>
		</div>
	  </div>
	
	
</body>
</html>