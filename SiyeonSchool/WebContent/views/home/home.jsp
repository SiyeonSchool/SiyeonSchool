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
	.mail-tome{
		width: 230px;
		height: 460px;
		margin-top: 80px;
		display: flex;	
	}

	.total-area{
		width: 230px;
		height: 460px;
		border: 1px solid #C2F0FF;
		border-radius: 10px;
	}

	.mail-header{
		width: 230px;
		height: 60px;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 24px;
		color: #46D3FF;
		border-bottom: 1px solid #C2F0FF;
	}

	.mail-box{
		width: 190px;
		height: 380px;
		margin-top: 20px;
	}

    .mail-box table{
        width: 230px;
        height: 380px;
    }

	.mail-box .nothing{
		text-align: center;
		vertical-align: middle;
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