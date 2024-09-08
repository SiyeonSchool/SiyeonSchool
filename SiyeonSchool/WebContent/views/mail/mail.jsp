<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/common.jsp" %>

<%
	// 페이징 관련
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Mail> list = (ArrayList<Mail>)request.getAttribute("list");
	
	int cPage = pi.getcPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
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
	<main>

		<!-- --------------------- 첫번째 헤더 --------------------- -->
		<section class="first-header">
			<div class="first-header-btns">
				<button class="btn moveBtn">이동</button>
				<button class="btn deleteBtn">삭제</button>
			</div>

			<div class="search-bar-div">
				<select class="search-option" name="search-option">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="titleAndContent">제목+내용</option>
				</select>
				<input type="text" class="search-bar" name="keyword" placeholder="검색어를 입력해주세요." maxlength="20">
				<span class="icon search-icon material-symbols-outlined">search</span>
			</div>
		</section>

		<!-- --------------------- 두번째 헤더 --------------------- -->
		<section class="second-header">
			<ul>
				<li class="mail-column checkbox"><input type="checkbox"></li>
				<li class="mail-column star">중요</li>
				
				<% if(currentMailbox.equals("i") || currentMailbox.equals("u") || currentMailbox.equals("m")) { // 받은메일함, 안읽은메일, 내게쓴메일함 %>
					<li class="mail-column read">읽음</li>
				<% } %>

				<li class="mail-column attachment">첨부</li>
				
				<% if(currentMailbox.equals("a") || currentMailbox.equals("im") || currentMailbox.equals("b")) { // 전체메일함, 중요메일, 휴지통 %>
					<li class="mail-column mailbox">메일함</li>
				<% } %>
				
				<li class="mail-column sender">보낸사람</li>
				<li class="mail-column mailTitle">메일 제목</li>
				
				<% if(currentMailbox.equals("i") || currentMailbox.equals("u")) { // 받은메일함, 안읽은메일 %>
					<li class="mail-column type">수신구분</li>
				<% } %>
				
				<% Set<String> excludedMailboxes = Set.of("a", "i", "u", "im", "b"); // 전체메일함, 받은메일함, 임시보관함, 안읽은메일, 중요메일, 휴지통
				   if(!excludedMailboxes.contains(currentMailbox)) { %>
					<div class="mail-column empty-space"></div>
				<% } %>
				
				<li class="mail-column sentDate">보낸시간</li>
			</ul>
		</section>
		
		<!-- --------------------- 메일 목록 --------------------- -->
		<section class="mail-list">
			<ul>
				<% if(list.isEmpty()) { %>
					<li class="mail noMail">조회된 메일이 없습니다.</li>
				<% }else { %>
					<% for(Mail m : list) { %>

						<!-- 하나의 메일 -->
						<% if(m.getIsRead() != null && m.getIsRead().equals("N")){ %>
							<li id="<%= m.getUserId() %>" class="mail unreadMail">
						<% }else { %>
							<li id="<%= m.getMailNo() %>" class="mail">
						<% } %>
						
								<!-- 체크박스 -->
								<div class="mail-column checkbox jc-center">
									<input type="checkbox">
								</div>
								
								<!-- 중요(별) -->
								<div class="mail-column star jc-center" onclick="location.href='<%= contextPath %>/mail.update.star?mb=<%= currentMailbox %>&cpage=<%= cPage %>&m=<%= m.getMailNo() %>&s=<%= m.getMailStar() %>'">
									<% if(m.getMailStar().equals("N")){ %>
										<span class="icon star material-symbols-rounded">star</span>
									<% }else { %>
										<span class="icon star fill material-icons-round">star</span>
									<% } %>
								</div>
			
								<!-- 읽음 -->
								<% if(currentMailbox.equals("i") || currentMailbox.equals("u") || currentMailbox.equals("m")) { %>
									<div class="mail-column read jc-center" onclick="location.href='<%= contextPath %>/mail.update.read?mb=<%= currentMailbox %>&cpage=<%= cPage %>&m=<%= m.getMailNo() %>&r=<%= m.getIsRead() %>'">
										<% if(m.getIsRead().equals("N")){ %>
											<span class="icon mail-icon material-icons-round">markunread</span>
										<% }else { %>
											<span class="icon mail-icon material-icons-outlined">drafts</span>
										<% } %>
									</div>
								<% } %>

								<!-- 첨부파일 -->
								<div class="mail-column attachment jc-center">
									<% if(m.getAttachmentCount() > 0) { %>
										<span class="icon material-symbols-outlined">attach_file</span>
									<% } else { %>
										<span>-</span>
									<% } %>
								</div>
			
								<!-- 메일함 -->
								<% if(currentMailbox.equals("a") || currentMailbox.equals("im") || currentMailbox.equals("b")) { %>
									<div class="mail-column mailbox jc-center">
										<span class="mailboxName"><%= m.getMailboxName() %></span>
									</div>
								<% } %>
			
								<!-- 보낸사람 -->
								<div class="mail-column sender">
									<% if(m.getProfilePath() != null){ %>
										<img src="<%= contextPath %>/<%= m.getProfilePath() %>" class="profile-img">
									<% }else { %>
										<span class="material-symbols-rounded icon profile-icon">account_circle</span>
									<% } %>
									<span class="userNameText"><%= m.getUserName() %></span>
									<span class="userId">(<%= m.getUserId() %>)</span>
								</div>
								
								<!--  메일 제목 -->
								<div class="mail-column mailTitle" onclick="location.href='<%= contextPath %>/mail.detail?mb=<%= currentMailbox %>&m=<%= m.getMailNo() %>'">
									<span><%= m.getMailTitle() %></span>
								</div>
			
								<!-- 수신구분 -->
								<% if(currentMailbox.equals("i") || currentMailbox.equals("u")) { %>
									<div class="mail-column type jc-center">
										<span><%= m.getReceiverType() %></span>
									</div>
								<% } %>
			
								<!-- 여백 -->
								<% if(!excludedMailboxes.contains(currentMailbox)) { %>
									<div class="mail-column empty-space"></div>
								<% } %>
			
								<!-- 보낸시간 -->
								<div class="mail-column sentDate jc-center">
									<span><%= m.getSendDate() %></span>
								</div>
							</li>

					<% } %>
				<% } %>

			</ul>
		</section>

		<!-- --------------------- 페이징 --------------------- -->
		<% if(!list.isEmpty()) { %>
			<section class="paging-area">

				<!-- ------- 이전 페이지로 ------- -->
				<% if(cPage != 1) { %>
					<!-- 왼쪽 끝 페이지로 -->
					<% if(cPage == startPage) { %>
						<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= startPage - 10 %>'">first_page</span>
					<% } else { %>
						<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= startPage %>'">first_page</span>
					<% } %>

					<!-- 왼쪽 바로 이전 페이지로 -->
					<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= cPage -1 %>'">navigate_before</span>
				
				<% } else { %> <!-- 첫페이지면 버튼 숨기기-->
					<!-- 왼쪽 끝 페이지로 -->
					<% if(cPage == startPage) { %>
						<span class="icon material-icons-outlined hidden">first_page</span>
					<% } else { %>
						<span class="icon material-icons-outlined hidden" >first_page</span>
					<% } %>

					<!-- 왼쪽 바로 이전 페이지로 -->
					<span class="icon material-icons-outlined hidden">navigate_before</span>
				<% } %>
				

				<!-- ------- 페이지 번호 처리 ------- -->
				<% for(int p=startPage; p<=endPage; p++) { %>
					<% if(p == cPage) { %>
						<span class="page currentPage"><%= p %></span>
					<% } else { %>
						<span class="page" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= p %>'"><%= p %></span>
					<% } %>
				<% } %>
				

				<!-- ------- 다음 페이지로 ------- -->
				<% if(cPage != maxPage) { %>
					<!-- 오른쪽 바로 다음 페이지로 -->
					<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= cPage + 1 %>'">navigate_next</span>
					
					<!-- 오른쪽 끝 페이지로 -->
					<% if(cPage == endPage) { %>
						<% if(endPage + 10 < maxPage) { %>
							<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= endPage + 10 %>'">last_page</span>
						<% } else { %>
							<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= maxPage %>'">last_page</span>
						<% } %>
					<% } else { %>
						<span class="icon material-icons-outlined" onclick="location.href='<%= contextPath %>/mail?mb=<%= currentMailbox %>&cpage=<%= endPage %>'">last_page</span>
					<% } %>

				<% } else { %>  <!-- 마지막 페이지면 버튼 숨기기-->
					<!-- 오른쪽 바로 다음 페이지로 -->
					<span class="icon material-icons-outlined hidden">navigate_next</span>
					
					<!-- 오른쪽 끝 페이지로 -->
					<% if(cPage == endPage) { %>
						<span class="icon material-icons-outlined hidden">last_page</span>
					<% } else { %>
						<span class="icon material-icons-outlined hidden">last_page</span>
					<% } %>
				<% } %>

			</section>
		<% } %>

	</main>
</body>
</html>