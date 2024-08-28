<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/myPageATD.css">
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
        %>
    <div class="wrapper">
        <section class="body__wrapper">
            <%@ include file="../common/myPageSide.jsp" %>
            <div id="myATD-body">
                <div class="myATD-title">출결 / 휴가</div>
                <div class="myATD-content">
                    <div class="month-navigation">
                        <span class="material-icons navicon" id="prevMonth">navigate_before</span>
                        <span id="currentMonth"></span>
                        <span class="material-icons navicon" id="nextMonth">navigate_next</span>
                    </div>
                    <div class="attendance-container">
                    
                        <table>
                            <thead>
                                <tr id="dateRow">
                                    <!-- 날짜가 여기에 동적으로 추가됩니다 -->
                                </tr>
                                <tr id="dayRow">
                                    <!-- 요일이 여기에 동적으로 추가됩니다 -->
                                </tr>
                                <tr id="attendanceData">
                                    <!-- 출석 데이터가 여기에 표시됩니다 -->
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <script src="resources/js/myPageATD.js"></script>

</body>
</html>