<%@page import="com.kh.common.model.vo.Attachment"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.kh.mail.model.vo.MailReceiver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/common.jsp" %>

<%
	Mail m = (Mail)request.getAttribute("mail");
	// 메일: 메일번호, 별표, 메일제목, 작성자이름, 작성자아이디, 프로필사진경로, 보낸일시, 메일내용

	ArrayList<MailReceiver> mrList = (ArrayList<MailReceiver>)request.getAttribute("mailReceivers");
	// 메일수신인: 메일번호, 수신인번호, 수신인이름, 수신인아이디, 수신인구분(수신/참조/비밀), 읽은시간
	
	HashMap<String, Integer> mrTypeCountMap = (HashMap<String, Integer>)request.getAttribute("mrTypeCountMap");
	// 수신구분(수신:R/참조:C/비밀:S/총:TOTAL), 카운트숫자
	
	// 수신구분 String 조작
	StringBuilder sb = new StringBuilder();
	if(mrTypeCountMap.containsKey("R")){
		sb.append("수신: " + mrTypeCountMap.get("R") + ",  ");
	}
	if(mrTypeCountMap.containsKey("C")){
		sb.append("참조: " + mrTypeCountMap.get("C") + ",  ");
	}
	if(mrTypeCountMap.containsKey("S")){
		sb.append("비밀: " + mrTypeCountMap.get("S") + ",  ");
	}
	String mrTypeCount = sb.toString();
	if (mrTypeCount.endsWith(",  ")) {
            mrTypeCount = mrTypeCount.substring(0, mrTypeCount.length() - 3);
	}
	
	ArrayList<Attachment> attList = (ArrayList<Attachment>)request.getAttribute("attList");
	// 첨부파일 리스트: 첨부파일번호, 원본파일명, 변경파일명, 파일경로
	
	//"안읽음"버튼 관련
	String updateRead = "";
	if(request.getAttribute("ur") != null) {
		updateRead = (String)request.getAttribute("ur");
		// "n" : 메일상세조회에서 "안읽음" 처리를 한경우, n을 넘겨줌. -> "안읽음"버튼을 숨기기 위해 사용됨.
	}
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<%@ include file="mailSidebar.jsp" %>

	<script>
		// 뒤로가기 관련 기능 - 메일목록페이지의 url만 저장
		const previousURL = document.referrer;
		const splitURL = previousURL.split("/mail");
		if(splitURL[1].startsWith("?mb=")){
			sessionStorage.setItem("previousPage", previousURL);
		}
	</script>

	<!-- ======================================== 메인 ======================================== -->
	<main class="mail-detail">

		<div class="btnToGoBack">
			<span class="icon material-icons-outlined">navigate_before</span>
			<span class="text">목록으로</span>
		</div>

		<div class="email-btns">
			<button class="btn" onclick="location.href='<%= contextPath %>/mail.writeForm?mb=x&m=<%= m.getMailNo() %>'">답장</button>
			<button class="btn">전달</button>
			<button class="btn">이동</button>
			<button class="btn">삭제</button>
		</div>

		<section>
			<div class="mail-detail-header" id="mail-detail-header">

				<div class="title">
					<div class="starDiv" onclick="location.href='<%= contextPath %>/mail.update.star?mb=<%= currentMailbox %>&m=<%= m.getMailNo() %>&s=<%= m.getMailStar() %>'">
						<% if(m.getMailStar().equals("N")){ %>
							<span class="icon star material-symbols-rounded">star</span>
						<% }else { %>
							<span class="icon star fill material-icons-round">star</span>
						<% } %>
					</div>
					<div class="mailTitle">
						<%= m.getMailTitle() %>
					</div>
				</div>

				<table class="outer-table">
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
						<td class="td-left">받는사람</td>
						<td class="td-right">
							<ul class="list-header">
								<li>
									<div class="rCheckbox"><input type="checkbox"></div>
									<div class="rUserName">받는사람</div>
									<div class="rType">구분</div>
									<div class="rTime">읽은시간</div>
								</li>
							</ul>
							<ul class="list-contents">
								<% if (mrList.size() == 0) { %>
									<li class="noReceivers">(지정된 수신인이 없습니다.)</li>
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

							<% if (mrList.size() != 0) { %>
								<p class="listSummary">총 <%= mrTypeCountMap.get("TOTAL") %>명
									<span>( <%= mrTypeCount %> )</span>
								</p>
							<% } %>
							
							<% if(m.getUserId().equals(loginUser.getUserId()) && !currentMailbox.equals("t")) { %>
								<button class="btn">
									발신취소
								</button>
							<% }else if(!currentMailbox.equals("t") && !updateRead.equals("n")) { %>
								<button class="btn" onclick="location.href='<%= contextPath %>/mail.update.read?mb=<%= currentMailbox %>&m=<%= m.getMailNo() %>&r=<%= m.getIsRead() %>'">
									안읽음
								</button>
							<% } %>
						</td>
					</tr>

					<tr class="attachment">
						<td class="td-left">첨부파일</td>
						<% if(attList.size() == 0) { %>
							<td class="td-right">-</td>
						<% }else {%>
							<td class="td-right">
								<% for(Attachment at : attList) { %>
									<a class="file" download="<%= at.getOriginName() %>" href="<%= contextPath %>/<%= at.getFilePath() + at.getChangeName() %>">
										<span class="icon material-icons">file_download</span>
										<span class="fileName"><%= at.getOriginName() %></span>
									</a>
								<% } %>
							</td>
						<% } %>
					</tr>
					
				</table>

			</div>

			<div class="mail-detail-content"><%= m.getMailContent() %></div>

		</section>

		<div class="btn" id="btnToGoUp">
			<span class="icon material-icons-round">arrow_upward</span>
		</div>
	</main>
	
</body>
</html>