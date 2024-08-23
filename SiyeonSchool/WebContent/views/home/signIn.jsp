<%@page import="com.kh.user.model.vo.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/css/login.css">
    <!-- JavaScript 파일을 defer 속성으로 로드 -->
    <script defer src="resources/js/signIn.js"></script>
    <style>
        .canvas {
            top: 0;
            left: 0;
            pointer-events: none;
            width: 100%;
            height: 100%;
            position: absolute;
            z-index: 0;
        }

        #signIn {
            border: 1px solid #77ddff;
            background-color: rgb(223, 247, 255);
            border-radius: 20px;
            width: 1000px;
            height: 600px;
            z-index: 1;
            position: relative;
            margin: auto;
            top: 159.5px;
        }

        #back {
            width: 210px;
            height: 47px;
            border-radius: 10px;
            background-color: #46D3FF;
            color: white;
            font-size: 26px;
            font-weight: 700;
            border: none;
            position: absolute;
            margin-top: 520px;
            margin-left: 508px;
            letter-spacing: 3px;
        }

        #submit {
            width: 210px;
            height: 47px;
            border-radius: 10px;
            background-color: #46D3FF;
            color: white;
            font-size: 26px;
            font-weight: 700;
            border: none;
            position: absolute;
            margin-top: 520px;
            margin-left: 280px;
            letter-spacing: 3px;
        }

        #submit:hover, #back:hover {
            background-color: #00c3ff;
            cursor: pointer;
        }

        input, select {
            font-size: 16px;
            font-weight: 500;
            border: none;
            border-radius: 7px;
        }

        #checkId {
            width: 100px;
            height: 27px;
            border-radius: 7px;
            background-color: #46D3FF;
            font-size: 16px;
            font-weight: 700;
            border: none;
            position: absolute;
            letter-spacing: 3px;
        }

        #checkId:hover {
            background-color: #00c3ff;
            cursor: pointer;
        }

        tr {
            height: 45px;
        }

        tr th {
            text-align: center;
            padding-right: 40px;
            letter-spacing: 2px;
        }

        tr td {
            width: 691px;
            height: 35px;
            position: relative;
            letter-spacing: 1px;
        }

        #userId {
            width: 580px;
        }

        .s {
            width: 570px;
            height: 30px;
            margin-right: 10px;
        }

        .l {
            width: 685px;
            height: 30px;
        }

        select {
            width: 685px;
            height: 30px;
        }

        select:hover {
            cursor: pointer;
        }

        span {
            color: red;
        }

        form {
            position: relative;
        }

        table {
            position: absolute;
            margin-top: 30px;
            margin-left: 70px;
            font-weight: 600;
            font-size: 18px;
        }

        tr:last-child {
            height: 50px;
            vertical-align: bottom;
        }

        #end {
            text-align: center;
        }

        input::placeholder {
            font-size: 13px;
            color: #a98affe8;
        }

        select option[value=""][disabled] {
            display: none;
        }

        select:invalid {
            font-size: 13px;
            color: #a98affe8;
        }

    </style>
</head>
<body>
<canvas id="canvas" class="canvas"></canvas>
<div id="signIn">
    <form action="<%= contextPath %>/insert.user" method="post" id="signIn-form" onsubmit="return validateForm();">
        <table>
            <tr>
                <th><span>*</span> 아이디</th>
                <td>
                    <input type="text" class="s" name="userId" id="userId"
                           placeholder="영문, 숫자 조합으로 입력해주세요.(6~12자)(변경 불가)" required>
                    <button type="button" id="checkId" onclick="idCheck();">중복확인</button>
                </td>
            </tr>
            <tr>
                <th><span>*</span> 비밀번호</th>
                <td><input type="text" class="l" name="userPwd" placeholder="영문, 숫자,특수문자(!,@,#,$,%,^,&,* 만 사용) 조합으로 입력해주세요.(6~18자)" required></td>
            </tr>
            <tr>
                <th><span>*</span> 비밀번호 확인</th>
                <td><input type="text" class="l" name="userPwdCheck" placeholder="비밀번호를 다시 한번 입력해주세요." required></td>
            </tr>
            <tr>
                <th><span>*</span> 이름</th>
                <td><input type="text" class="l" name="userName" placeholder="이름을 입력해주세요." required></td>
            </tr>
            <tr>
                <th><span>*</span> 생년월일</th>
                <td><input type="text" class="l" name="birthday" placeholder="6자리 형식으로 입력해주세요. ex) 020202" required></td>
            </tr>
            <tr>
                <th>주소</th>
                <td><input type="text" class="l" name="address" placeholder="상세주소는 필수 입력 항목이 아닙니다. ex) 서울 강남구"></td>
            </tr>
            <tr>
                <th>이메일</th>
                <td><input type="text" class="l" name="email" placeholder="이메일 형식에 알맞게 입력해주세요."></td>
            </tr>
            <tr>
                <th><span>*</span> 전화번호</th>
                <td><input type="text" class="l" name="phone" placeholder="-를 포함해 입력해주세요" required></td>
            </tr>
            <tr>
                <th><span>*</span> 질문</th>
                <td>
                    <select name="questionNo" required>
						<option value="" disabled selected>아이디/비밀번호 찾기에 사용됩니다.</option>
						<% ArrayList<Question> questionList = (ArrayList<Question>)request.getAttribute("questionList");
					        if (questionList != null && !questionList.isEmpty()) {
					            for (Question question : questionList) {
					    %>
					        <option value="<%= question.getQuestionNo() %>"><%= question.getQuestionContent() %></option>
					    <%     }
					        } else {
					    %> <option value="">질문이 없습니다</option>
					    <% } %>
					</select>
                </td>
            </tr>
            <tr>
                <th><span>*</span> 답변</th>
                <td><input type="text" class="l" name="questionAnswer" placeholder="아이디/비밀번호 찾기에 사용됩니다."
                           required></td>
            </tr>
            <tr>
                <td colspan="2" id="end"><span>*</span> 필수 입력 항목입니다.</td>
            </tr>
        </table>
        <button type="submit" id="submit" disabled>회원가입 요청</button>
        <button id="back" onclick="history.back()">회원가입 취소</button>
    </form>
</div>
</body>
</html>
