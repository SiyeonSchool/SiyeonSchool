<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/home.css">
	<script src="resources/js/home.js"></script>
<style>
	.boards{
		width: auto;
		height: auto;
		justify-content: center;
		flex-direction: column;
		float: left;
		margin-right: 100px;
	}
	.calendar{
		width: 500px;
		height: auto;
		display: flex;
		flex-direction: column;
		margin-top: 80px;
	}

	#calendar table{
		width: 500px;
		height: 300px;
	}

		#calendar *{
			border: 1px solid #000;
		}

	.header{
		margin-bottom: 50px;
		padding : 0px 100px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 24px;
	}

	.header span:hover{
		cursor: pointer;
	}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	<!-- 커리큘럼 바 -->
	<section  class="curriculum">
		<div class="curriculum-bnt">
			<div class="cir java-cir">JAVA</div>
			<div class="cir oracle-cir">ORACLE</div>
			<div class="cir jdbc-cir">JDBC</div>
			<div class="cir html-cir">HTML</div>
			<div class="cir css-cir">CSS</div>
			<div class="cir js-cir">JS</div>
			<div class="cir jQuery-cir">jQuery</div>
			<div class="cir servlet-cir">Servlet</div>
			<div class="cir jsp-cir">JSP</div>
			<div class="cir ajax-cir">AJAX</div>
			<div class="cir myBatis-cir">myBatis</div>
			<div class="cir spring-cir">Spring</div>
		</div>
		<div class="progress-area">
			<div class="progress-container">
				<div class="progress-bar"></div>
			</div>
		</div>
	</section>
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
			</table>
		</section>
	</section>
	<!-- 달력 -->
	<section class="calendar">
		<div class="wrapper"></div>
		<div id="calendar"></div>
	</section>

	<script>
		
	</script>
</body>
</html>