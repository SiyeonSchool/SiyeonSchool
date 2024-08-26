<%@page import="com.kh.mail.model.vo.Mail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
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
	
	// 이외
	String currentMailbox = (String)request.getAttribute("currentMailbox");
	//a(all: 전체메일함), i(inbox: 받은메일함), s(sent: 보낸메일함), t(temp: 임시보관함), m(myself: 내게쓴메일함), b(bin:휴지통)
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/mail.css">
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<!-- ======================================== 사이드바 ======================================== -->
	<aside>

		<!--  -------------------------- 사이드바 버튼그룹  -------------------------- -->
		<section class="aside-btn-group">

			<div class="write-btn-group">
				<div class="btn">메일 쓰기</div>
				<div class="btn">내게 쓰기</div>
			</div>
	
			<div class="remind-btn-group">
				<div class="remind-btn unread">
					<span class="icon material-icons-round">markunread</span>
					<p>안읽음<span>(5)</span></p>
				</div>
				<div class="remind-btn star">
					<span class="icon material-icons-round yellow-star">star</span>
					<p>중요<span>(20)</span></p>
				</div>
			</div>

		</section>

		<!--  -------------------------- 기본메일함  -------------------------- -->
		<section class="basic-mailBox">

			<!-- -------------- 기본메일함 타이틀 -------------- -->
			<div class="category-div">
				<h2 class="category-title">기본 메일함</h2>
			</div>
	
			<!-- -------------- 기본메일함 목록 -------------- -->
			<ul>
	
				<!-- 전체메일함 -->
				<li onclick="location.href='<%= contextPath2 %>/mail?mb=a&cpage=1'" <% if(currentMailbox.equals("a")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="0">
						<span class="icon mailboxNo-icon material-symbols-rounded">stacked_email</span>
						<span class="mailboxName">전체메일함</span>
					</div>
				</li>
		
				<!-- 받은메일함 -->
				<li onclick="location.href='<%= contextPath2 %>/mail?mb=i&cpage=1'" <% if(currentMailbox.equals("i")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-round">mail_outline</span>
						<span class="mailboxName">받은메일함</span>
						<span class="mailCount">(5)</span>
					</div>
				</li>
	
				<!-- 보낸메일함 -->
				<li onclick="location.href='<%= contextPath2 %>/mail?mb=s&cpage=1'" <% if(currentMailbox.equals("s")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">send</span>
						<span class="mailboxName">보낸메일함</span>
					</div>
				</li>
	
				<!-- 임시보관함 -->
				<li onclick="location.href='<%= contextPath2 %>/mail?mb=t&cpage=1'" <% if(currentMailbox.equals("t")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">note</span>
						<span class="mailboxName">임시보관함</span>
						<span class="mailCount">(0)</span>
					</div>
				</li>
	
				<!-- 내게쓴메일함 -->
				<li onclick="location.href='<%= contextPath2 %>/mail?mb=m&cpage=1'" <% if(currentMailbox.equals("m")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">article</span>
						<span class="mailboxName">내게쓴메일함</span>
					</div>
				</li>
	
				<!-- 휴지통 -->
				<li onclick="location.href='<%= contextPath2 %>/mail?mb=b&cpage=1'" <% if(currentMailbox.equals("b")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-symbols-outlined">delete</span>
						<span class="mailboxName">휴지통</span>
						<div class="emptyBtn">비우기</div>
					</div>
				</li>
	
			</ul>
		</section>


		<!--  -------------------------- 내메일함  -------------------------- -->
		<section class="private-mailBox">
			
			<!-- -------------- 내메일함 타이틀 -------------- -->
			<div class="category-div">
				<h2 class="category-title">내 메일함</h2>
				<div class="icon add-icon material-symbols-rounded">add</div>
			</div>
	
			<!-- -------------- 내메일함 목록 -------------- -->
			<ul>
	
				<!-- 내메일함 -->
				<li class>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-symbols-outlined">folder</span>
						<span class="mailboxName">내 메일함</span>
						<span class="mailCount">(5)</span>
						<span class="icon edit-icon material-symbols-rounded icon edit">edit</span>
					</div>
				</li>
	
			</ul>
		</section>


	</aside>

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
				
				<% if(currentMailbox.equals("i")) { %>
					<li class="mail-column read">읽음</li>
				<% } %>
				
				<li class="mail-column sender">보낸사람</li>
				<li class="mail-column mailTitle">메일 제목</li>
				
				<% if(currentMailbox.equals("i")) { %>
					<li class="mail-column type">수신구분</li>
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
							<li class="mail unreadMail">
						<% }else { %>
							<li class="mail">
						<% } %>
						
							<div class="mail-column checkbox jc-center">
								<input type="checkbox">
							</div>
							
							<div class="mail-column star jc-center">
								<% if(m.getMailStar().equals("N")){ %>
									<span class="icon star material-symbols-rounded">star</span>
								<% }else { %>
									<span class="icon star fill material-icons-round">star</span>
								<% } %>
							</div>
		
							<% if(currentMailbox.equals("i")) { %>
								<div class="mail-column read jc-center">
									<% if(m.getIsRead().equals("N")){ %>
										<span class="icon mail-icon material-icons-round">markunread</span>
									<% }else { %>
										<span class="icon mail-icon material-icons-outlined">drafts</span>
									<% } %>
								</div>
							<% } %>
		
							<div class="mail-column sender">
								<% if(m.getProfilePath() != null){ %>
									<img src="<%= contextPath %>/<%= m.getProfilePath() %>" class="profile-img">
								<% }else { %>
									<span class="material-symbols-rounded icon profile-icon">account_circle</span>
								<% } %>
								<span class="userNameText"><%= m.getUserName() %></span>
								<span class="userId">(<%= m.getUserId() %>)</span>
							</div>
							
							<div class="mail-column mailTitle text-left">
								<span><%= m.getMailTitle() %></span>
							</div>
		
							<% if(currentMailbox.equals("i")) { %>
								<div class="mail-column type jc-center">
									<span><%= m.getReceiverType() %></span>
								</div>
							<% } %>
		
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