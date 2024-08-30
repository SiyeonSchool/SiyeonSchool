<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<%@ include file="../common/menubar.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="resources/css/homework_detailView.css">
    </head>
    <body>
        <div id="container_detailView">
            <%@ include file="sidebar.jsp" %>
            <div class="toList" onclick="history.back()">
                <span class="material-icons">arrow_back_ios</span>
                <span>목록으로</span>
            </div>
            <div id="homework_detailView_content">
                <div id="detailView_content">
                    <table>
                        <tr>
                            <td colspan="4">[subject]title</td>
                        </tr>
                        <tr>
                            <td>작성자</td>
                            <td colspan="3">-------------------- <span>작성일</span></td>
                        </tr>
                        <tr>
                            <td>첨부파일</td>
                            <td colspan="3">--------------------</td>
                        </tr>
                        <tr>
                            <td>제출기한</td>
                            <td colspan=3>--------------------</td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <div>
                                    내용
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <span class="material-icons">comment</span>
                                <span>댓글</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="2">
                                <img src="<%= loginUser.getProfilePath() %>" alt="">
                            </td>
                            <td colspan="3"><%= loginUser.getUserName() %></td>
                            
                        </tr>
                        <form action="">
                            <tr>
                                <td>
                                    <input type="text" placeholder="댓글 입력">
                                </td>
                                <td>
                                    <button type="submit">등록</button>
                                </td>
                                <td>
                                    <button type="reset">취소</button>
                                </td>
                            </tr>
                        </form>
                    </table>
                </div>

            </div>
            <div style="display: none;" id="detailView_submit_status">
                <form action="">
                    <div id="submit_before">
                        <table>
                            <tr>
                                <td>내 과제</td>
                                <td>
                                    <span class="material-icons">highlight_off</span>
                                    <span>제출 안함</span>
                                </td>
                            </tr>
                            <tr>
                                <td>첨부파일</td>
                                <td>
                                    <input type="file">
                                </td>
                            </tr>
                            <tr>
                                <td>제출일시</td>
                                <td>
                                    <span>제출 전</span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <button type="submit">제출하기</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
                <div id="submit_after">
                    <table>
                        <tr>
                            <td>내 과제</td>
                            <td>
                                <span class="material-icons">check_circle_outline</span>
                                <span>제출 완료</span>
                            </td>
                        </tr>
                        <tr>
                            <td>첨부파일</td>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>제출일시</td>
                            <td>
                                <span> </span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit">제출취소</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="detailView_submit_situation">
                <ul>
                    <li>
                        <span>제출현황</span>
                        <div id="sort">
                            <span class="material-icons">sort</span>
                            <span>정렬기준</span>
                        </div>
                    </li>
                    <li>
                        <table>

                        </table>
                    </li>
                </ul>
            </div>
        </div>
   
</body>
</html>