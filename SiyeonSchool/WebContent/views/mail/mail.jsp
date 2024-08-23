<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

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
					<p>안읽음</p>
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
				<li>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="0">
						<span class="icon mailboxNo-icon material-symbols-rounded">stacked_email</span>
						<span class="mailboxName">전체메일함</span>
					</div>
				</li>
	
				<!-- 받은메일함 -->
				<li class="active">
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-round">mail_outline</span>
						<span class="mailboxName">받은메일함</span>
						<span class="mailCount">(5)</span>
					</div>
				</li>
	
				<!-- 보낸메일함 -->
				<li>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">send</span>
						<span class="mailboxName">보낸메일함</span>
					</div>
				</li>
	
				<!-- 임시보관함 -->
				<li>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">note</span>
						<span class="mailboxName">임시보관함</span>
						<span class="mailCount">(0)</span>
					</div>
				</li>
	
				<!-- 내게쓴메일함 -->
				<li>
					<div class="mailbox-div">
						<input type="hidden" name="mailboxNo" value="">
						<span class="icon mailboxNo-icon material-icons-outlined">article</span>
						<span class="mailboxName">내게쓴메일함</span>
					</div>
				</li>
	
				<!-- 휴지통 -->
				<li>
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
					<option value="mailTitle">제목</option>
					<option value="mailContent">내용</option>
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
				<li class="mail-column read">읽음</li>
				<li class="mail-column sender">작성자</li>
				<li class="mail-column mailTitle">메일 제목</li>
				<li class="mail-column mailboxTitle">메일함</li>
				<li class="mail-column sentDate">발신일시</li>
			</ul>
		</section>
		
		<!-- --------------------- 메일 목록 --------------------- -->
		<section class="mail-list">
			<ul>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>

				<!-- 하나의 메일 -->
				<li class="mail">
					<div class="mail-column checkbox jc-center">
						<input type="checkbox">
					</div>
					
					<div class="mail-column star jc-center">
						<span class="icon star material-symbols-rounded ">star</span>
					</div>

					<div class="mail-column read jc-center">
						<span class="icon mail-icon material-icons-round">mail_outline</span>
					</div>

					<div class="mail-column sender">
						<img class="profile-img" src="/SiS/resources/images/profile_img/user17.jpg">
						<span class="userNameText">공유유</span>
						<span class="userId">(gdhong)</span>
					</div>
					
					<div class="mail-column mailTitle text-left">
						<span>제목이 들어올 자리입니다.</span>
					</div>

					<div class="mail-column mailboxTitle jc-center">
						<span >받은메일함</span>
					</div>

					<div class="mail-column sentDate jc-center">
						<span>2024-05-09  00:00</span>
					</div>
				</li>


			</ul>
		</section>

		<!-- --------------------- 페이징 --------------------- -->
		<section class="paging-area">
			<span class="icon material-icons-outlined">first_page</span>
			<span class="icon material-icons-outlined">navigate_before</span>

			<span class="page currentPage">1</span>
			<span class="page">2</span>
			<span class="page">3</span>
			<span class="page">4</span>

			<span class="icon material-icons-outlined">navigate_next</span>
			<span class="icon material-icons-outlined">last_page</span>
		</section>

	</main>
</body>
</html>