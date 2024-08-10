<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/contacts.css">
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<!-- ==================== 사이드바 ==================== -->
	<aside>
		<div>
			<h2>
				공유 주소록
				<a href="">
					<span class="material-symbols-rounded">add</span>
				</a>
			</h2>
			<ul>
				<li>
					<a href="">
						<div>
							<span class="material-icons-round">people</span>
							<span>모든 사용자</span>
							<span class="userNum">(24)</span>
						</div>
					</a>
				</li>
				<li>
					<a href="">
						<div>
							<span class="material-icons-round">people</span>
							<span class="title">자바 미니 팀프로젝트</span>
						</div>
						<div>
							<span class="material-symbols-rounded">edit</span>
							<span class="material-symbols-rounded">keyboard_arrow_up</span>
						</div>
					</a>
					<ul>
						<li>
							<a href="">
								<span class="material-icons">subdirectory_arrow_right</span>
								<span>1조</span>
								<span>(6)</span>
							</a>
						</li>
						<li>1조</li>
						<li>1조</li>
						<li>1조</li>
						<li>1조</li>
					</ul>

				</li>

			</ul>
		</div>
		<div>
			<h2>내 주소록</h2>
			<ul>
				<li>즐겨찾기 (1)</li>
				<li>개인주소록 (3)</li>
			</ul>
		</div>
	</aside>

	<!-- ==================== 메인 컨텐츠 ==================== -->
	<main>
		<h2>내용 공간</h2>
	</main>
	
</body>
</html>