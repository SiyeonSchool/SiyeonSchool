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
<script src="resources/js/sigInRequest.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
        
    <div class="wrapper">
        <section class="body__wrapper">
            <%@ include file="../common/adminPageMenu.jsp" %>

            <div class="signInRequest__body">
                <div class="signInRequest__header"><p>회원가입 관리</p></div>
                <div class="signInRequest__content">
                    <table>
                        <% if(list != null && list.size() > 0){ %>
                            <% for(int i = 0; i < list.size(); i++){ 
                                User user = list.get(i);
                            %>
                                <tr>
                                    <td><%= user.getUserName() %>님이 회원가입을 요청했습니다.</td>
                                    <td> <!-- 왜인진 모르겠지만 버튼으로 데이터 호출시 데이터명은 낙타표기법 안됨 -->
                                        <button 
                                            class="infoBtn" 
                                            data-username="<%= user.getUserName() %>" 
                                            data-birth="<%= user.getBirthday() %>"
                                            data-email="<%= user.getEmail() %>" 
                                            data-phone="<%= user.getPhone() %>" 
                                            data-address="<%= user.getAddress() %>"
                                            data-userno="<%= user.getUserNo() %>"
                                        >
                                            정보확인
                                        </button>
                                    </td>
                                </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="2">회원가입 요청이 없습니다.</td>
                            </tr>
                        <% } %>
                    </table>
                    <div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-labelledby="userInfoModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                          <div class="modal-content">
                            <form action="<%= contextPath2 %>/update.st" method="post">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="userInfoModalLabel">회원 정보</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="닫기">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" name="userNo" id="modalUserNo">
                                    <p><strong>이름:</strong> <span id="modalUsername"></span></p>
                                    <p><strong>생년월일:</strong> <span id="modalUserBirthday"></span></p>
                                    <p><strong>이메일:</strong> <span id="modalEmail"></span></p>
                                    <p><strong>전화번호:</strong> <span id="modalPhone"></span></p>
                                    <p><strong>주소:</strong> <span id="modalAddress"></span></p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                                    <button type="submit" class="btn btn-success" id="approveBtn">승인</button>
                                    <button type="button" class="btn btn-danger" id="rejectBtn">거절</button>
                                </div>
                            </form>
                          </div>
                        </div>
                      </div>
                </div>
            </div>
        </section>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>