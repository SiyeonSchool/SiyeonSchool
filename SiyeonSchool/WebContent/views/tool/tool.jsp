<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/tool.css">
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<main>

		<section>
			<h2>룰렛 돌리기</h2>
			<img src="<%= contextPath %>/resources/images/tool/roulette.png" alt="" width="70%">
			<div class="section-behind"></div>
		</section>

		<section>
			<h2>설문조사</h2>
			<img src="<%= contextPath %>/resources/images/tool/survey.png" alt="" width="85%">
			<div class="section-behind"></div>
		</section>

		<section>
			<h2>팀 정하기</h2>
			<img src="<%= contextPath %>/resources/images/tool/setTeam.png" alt="" width="65%">
			<div class="section-behind"></div>
		</section>

	</main>

</body>
</html>