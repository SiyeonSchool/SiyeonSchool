<%@page import="com.kh.home.model.vo.Curriculum"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Curriculum> list = (ArrayList<Curriculum>)request.getAttribute("list");
	int progressValue = Integer.parseInt(String.valueOf(request.getAttribute("progressValue")));
%>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/home.css">
	<script src="resources/js/home.js"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	<!-- 커리큘럼 바 -->
	<section class="curriculum">
		<div class="curriculum-bnt">
			<!-- JAVA 버튼 -->
			<% if(list.get(0).getCbState().equals("Y")) { %>
				<div class="cir java-cir selected">JAVA</div>
			<% } else { %>
				<div class="cir java-cir" onclick="updateState('JAVA')">JAVA</div>
			<% } %>
	
			<!-- ORACLE 버튼 -->
			<% if(list.get(1).getCbState().equals("Y")) { %>
				<div class="cir oracle-cir selected">ORACLE</div>
			<% } else { %>
				<div class="cir oracle-cir" onclick="updateState('ORACLE')">ORACLE</div>
			<% } %>
	
			<!-- JDBC 버튼 -->
			<% if(list.get(2).getCbState().equals("Y")) { %>
				<div class="cir jdbc-cir selected">JDBC</div>
			<% } else { %>
				<div class="cir jdbc-cir" onclick="updateState('JDBC')">JDBC</div>
			<% } %>
	
			<!-- HTML 버튼 -->
			<% if(list.get(3).getCbState().equals("Y")) { %>
				<div class="cir html-cir selected">HTML</div>
			<% } else { %>
				<div class="cir html-cir" onclick="updateState('HTML')">HTML</div>
			<% } %>
	
			<!-- CSS 버튼 -->
			<% if(list.get(4).getCbState().equals("Y")) { %>
				<div class="cir css-cir selected">CSS</div>
			<% } else { %>
				<div class="cir css-cir" onclick="updateState('CSS')">CSS</div>
			<% } %>
	
			<!-- JS 버튼 -->
			<% if(list.get(5).getCbState().equals("Y")) { %>
				<div class="cir js-cir selected">JS</div>
			<% } else { %>
				<div class="cir js-cir" onclick="updateState('JS')">JS</div>
			<% } %>
	
			<!-- jQuery 버튼 -->
			<% if(list.get(6).getCbState().equals("Y")) { %>
				<div class="cir jQuery-cir selected">jQuery</div>
			<% } else { %>
				<div class="cir jQuery-cir" onclick="updateState('jQuery')">jQuery</div>
			<% } %>
	
			<!-- Servlet 버튼 -->
			<% if(list.get(7).getCbState().equals("Y")) { %>
				<div class="cir servlet-cir selected">Servlet</div>
			<% } else { %>
				<div class="cir servlet-cir" onclick="updateState('Servlet')">Servlet</div>
			<% } %>
	
			<!-- JSP 버튼 -->
			<% if(list.get(8).getCbState().equals("Y")) { %>
				<div class="cir jsp-cir selected">JSP</div>
			<% } else { %>
				<div class="cir jsp-cir" onclick="updateState('JSP')">JSP</div>
			<% } %>
	
			<!-- AJAX 버튼 -->
			<% if(list.get(9).getCbState().equals("Y")) { %>
				<div class="cir ajax-cir selected">AJAX</div>
			<% } else { %>
				<div class="cir ajax-cir" onclick="updateState('AJAX')">AJAX</div>
			<% } %>
	
			<!-- myBatis 버튼 -->
			<% if(list.get(10).getCbState().equals("Y")) { %>
				<div class="cir myBatis-cir selected">myBatis</div>
			<% } else { %>
				<div class="cir myBatis-cir" onclick="updateState('myBatis')">myBatis</div>
			<% } %>
	
			<!-- Spring 버튼 -->
			<% if(list.get(11).getCbState().equals("Y")) { %>
				<div class="cir spring-cir selected">Spring</div>
			<% } else { %>
				<div class="cir spring-cir" onclick="updateState('Spring')">Spring</div>
			<% } %>
		</div>
	
		<div class="progress-area">
			<div class="progress-container">
				<div class="progress-bar" style="width: <%= progressValue %>%"></div>
			</div>
		</div>
	</section>
	
	<!-- Form 태그 -->
	<form id="updateForm" method="POST" action="<%= contextPath2 %>/update.cb" style="display: none;">
		<input type="hidden" name="subject" value="">
		<input type="hidden" name="state" value="Y">
	</form>
	
	<!-- 게시판들 -->
	<section class="boards">
		<!-- 수업자료 -->
		<section class="classDatum">
			<table>
				<tr>
					<td colspan="3">수업자료</td>
				</tr>
				<tr class="navi">
					<th>제목</th>
					<th>게시일</th>
					<th>댓글</th>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">insert_comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
			</table>
		</section>
		<!-- 과제 -->
		<section class="homework">
			<table>
				<tr>
					<td colspan="3">과제</td>
				</tr>
				<tr class="navi">
					<th>제목</th>
					<th>게시일</th>
					<th>댓글</th>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">insert_comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
				<tr>
					<td class="title">Java</td>
					<td class="enroll-date">2024-01-01</td>
					<td class="comments"><span class="material-icons">comment</span></td>
				</tr>
			</table>
		</section>
	</section>
	<!-- 달력/일정 -->
	<section class="todo">
		<div class="calendar-area">
			<div class="wrapper"></div>
			<div id="calendar"></div>
		</div>
		
		<div class="todo-area">
			<div class="todo-header">
				
			</div>
		</div>
	</section>

	<!-- 종강 D-Day -->
	<section class="D-day">
		<div class="text-area">
			<div class="end-day">
				<p>종강일 : 2024년 10월 25일</p>
			</div>
			<div id="D-day"></div>
		</div>
		<div class="progress-area2">
			<div class="progress-circle">
				<span class="progress-text">0%</span>
			</div>
		</div>
		<div class="comment-area">

		</div>
	</section>

	<!-- 출석 버튼 -->
	<section class="attendance-btn">
		<div class="atd-btn">
			<p>출석하기</p>
		</div>
	</section>

	<!-- 메일 -->
	<section class="mail-tome">
		<div class="total-area">
			<div class="mail-header">
				<p>메일</p>
			</div>
			<div class="mail-box">
				<table>
					<tr class="nothing">
						<td colspan="2">받은 메일함이 비어있습니다</td>
					</tr>
				</table>
			</div>
		</div>
	</section>

	<script>
		
	</script>
</body>
</html>