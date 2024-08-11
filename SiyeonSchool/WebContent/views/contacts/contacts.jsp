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

		<!-- ------- 공유 주소록 ------- -->
		<!-- 대분류 -->
		<div class="big-cate">

			<h2 class="big-cate__title">
				공유 주소록
				<a href="">
					<span class="material-symbols-rounded icon">add</span>
				</a>
			</h2>

			<ul class="big-cate__contents">

				<!-- 중분류 -->
				<li class="mid-cate">
					<a href="" class="mid-cate__title">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span>모든 사용자</span>
							<span class="userCount">(24)</span>
						</div>
					</a>
				</li>

				<!-- 중분류 -->
				<li class="mid-cate">
					<a href="" class="mid-cate__title">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span class="title">자바 미니 팀프로젝트</span>
							<span class="material-symbols-rounded icon fold">keyboard_arrow_up</span>
						</div>
						<div>
							<span class="material-symbols-rounded icon edit">edit</span>
						</div>
					</a>

					<ul class="mid-cate__contents">

						<!-- 소분류 -->
						<li class="sm-cate">
							
							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>1조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>2조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>3조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>4조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>5조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

						</li> <!-- sm-cate -->

					</ul> <!-- .mid-cate__contents -->
				</li> <!-- .mid-cate -->

				<!-- 중분류 -->
				<li class="mid-cate">
					<a href="" class="mid-cate__title">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span class="title">웹 클론 팀프로젝트</span>
						</div>
						<div>
							<span class="material-symbols-rounded icon edit">edit</span>
							<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
						</div>
					</a>

					<ul class="mid-cate__contents">

						<!-- 소분류 -->
						<li class="sm-cate hidden">
							
							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>1조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>2조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>3조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>4조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>5조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

						</li> <!-- sm-cate -->
						
					</ul> <!-- .mid-cate__contents -->
				</li> <!-- .mid-cate -->

				<!-- 중분류 -->
				<li class="mid-cate">
					<a href="" class="mid-cate__title">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span class="title">세미 프로젝트</span>
						</div>
						<div>
							<span class="material-symbols-rounded icon edit">edit</span>
							<span class="material-symbols-rounded icon fold">keyboard_arrow_down</span>
						</div>
					</a>

					<ul class="mid-cate__contents">

						<!-- 소분류 -->
						<li class="sm-cate hidden">
							
							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>1조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>2조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>3조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>4조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

							<a href="">
								<div>
									<span class="material-icons icon">subdirectory_arrow_right</span>
									<span>5조</span>
									<span class="userCount">(6)</span>
								</div>
								<div>
									<span class="material-symbols-rounded icon edit">edit</span>
								</div>
							</a>

						</li> <!-- sm-cate -->
						
					</ul> <!-- .mid-cate__contents -->
				</li> <!-- .mid-cate -->

			</ul> <!-- .big-cate__contents -->
		</div> <!-- .big-cate -->

		<!-- ------- 개인 주소록 ------- -->
		<!-- 대분류 -->
		<div class="big-cate">

			<h2 class="big-cate__title">
				개인 주소록
				<a href="">
					<span class="material-symbols-rounded icon">add</span>
				</a>
			</h2>

			<ul class="big-cate__contents">

				<!-- 중분류 -->
				<li class="mid-cate">
					<a href="" class="mid-cate__title">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span>기본주소록</span>
							<span class="userCount">(3)</span>
						</div>
					</a>
				</li>

				<!-- 중분류 -->
				<li class="mid-cate">
					<a href="" class="mid-cate__title">
						<div>
							<span class="material-icons-round icon people">people</span>
							<span class="title">세미 프로젝트</span>
						</div>
						<div>
							<span class="material-symbols-rounded icon edit">edit</span>
						</div>
					</a>
				</li>



			</ul> <!-- .big-cate__contents -->
		</div> <!-- .big-cate -->
	</aside>

	<!-- ==================== 메인 컨텐츠 ==================== -->
	<main>
		<h2>내용 공간</h2>
	</main>
	
</body>
</html>