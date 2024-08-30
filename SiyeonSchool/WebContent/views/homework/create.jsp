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
                <form action="<%=contextPath%>/insert.homework" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>">
                    <div id="homework_btn">
                        <button type="button">작성하기</button>
                        <button type="button">임시저장</button>
                        <button type="button" id="cantsle" onclick="history.back()">취소</button>
                    </div>
                    <div id="homework_create">
                        <span>과제 만들기</span>
                        <table>
                            <tr>
                                <th>제목</th>
                                <td><input type="text" name="title" required></td>
                            </tr>
                            <tr>
                                <th>구분</th>
                                <td>
                                    <select name="subject" id="" required>
                                        <option value=""></option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>제출기한</th>
                                <td><input type="date" name="endDate" id=""></td>
                            </tr>
                            <tr>
                                <th rowspan="3">제출대상자</th>
                                <td>
                                    <input type="text">
                                    <input type="radio" name="" id=""><label for="">전체학생</label>
                                    <input type="radio" name="" id=""><label>팀장</label>
                                </td>
                            </tr>
                          
                            <tr>
                                <td id="homework_select">
                                    <ul>
                                        <li>
                                            <input type="checkbox" style="float: left;"><span>대상자</span>
                                        </li>
                                    </ul>
                                    <ul id="homework_contacts"> <!-- overflow-y: auto-->
                                        <li><input type="checkbox">2</li>
                                        <li><input type="checkbox">3</li>
                                        <li>4</li>
                                        <li>5</li>
                                        <li>6</li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td>총 xx명</td>
                            </tr>
                            <tr>
                                <th>첨부파일</th>
                                <td>
                                    <!-- <label id="label_file" for="input-file">첨부파일</label> -->
                                    <input type="file" name="upfile" id="input-file">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <textarea name="description" id="" rows="20" style="resize: none;"></textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
   
</body>
</html>