<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/myPage.css">
<style>

#progress {
	position: absolute;
	right: 150px;
	top : 150px;
    margin-bottom: 17px;
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
	
	<progress class="progress" id="progress" min="0" max="100"></progress>

	<span class="dDay"></span>

	<script>
		$(function() {
			let progress = 0;
			let startDay = new Date('2024-05-09');
			let today = new Date();
			let lastDay = new Date('2024-10-26');
			
			let hour = today.getHours();
			let min = today.getMinutes();
			let sec = today.getSeconds();
			let msec = today.getMilliseconds();
			
			if(hour < 10){
				// 한자리 수 일 경우
				hour = "0" + hour;
			}
			if(min < 10){
				min = "0" + min;
			}
			if(sec < 10){
				sec = "0" + sec;
			}
			if(msec < 10){
				msec = "00" + msec;
			}else if(msec < 100){
				msec = "0" + msec;
			}
			
			let lstime = lastDay - startDay;
			let lsDays = Math.ceil(lstime / (1000 * 60 * 60 * 24));

			let ltTime = lastDay - today;
			let ltDays = Math.ceil(ltTime / (1000 * 60 * 60 * 24));


            $('#progress').attr('value', (lsDays - ltDays) / lsDays * 100);

			document.querySelector('.dDay').textContent = (lsDays - ltDays) / lsDays * 100;
			
		})
	</script>
	
</body>
</html>