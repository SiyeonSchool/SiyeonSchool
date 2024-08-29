<%@page import="java.util.Set"%>
<%@page import="com.kh.mail.model.vo.Mailbox"%>
<%@page import="com.kh.mail.model.vo.Mail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 현재 메일함
	String currentMailbox = (String)request.getAttribute("currentMailbox");
	// a(all: 전체메일함), i(inbox: 받은메일함), s(sent: 보낸메일함), t(temp: 임시보관함), m(myself: 내게쓴메일함), b(bin:휴지통)
	// u(unread: 안읽은메일), im(important:중요메일)
	
	// 메일 개수
	ArrayList<Mailbox> mailboxCountList = (ArrayList<Mailbox>)request.getAttribute("mailboxCountList"); // 메일함별 메일개수 리스트 
	int allMailCount = (int)request.getAttribute("allMailCount");  			  // 전체메일개수
	int unreadMailCount = (int)request.getAttribute("unreadMailCount"); 	  // 안읽은메일 갯수
	int importantMailCount = (int)request.getAttribute("importantMailCount"); // 중요메일개수
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/mail.css">
	<script defer src="resources/js/mail.js"></script>
</head>
<body>

	<!-- ======================================== 사이드바 ======================================== -->
	<aside>

		<!--  -------------------------- 사이드바 버튼그룹  -------------------------- -->
		<section class="aside-btn-group">

			<div class="write-btn-group">
				<div class="btn">메일 쓰기</div>
				<div class="btn">내게 쓰기</div>
			</div>
	
			<div class="remind-btn-group">
				<!-- 안읽은메일 -->
				<% if(currentMailbox.equals("u")){ %> 
					<div class="remind-btn unread active" onclick="location.href='<%= contextPath %>/mail?mb=u&cpage=1'">
				<% }else { %>
					<div class="remind-btn unread" onclick="location.href='<%= contextPath %>/mail?mb=u&cpage=1'">
				<% } %>
						<span class="icon material-icons-round">markunread</span>
						<p>안읽음<span class="count">(<%= unreadMailCount %>)</span></p>
					</div>
				
				<!-- 중요메일 -->
				<% if(currentMailbox.equals("im")){ %> 
					<div class="remind-btn star active" onclick="location.href='<%= contextPath %>/mail?mb=im&cpage=1'">
				<% }else { %>
					<div class="remind-btn star" onclick="location.href='<%= contextPath %>/mail?mb=im&cpage=1'">
				<% } %>
						<span class="icon material-icons-round yellow-star">star</span>
						<p>중요<span class="count">(<%= importantMailCount %>)</span></p>
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
				<li onclick="location.href='<%= contextPath %>/mail?mb=a&cpage=1'" <% if(currentMailbox.equals("a")){ %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="0">
						<span class="icon mailboxNo-icon material-symbols-rounded">stacked_email</span>
						<span class="mailboxName">전체메일함</span>
						<span class="mailCount">(<%= allMailCount %>)</span>
					</div>
				</li>
		
				<!-- 받은메일함 -->
				<li onclick="location.href='<%= contextPath %>/mail?mb=i&cpage=1'" <% if(currentMailbox.equals("i")){ %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-round">mail_outline</span>
						<span class="mailboxName">받은메일함</span>
						<span class="mailCount">(<%= mailboxCountList.get(0).getMailCount() %>)</span>
					</div>
				</li>
	
				<!-- 보낸메일함 -->
				<li onclick="location.href='<%= contextPath %>/mail?mb=s&cpage=1'" <% if(currentMailbox.equals("s")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">send</span>
						<span class="mailboxName">보낸메일함</span>
						<span class="mailCount">(<%= mailboxCountList.get(1).getMailCount() %>)</span>
					</div>
				</li>
	
				<!-- 임시보관함 -->
				<li onclick="location.href='<%= contextPath %>/mail?mb=t&cpage=1'" <% if(currentMailbox.equals("t")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">note</span>
						<span class="mailboxName">임시보관함</span>
						<span class="mailCount">(<%= mailboxCountList.get(2).getMailCount() %>)</span>
					</div>
				</li>
	
				<!-- 내게쓴메일함 -->
				<li onclick="location.href='<%= contextPath %>/mail?mb=m&cpage=1'" <% if(currentMailbox.equals("m")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">article</span>
						<span class="mailboxName">내게쓴메일함</span>
						<span class="mailCount">(<%= mailboxCountList.get(3).getMailCount() %>)</span>
					</div>
				</li>
	
				<!-- 휴지통 -->
				<li onclick="location.href='<%= contextPath %>/mail?mb=b&cpage=1'" <% if(currentMailbox.equals("b")) { %> class="active" <% } %>>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-symbols-outlined">delete</span>
						<span class="mailboxName">휴지통</span>
						<span class="mailCount">(<%= mailboxCountList.get(4).getMailCount() %>)</span>
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
						<span class="mailCount">(<%= mailboxCountList.get(5).getMailCount() %>)</span>
						<span class="icon edit-icon material-symbols-rounded icon edit">edit</span>
					</div>
				</li>
	
			</ul>
		</section>


	</aside>
</body>
</html>