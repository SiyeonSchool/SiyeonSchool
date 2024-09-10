<%@page import="com.kh.user.controller.LoginUserController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="resources/css/myPageInfo.css">
    </head>
    <body>
        <%@ include file="../common/menubar.jsp" %>
        
        <%
        String userName = loginUser.getUserName();
            String phone = loginUser.getPhone();
            String birthday = loginUser.getBirthday();
            String address = loginUser.getAddress();
            String email = loginUser.getEmail();
            String githubUrl = loginUser.getGithubUrl();
            String notionUrl = loginUser.getNotionUrl();
            String userId = loginUser.getUserId();
            int userNo = loginUser.getUserNo();
        %>
            <div class="wrapper">
                <section class="body__wrapper">
                    <%@ include file="../common/myPageSide.jsp" %>
                    
                    <div class="myInfo__body">
                        <div class="myInfo__header"><p>내정보</p></div>
                        <div class="myInfo__transBtn_area">
                             <button class="myInfo__transBtn" id="openModal">정보변경</button>
                        </div>
                        <div class="myInfo__content">
                            <table>
                                <tr>
                                    <td>이름</td>
                                    <td><%= userName %></td>
                                </tr>
                                <tr>
                                    <td>전화번호</td>
                                    <td><%= phone %></td>
                                </tr>
                                <tr>
                                    <td>생년월일</td>
                                    <td><%= birthday %></td>
                                </tr>
                                <tr>
                                    <td>주소</td>
                            <td><%= address %></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td><%= email %></td>
                        </tr>
                        <tr>
                            <td>깃허브주소</td>
                            <% if(githubUrl == null) { %>
                                <td>등록된 깃허브 주소가 없습니다.</td>
                            <% }else{ %>
                                <td><a href="<%= githubUrl %>" class="myGithub">나의 깃허브</a></td>
                                <% } %>
                            </tr>
                            <tr>
                                <td>노션주소</td>
                                <% if(notionUrl == null){ %>
                                    <td>등록된 노션 주소가 없습니다.</td>
                                    <% }else{ %>
                                        <td><a href="<%= notionUrl %>" class="myNotion">나의 노션</a></td>
                                        <% } %>
                                    </tr>
                        <tr>
                            <td>아이디</td>
                            <td><%= userId %></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="infoModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <h2>정보 변경</h2>
                    <form id="infoForm" action="<%= contextPath2 %>/myInfo.update" method="post">
                        <input type="hidden" name="userNo" value="<%= userNo %>">
                        <label for="userName">이름:</label>
                        <input type="text" id="userName" name="userName" value="<%= userName %>">
                        <br>
                        <label for="phone">전화번호:</label>
                        <input type="text" id="phone" name="phone" value="<%= phone %>">
                        <br>
                        <label for="address">주소:</label>
                        <input type="text" id="address" name="address" value="<%= address %>">
                        <br>
                        <label for="email">이메일:</label>
                        <input type="email" id="email" name="email" value="<%= email %>">
                        <br>
                        <label for="githubUrl">깃허브 주소:</label>
                        <input type="text" id="githubUrl" name="githubUrl" value="<%= githubUrl != null ? githubUrl : "" %>">
                        <br>
                        <label for="notionUrl">노션 주소:</label>
                        <input type="text" id="notionUrl" name="notionUrl" value="<%= notionUrl != null ? notionUrl : "" %>">
                        <br>
                        <button type="submit">정보 변경하기</button>
                    </form>
                </div>
            </div>
        </section>
        <script src="./resources/js/myPageInfo.js"></script>
    </div>
</body>
</html>