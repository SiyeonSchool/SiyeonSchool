<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<%
	ArrayList<User> list = (ArrayList<User>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/signInRequest.css">
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
        
    <div class="wrapper">
        <section class="body__wrapper">
            <%@ include file="../common/adminPageMenu.jsp" %>

            <script>
                const list = `<%= list %>`
            </script>

            <div class="myInfo__body">
                <div class="signInRequest__header"><p>회원가입 관리</p></div>
                <div class="signInRequest__content">
                    <table>
                       <% if(list.size()>0){ %>
                       		<%for(int i=0 ; i<list.size() ; i++){ %>
                       			<tr>
                                    <td><%= list.get(i).getUserName() %>님이 회원가입을 요청했습니다.</td>
                                    <td><button id="myBtn">모달버튼</button></td>
                                    
                                </tr>
                                div
                       		<% } %>
                       <% }else{ %>
                       
                       <% } %>
                    </table>
                </div>
            </div>
        </section>
</body>
</html>