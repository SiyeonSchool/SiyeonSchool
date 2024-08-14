<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/myPage.css">
<style>
.countDay{
	position: absolute;
	right: 150px;
	top : 150px;
    margin-bottom: 17px;
}

#progress {
    appearance: none;
}

#progress::-webkit-progress-bar {
    background-color: #f2f2f2;
    border-radius: 12px;
    border: 1px solid #eeeeee;
    width: 250px;
    height: 18px;
    overflow: hidden;
}

#progress::-webkit-progress-value {
    background : #C2F0FF;
    border-radius: 0px;
    width: 48px;
    height: 18px;
}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="countDay">
		<progress class="progress" id="progress" min="0" max="100"></progress>
		<br>
		<span class="dDay"></span>

	</div>

	<script>
		setInterval(countDown, 100);

		function countDown() {
			let startDay = new Date('Fri May 09 2024 09:00:00 GMT+0900 (한국 표준시)');
			let today = new Date();
			let lastDay = new Date('Fri Oct 25 2024 18:00:00 GMT+0900 (한국 표준시)');
			
			let lstime = lastDay - startDay;
			//let lsDays = Math.ceil(lstime / (1000 * 60 * 60 * 24));

			let ltTime = lastDay - today;
			//let ltDays = Math.ceil(ltTime / (1000 * 60 * 60 * 24));

			//console.log(lstime)
			console.log(ltTime)

            $('#progress').attr('value',((lstime - ltTime) / lstime * 100).toPrecision(8));

			document.querySelector('.dDay').innerHTML = ((lstime - ltTime) / lstime * 100).toPrecision(8);

			// let today = new Date();

			// document.querySelector('.dDay').innerHTML = today;
		}
	</script>
	
</body>
</html>