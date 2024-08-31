<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<!-- 네이버 스마트에디터(SmartEditor) -->
	<script type="text/javascript" src="<%= contextPath %>/resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script type="text/javascript">
		var oEditors = [];
		$(function(){
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
				//SmartEditor2Skin.html 파일이 존재하는 경로
				sSkinURI: "<%= contextPath %>/resources/SE2/SmartEditor2Skin.html",
				htParams : {
					bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					fOnBeforeUnload : function(){}
				}, 
				fOnAppLoad : function(){
				    //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
				    //oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
				},
				fCreator: "createSEditor2"
			});
		      
			//저장버튼 클릭시 form 전송
			$("#save").click(function(){
			    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			    $("#frm").submit();
			});
		});
	</script>

</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	<%@ include file="mailSidebar.jsp" %>
	
	<!-- ======================================== 메인 ======================================== -->
	
	<main class="mail-write">

		<form id="frm" action="<%= contextPath %>/mail.insert" method="post" enctype="multipart/form-data">

			<div class="mail-write-header">

				<div class="title">
					<h2>메일쓰기</h2>

					<div class="email-btns">
						<input type="button" class="btn" id="save" value="보내기"/>
						<input type="button" class="btn" value="임시저장">
						<input type="button" class="btn" value="취소"/>
					</div>
				</div>

				<table>

					<tr class="mailtitle">
						<td class="td-left">제목</td>
						<td class="td-right">
							<input type="text" id="title" name="title" placeholder="제목을 입력해주세요." required/>
						</td>
					</tr>

					<tr class="receiver">
						<td class="td-left">받는사람</td>
						<td class="td-right">

							<div class="search">
								<input class="search-input" type="text" name="keyword" placeholder="받는사람을 입력해주세요.">
								<span class="icon material-symbols-outlined icon">search</span>

								<div class="receiverType">
									<input type="radio" id="rTypeR" name="rType" value="r" checked>
									<label for="rTypeR">수신</label>
									
									<input type="radio" id="rTypeC" name="rType" value="c">
									<label for="rTypeC">참조</label>
									
									<input type="radio" id="rTypeS" name="rType" value="s">
									<label for="rTypeS">비밀</label>
								</div>
							</div>

							<ul class="list-header">
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">받는사람</div>
									<div class="rType">구분</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
							</ul>

							<ul class="list-contents">
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								<li>
									<div class="rCheckbox">
										<input type="checkbox">
									</div>
									<div class="rUserName">
										<span class="userName">장원영</span>
										<span class="userId">(wyjang)</span>
									</div>
									<div class="rType">수신</div>
									<div class="rDelete">
										<span class="icon material-symbols-rounded">close</span>
									</div>
								</li>
								
							</ul>

							<p class="listSummary">총 10명
								<span>( 수신 8, 참조 1, 비밀 1 )</span>
							</p>

						</td>
					</tr>

					<tr class="attachment">
						<td class="td-left">첨부파일</td>

						<td class="td-right">
							<input type="file" name="upfile">
						</td>
					</tr>
					
				</table>

			</div>

			<div class="mail-write-content">
				<textarea id="ir1" name="content" style="width:100%; height:500px;"></textarea>
			</div>

		</form>
		
	</main>
	
</body>
</html>