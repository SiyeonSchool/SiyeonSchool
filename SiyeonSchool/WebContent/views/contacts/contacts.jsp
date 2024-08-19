<%@page import="com.kh.contacts.model.vo.ContactsCategory"%>
<%@page import="com.kh.contacts.model.vo.Contacts"%>
<%@page import="com.kh.user.model.vo.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<%
	ArrayList<ContactsCategory> categoryList = (ArrayList<ContactsCategory>)request.getAttribute("categoryList");
	// 사이드바에 사용될 정보 (공유주소록) - 카테고리번호, 카테고리명 

	ArrayList<Contacts> pivateContactsList = (ArrayList<Contacts>)request.getAttribute("pivateContactsList");
	// 사이드바에 사용될 정보 (개인주소록) - 주소록번호, 주소록명, 인원수
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

	<script>
		// 별도 js 파일로 loginUser을 가져가기 위함. (json 형태로)
		const loginUserJson = `<%= loginUserJson %>`;
	</script>
	

	<!-- ==================== 사이드바 ==================== -->
	<aside>

		<!-- ------- 대분류: 공유주소록 ------- -->
		<div class="big-cate public-contacts">

			<h2 class="big-cate__title">
				공유 주소록
				<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
					<div class="addContactsBtn">
						<span class="material-symbols-rounded icon">add</span>
					</div>
				<% } %>
			</h2>

			<ul class="big-cate__contents">

				<!-- ------- 중분류: 모든 사용자 ------- -->
				<li class="mid-cate allUsers">
					<div class="mid-cate__title active">
						<input type="hidden" name="categoryNo" value="0">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span>모든 사용자</span>
							<span class="userCount"></span>
						</div>
					</div>
				</li>

				<!-- ------- 중분류: ex) 세미 프로젝트 ------- -->
				<% for(ContactsCategory cc : categoryList) { %>
					<li class="mid-cate">
	
						<div class="mid-cate__title dynamic">
							<input type="hidden" name="categoryNo" value="<%= cc.getCategoryNo() %>">
							<div>
								<span class="material-icons-round icon people">people</span>
								<span class="title"><%= cc.getCategoryName() %></span>
								<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
							</div>
							<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							<% } %>
						</div>
	
						<ul class="mid-cate__contents hidden">
							<!-- ajax로 데이터가 동적으로 들어갈 공간 - 소분류: ex) 세미 2조 -->
						</ul>
						
					</li>
				<% } %>

			</ul> <!-- .big-cate__contents -->
		</div> <!-- .big-cate ------- 대분류: 공유주소록 ------- -->

		<!-- ------- 대분류: 개인주소록 ------- -->
		<div class="big-cate private-contacts">

			<h2 class="big-cate__title">
				개인 주소록
				<div class="addContactsBtn" onclick="showModal_AddPrivateContacts();">
					<span class="material-symbols-rounded icon">add</span>
				</div>
			</h2>

			<ul class="big-cate__contents">
			
				<!-- ------- 중분류: ex) 개인주소록1 ------- -->
				<% for(Contacts c : pivateContactsList) { %>
					<li class="mid-cate">
						<div class="mid-cate__title">
							<input type="hidden" name="contactsNo" value="<%= c.getContactsNo() %>">
							<div>
								<span class="material-icons-round icon people">people</span>
								<span><%= c.getContactsName() %></span>
								<span class="userCount">(<%= c.getUserCount() %>)</span>
							</div>
							<div>
								<span class="material-symbols-rounded icon edit">edit</span>
							</div>
						</div>
					</li>
				<% } %>
				
			</ul> <!-- .big-cate__contents -->
		</div> <!-- .big-cate ------- 대분류: 개인주소록 ------- -->

	</aside>

	<!-- ==================== 메인 컨텐츠 ==================== -->
	<main>
		<!-- ------- 검색창 섹션 (section__serach-bar) ------- -->
		<section class="section__serach-bar">

			<div class="btn-group">
				<button class="email">
					<span class="material-symbols-outlined icon">mail</span>
					<span>메일</span>
				</button>
				<button class="addBtn">주소록에 추가</button>
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
						<input type="checkbox">
					</div>
		
					<div class="star">
						<span class="material-icons-round icon star fill">star</span>
						<span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
					</div>
					
					<!-- "주소록"칸 들어갈자리 (카테고리전체유저리스트 조회시에만 동적으로 추가됨.) -->
		
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
				<!-- ajax로 데이터가 동적으로 들어갈 공간 - 유저 정보 한줄씩 -->
			</ul>
		</section>

		<!-- modal창 - "주소록에추가"  -->
		<div class="modal-bg modal-addMember-bg">
			<div class="modal modal-addMember">
				<span class="material-symbols-rounded icon closeBtn">close</span>

				<h3>주소록에 추가</h3>
				<p>주소록을 선택해주세요. (선택한 사용자를 해당 주소록에 추가합니다.)</p>
				<hr>

				<div class="modal-addMember__contactsList">
					<!-- ajax로 데이터가 동적으로 들어갈 공간 - 현재 유저가 소유한 주소록명 한줄씩 -->
				</div>
			</div>
		</div>

		<!-- modal창 - 개인주소록 추가 -->
		<div class="modal-bg modal-addPrivateContact-bg">
			<div class="modal modal-addPrivateContact">
				<span class="material-symbols-rounded icon closeBtn" onclick="closeModal(this);">close</span>

				<h3>개인주소록 추가</h3>
				<p>개인주소록을 추가합니다. 새로운 주소록의 이름을 입력해주세요.</p>
				<hr>

				<label for="newPrivateContactsName">새 주소록 이름 :</label>
				<input type="text" name="newPrivateContactsName" id="newPrivateContactsName">

				<hr>
				<input type="submit" value="추가" onclick="insertPrivateContacts();">
			</div>
		</div>
		
	</main>
	
</body>
</html>