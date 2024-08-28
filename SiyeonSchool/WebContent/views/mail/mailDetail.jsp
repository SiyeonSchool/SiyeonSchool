<%@page import="com.kh.mail.model.vo.MailReceiver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/common.jsp" %>

<%
	Mail m = (Mail)request.getAttribute("mail");
	// 메일: 메일번호, 별표, 메일제목, 작성자이름, 작성자아이디, 프로필사진경로, 보낸일시, 메일내용

	ArrayList<MailReceiver> mrList = (ArrayList<MailReceiver>)request.getAttribute("mailReceivers");
	// 메일수신인: 메일번호, 수신인번호, 수신인이름, 수신인아이디, 수신인구분(수신/참조/비밀), 읽은시간
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<%@ include file="mailSidebar.jsp" %>

	<!-- ======================================== 메인 ======================================== -->
	<main class="mail-detail">

		<div class="btnToGoBack">
			<span class="icon material-icons-outlined">navigate_before</span>
			<span class="text">목록으로</span>
		</div>

		<section>
			<div class="email-btns">
				<button class="btn">답장</button>
				<button class="btn">전달</button>
				<button class="btn">이동</button>
				<button class="btn">삭제</button>
			</div>

			<div class="mail-detail-header">
				<table class="outer-table">
					
					<tr class="title">
						<td class="td-left">
							<% if(m.getMailStar().equals("N")){ %>
								<span class="icon star material-symbols-rounded">star</span>
							<% }else { %>
								<span class="icon star fill material-icons-round">star</span>
							<% } %>
						</td>
						<td class="td-right">
							<span class="mailTitle"><%= m.getMailTitle() %></span>
						</td>
					</tr>

					<tr class="sender">
						<td class="td-left">보낸사람</td>
						<td class="td-right">
							<img src="<%= contextPath %>/<%= m.getProfilePath() %>" class="profile-img">
							<div class="sender-info">
								<span class="userName"><%= m.getUserName() %></span>
								<span class="userId">(<%= m.getUserId() %>)</span>
							</div>
							<div class="sentTime-info">
								<span>보낸시간:</span>
								<span class="sentDate"><%= m.getSendDate() %></span>
							</div>
						</td>
					</tr>

					<tr class="receiver">
						<td class="td-left">받은사람</td>
						<td class="td-right">
							<ul class="list-header">
								<li>
									<div class="rCheckbox"><input type="checkbox"></div>
									<div class="rUserName">수신인</div>
									<div class="rType">수신구분</div>
									<div class="rTime">읽은시간</div>
								</li>
							</ul>
							<ul class="list-contents">
							
								<% if (mrList.size() == 0) { %>
									<li>수신인이 없습니다.</li>
								<% }else { %>
									<% for(MailReceiver mr : mrList) { %>
										<li>
											<div class="rCheckbox"><input type="checkbox"></div>
											<div class="rUserName"><span class="userName"><%= mr.getReceiverName() %></span><span class="userId">(<%= mr.getReceiverId() %>)</span></div>
											<div class="rType"><%= mr.getReceiverType() %></div>
											<div class="rTime"><%= mr.getReadTime() %></div>
										</li>
									<% } %>
								<% } %>
								
							</ul>

							<p class="listSummary">총 10명 <span>(수신 8, 참조 1, 비밀 1)</span></p>
						</td>
					</tr>

					<tr class="attachment">
						<td class="td-left">첨부파일</td>
						<td class="td-right">첨부파일명.pdf</td>
					</tr>
				</table>

				<div class="cancel-sending">
					<button class="btn">발신취소</button>
				</div>
			</div>

			<div class="mail-detail-content">
				<%= m.getMailContent() %>
			</div>

		</section>
	</main>
	
</body>
</html>