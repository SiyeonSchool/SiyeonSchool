<%@page import="com.kh.contacts.model.vo.ContactsCategory"%>
<%@page import="com.kh.contacts.model.vo.Contacts"%>
<%@page import="com.kh.user.model.vo.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<%
	ArrayList<ContactsCategory> categoryList = (ArrayList<ContactsCategory>)request.getAttribute("categoryList");
	// 카테고리번호, 카테고리명 

	ArrayList<Contacts> contactsList = (ArrayList<Contacts>)request.getAttribute("contactsList");
	// 주소록번호, 주소록명, 주소록타입, 카테고리번호
	
	ArrayList<User> userList = (ArrayList<User>)request.getAttribute("userList");
	// 유저번호, 유저ID, 유저이름, 연락처, 연락처공개여부, 생일, 프로필사진파일번호, 사용자권한
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/contacts.css">
	<script defer src="resources/js/contacts.js"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<!-- ==================== 사이드바 ==================== -->
	<aside>

		<!-- ------- 대분류: 공유주소록 ------- -->
		<div class="big-cate public-contacts">

			<h2 class="big-cate__title">
				공유 주소록
				<a href="">
					<span class="material-symbols-rounded icon">add</span>
				</a>
			</h2>

			<ul class="big-cate__contents">

				<!-- ------- 중분류: 모든 사용자 ------- -->
				<li class="mid-cate">
					<div class="mid-cate__title active">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span>모든 사용자</span>
							<span class="userCount">(<%= userList.size() %>)</span>
						</div>
					</div>
				</li>

				<!-- ------- 중분류: ex) 세미 프로젝트 ------- -->
				<% for(ContactsCategory ca : categoryList) { %>
					<li class="mid-cate">
	
						<div class="mid-cate__title">
							<input type="hidden" value="<%= ca.getCategoryNo() %>">
							<div>
								<span class="material-icons-round icon people">people</span>
								<span class="title"><%= ca.getCategoryName() %></span>
								<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
							</div>
							<div>
								<span class="material-symbols-rounded icon edit">edit</span>
							</div>
						</div>
	
						<ul class="mid-cate__contents hidden">
							<!-- ------- 소분류: ex) 세미 2조 ------- -->
							<% for(Contacts c : contactsList) { %>
								<% if(c.getCategoryNo() == ca.getCategoryNo()) { // 주소록이 해당 카테고리에 속할경우 %>
									<li class="sm-cate">
										<div>
											<span class="material-icons icon">subdirectory_arrow_right</span>
											<span><%= c.getContactsName() %></span>
											<span class="userCount">(6)</span>
										</div>
									</li>
								<% } %>
							<% } %>
						</ul>
						
					</li>
				<% } %>

			</ul> <!-- .big-cate__contents -->
		</div> <!-- .big-cate ------- 대분류: ex) 공유주소록, 개인주소록 ------- -->

		<!-- ------- 대분류: 개인주소록 ------- -->
		<div class="big-cate private-contacts">

			<h2 class="big-cate__title">
				개인 주소록
				<a href="">
					<span class="material-symbols-rounded icon">add</span>
				</a>
			</h2>

			<ul class="big-cate__contents">
			
				<!-- ------- 중분류: ex) 개인주소록1 ------- -->
				<% for(Contacts c : contactsList) { %>
					<% if(c.getContactsType().equals("P")) { // 주소록타입이 P(개인)인 경우 %>
						<li class="mid-cate">
							<div class="mid-cate__title">
								<div>
									<span class="material-icons-round icon people">people</span>
									<span><%= c.getContactsName() %></span>
									<span class="userCount">(3)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</div>
						</li>
					<% } %>
				<% } %>
				

			</ul> <!-- .big-cate__contents -->
		</div> <!-- .big-cate ------- 대분류: ex) 공유주소록, 개인주소록 ------- -->

	</aside>

	<!-- ==================== 메인 컨텐츠 ==================== -->
	<main>
		<!-- ------- 검색창 섹션 (section__serach-bar) ------- -->
		<section class="section__serach-bar">

			<div class="btn-group">
				<button>공유 주소록에 추가</button>
				<button>내 주소록에 추가</button>
				<button>
					<span class="material-symbols-outlined icon">mail</span>
					<span>메일</span>
				</button>
			</div>

			<div class="search-bar">
				<input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
				<span class="material-symbols-outlined icon">search</span>
			</div>

			<div class="search-options">
				<input type="checkbox" id="search-by-userName" name="search-option">
				<label for="search-by-userName">이름</label>

				<input type="checkbox" id="search-by-userId" name="search-option">
				<label for="search-by-userId">아이디</label>

				<input type="checkbox" id="search-all-students" name="search-option">
				<label for="search-all-students">학생</label>
			</div>
		</section>

		<!-- 목록 헤더 섹션 (section__list-header) -->
		<section class="section__list-header">
			<ul>
				<li>
					<div class="checkbox">
						<input type="checkbox" name="" id="">
					</div>
		
					<div class="star">
						<span class="material-icons-round icon star fill">star</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
		
					<div class="userName">
						<span class="text">이름</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
					<div class="userId">
						<span class="text">아이디</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
					<div class="role">
						<span class="text">역할</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
					<div class="birthday">
						<span class="text">생일</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
					<div class="phone">
						<span class="text">연락처</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
				</li>
			</ul>
		</section>

		<!-- 목록 내용 섹션 (section__list-content) -->
		<section class="section__list-content">
			<ul>

				<% for(User u : userList) {%>
					<!-- 한 줄의 사용자 데이터 -->
					<li>
						<div class="checkbox">
							<input type="checkbox" name="" id="">
						</div>
						
						<div class="star">
							<span class="material-symbols-rounded icon star">star</span>
						</div>
						
						<div class="userName">
							<span class="material-symbols-rounded icon profile-pic">account_circle</span>
							<%= u.getUserName() %>
						</div>
						
						<div class="userId"><%= u.getUserId() %></div>
						
						<div class="role">
							<% if(u.getUserAuth().equals("A")) { %>
								선생님
							<% }else { %>
								학생
							<% } %>
						</div>
						
						<div class="birthday"><%= u.getBirthday() %></div>
						
						<div class="phone">
							<% if(u.getPhonePublic().equals("N")) { %>
								비공개
							<% }else { %>
								<%= u.getPhone() %>
							<% } %>
						</div>
					</li>
				<% } %>
				
			</ul>
		</section>
		
	</main>
	
</body>
</html>