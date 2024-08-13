<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/myPage.css">
	<style>
		#side-area {
			float: left;
            width: 485px;
			height: 100%;
            background-color: #ffffff;
            padding: 10px;
            margin-right: 10px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
			position: fixed;
		}

		h1{
			color: #000;
		}

		#img-area{
			width: 100%;
            height: 150px;
            margin-top: 50px;
            margin-bottom: 100px;
            display: flex;
            justify-content: center;
            align-items: center;
		}

		#profile-img{
			width: 150px;
			height: 150px;
			display: inline;
		}

		#menu-area{
			display: flex;
			margin-top: 50px;
			flex-direction: column;
			align-items: center;	
		}

		input:hover{
			background-color: #C2F0FF;
            cursor: pointer;
		}

		.myPage-menu{
			width: 255px;
			height: 50px;
			background-color: #C2F0FF;
			display: flex;
			margin-top: 50px;
			border-radius: 15px;
			border: none;
			font-size: 32px;
		}

		#myInfo-area{
			display: flex;
			flex-direction: column;
			position: absolute;
			top: 40px;
			right: 200px;
		}

		#myInfo-area>*{
			width: 1000px;
			height: 300px;
			display: flex;
			border-radius: 15px;
			border: 3px solid #C2F0FF;
			margin-bottom: 50px;
		}

		.header{
			width: 100%;
			height: 50px;
			background : linear-gradient(to right, #46D3FF, #D0BFFF);
			display: flex;
			align-items: center;
			border-radius: 12px 12px 0px 0px;
		}
	</style>
</head>
<body>

	<div id="side-area">
		<h1 align="center">마이페이지</h1>
		<div id="img-area">
			<img src="resources/images/myPage/account_circle_24dp_B7B7B7.png" id="profile-img">
			<p align="center">차은우</p>
		</div>
		<div id="menu-area">
			<input type="button" class="myPage-menu" value="내정보"></input>
			<input type="button" class="myPage-menu" value="출결/휴가"></input>
			<input type="button" class="myPage-menu" value="시험점수"></input>
		</div>
		
	</div>

	
	<div id="myInfo-area">
		<div id="myInfo">
			<div class="header">내정보</div>
			<div class="body"></div>
		</div>
		<div id="myATD">
			<div class="header">출결 / 휴가</div>
			<div class="body"></div>
		</div>
		<div id="myScore">
			<div class="header">시험점수</div>
			<div class="body"></div>
		</div>
	</div>
	
</body>
</html>