<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<%@ include file="../common/menubar.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="resources/css/homework_create.css">
    </head>
    <body>
        <div id="container">
            <%@ include file="sidebar.jsp" %>
            <div id="homework_create_content">
                <div id="homework_btn">
                    <button type="button">작성하기</button>
                    <button type="button">임시저장</button>
                    <button type="button" id="cantsle" onclick="history.back()">취소</button>
                </div>
                <div id="homework_create">
                    <span>과제 만들기</span>
                </div>
            </div>
        </div>
   
</body>
</html>