<%@page import="com.kh.contacts.model.vo.ContactsCategory"%>
<%@page import="com.kh.contacts.model.vo.Contacts"%>
<%@page import="com.kh.user.model.vo.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>
<%
	
	// 현재 카테고리번호 -> JS에서 사용
	int currentCategoryNo = 0;
	if(request.getAttribute("categoryNo") != null) {
		currentCategoryNo = (int)request.getAttribute("categoryNo");
	}

	// 현재 주소록번호 -> JS에서 사용
	int currentContactsNo = 0;
	if(request.getAttribute("contactsNo") != null) {
		currentContactsNo = (int)request.getAttribute("contactsNo");
	}

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
		// 별도 js 파일로 값을 가져가기 위함. 
		const loginUserJson = `<%= loginUserJson %>`; // json 형태
		const contextPath = `<%= contextPath2 %>`;
		const currentCategoryNo = `<%= currentCategoryNo %>`;
		const currentContactsNo = `<%= currentContactsNo %>`;
	</script>
	

	<!-- ==================== 사이드바 ==================== -->
	<aside>

		<!-- ------- 대분류: 공유주소록 ------- -->
		<div class="big-cate public-contacts">

			<h2 class="big-cate__title">
				공유 주소록
				<% if(loginUser.getUserAuth().equals("A")) { // 관리자인경우 %>
					<div class="addContactsBtn" onclick="showModal_AddPublicContacts();">
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
								<span class="contactsName"><%= c.getContactsName() %></span>
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
				<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력해주세요." maxlength="20">
				<span class="material-symbols-outlined icon">search</span>
			</div>

			<div class="search-options">
				<input type="radio" id="search-by-userName" name="search-option" checked>
				<label for="search-by-userName">이름</label>

				<input type="radio" id="search-by-userId" name="search-option">
				<label for="search-by-userId">아이디</label>
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





		<!-- ==================== 처음엔 안보였다가, 필요시 동적으로 보여질 내용들 ==================== -->

		<!-- modal창 - 프로필사진 확대  -->
		<div id="imageModal" style="display: none;">
			<span id="closeModal">&times;</span>
			<img id="modalImg" src="" alt="Larger Image">
		</div>

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

				<hr>
				<input type="submit" value="추가" onclick="insertContactsMember();">
			</div>
		</div>

		<!-- modal창 - 공유주소록 추가 -->
		<div class="modal-bg modal-addPublicContact-bg">
			<div class="modal modal-addPublicContact">
				<span class="material-symbols-rounded icon closeBtn" onclick="closeModalByBtn(this);">close</span>

				<h3>공유주소록 추가</h3>
				<p>공유주소록을 추가합니다. 카테고리를 선택후, 주소록의 이름을 입력해주세요.</p>
				<hr>

				<h4>카테고리 선택</h4>

				<input type="radio" id="existingCategory" name="isNewCategory" value="N" checked>
				<label for="existingCategory">기존 카테고리</label>

				<input type="radio" id="newCategory" name="isNewCategory" value="Y">
				<label for="newCategory">새로운 카테고리</label>

				<br><br>

				<div class="categorySelectDiv">
					<p>카테고리를 선택해주세요.</p>
					<select name="categorySelect" id="categorySelect">
						<!-- AJAX로 동적으로 카테고리 리스트(option) 들어올 공간 -->
					</select>
				</div>

				<div class="categoryNameInputDiv">
					<p>새로운 카테고리명을 입력해주세요.</p>
					<input type="text" name="newCategoryName" id="newCategoryName" maxlength="16">
				</div>

				<br><br>

				<h4>주소록명</h4>

				
				<p>새로운 주소록명을 입력해주세요.</p>
				<input type="text" name="newPublicContactsName" id="newPublicContactsName" maxlength="16">

				<hr>
				<input type="submit" value="추가" onclick="addPublicContacts();">
			</div>
		</div>

		<!-- modal창 - 개인주소록 추가 -->
		<div class="modal-bg modal-addPrivateContact-bg">
			<div class="modal modal-addPrivateContact">
				<span class="material-symbols-rounded icon closeBtn" onclick="closeModalByBtn(this);">close</span>

				<h3>개인주소록 추가</h3>
				<p>개인주소록을 추가합니다. 새로운 주소록의 이름을 입력해주세요.</p>
				<hr>
				<label for="newPrivateContactsName">새 주소록 이름 :</label>
				<input type="text" name="newPrivateContactsName" id="newPrivateContactsName" maxlength="16">
				<hr>
				<input type="submit" value="추가" onclick="insertPrivateContacts();">
			</div>
		</div>

		<!-- modal창 - 카레고리 수정 -->
		<div class="modal-bg modal-editCategory-bg">
			<div class="modal modal-editCategory">
				<span class="material-symbols-rounded icon closeBtn" onclick="closeModalByBtn(this);">close</span>

				<h3>카테고리 수정</h3>
				<p>카테고리를 수정합니다. 원하시는 항목을 선택해주세요.</p>
				<hr>
				
				<p>선택하신 카테고리명:</p>
				<h4 class="categoryName"></h4> <!-- 동적으로 카테고리명이 들어감 -->
				<hr>

				<input type="submit" value="카테고리명 변경" onclick="confirmEditCategory();">
				<input type="submit" value="카테고리 삭제" onclick="confirmDeleteCategory();">
				<hr>
			</div>
		</div>

		<!-- modal창 - 주소록 수정 -->
		<div class="modal-bg modal-editContacts-bg">
			<div class="modal modal-editContacts">
				<span class="material-symbols-rounded icon closeBtn" onclick="closeModalByBtn(this);">close</span>

				<h3>주소록 수정</h3>
				<p>주소록을 수정합니다. 원하시는 항목을 선택해주세요.</p>
				<hr>
				
				<p>선택하신 주소록명:</p>
				<h4 class="contactsName"></h4> <!-- 동적으로 주소록명이 들어감 -->
				<hr>

				<input type="submit" value="주소록명 변경" onclick="confirmEditContacts()">
				<input type="submit" value="주소록 삭제" onclick="confirmDeleteContacts()">
				<hr>
			</div>
		</div>
		
	</main>
	
</body>
</html>