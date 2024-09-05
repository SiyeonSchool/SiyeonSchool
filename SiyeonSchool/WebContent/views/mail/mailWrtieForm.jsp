<%@page import="com.kh.mail.model.vo.MailReceiver"%>
<%@page import="com.kh.mail.model.vo.MailWriteSearchResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/common.jsp" %>

<%
	MailWriteSearchResult teacher = (MailWriteSearchResult)request.getAttribute("teacher");
	// 선생님 - 사용자번호, 사용자이름, 사용자아이디

	ArrayList<MailWriteSearchResult> studentList = (ArrayList<MailWriteSearchResult>)request.getAttribute("studentList");
	// 학생목록 - 사용자번호, 사용자이름, 사용자아이디
	
	ArrayList<MailWriteSearchResult> contactsList = (ArrayList<MailWriteSearchResult>)request.getAttribute("contactsList");
	// 모든주소록(공유+개인) - 주소록번호, 주소록이름
	
	// 검색결과 리스트
	ArrayList<MailWriteSearchResult> searchResultList = new ArrayList<MailWriteSearchResult>();
	searchResultList.addAll(studentList);
	searchResultList.addAll(contactsList);
	
	// 메일답장시 - 원본 메일의 정보를 화면에 미리 넣어주기
	Mail m = null;
	String mailTitle = "";
	String mailContent = "";
	
	if(request.getAttribute("m") != null) { // 메일을 컨트롤러로부터 전달받았다면
		m = (Mail)request.getAttribute("m"); // 메일정보
		ArrayList<MailReceiver> mrListR = new ArrayList<MailReceiver>(); // 수신인 리스트
		ArrayList<MailReceiver> mrListC = new ArrayList<MailReceiver>(); // 참조인 리스트
		
		if(request.getAttribute("mrListR") != null){
			mrListR.addAll((ArrayList<MailReceiver>)request.getAttribute("mrListR"));
		}
		if(request.getAttribute("mrListC") != null){
			mrListC.addAll((ArrayList<MailReceiver>)request.getAttribute("mrListC"));
		}
	
		// 수신인리스트 -> '이름(아이디)' 형식으로 하나의 문자열로 합침
		String mrListR_str = "";
		StringBuilder sb2 = new StringBuilder();
		for(int i=0; i<mrListR.size(); i++) {
			if(i != 0) {
				sb2.append(", ");
			}
			sb2.append(mrListR.get(i).getReceiverName() + "(" + mrListR.get(i).getReceiverId() + ")");
		}
		mrListR_str = sb2.toString();
		
		// 참조인리스트 -> '이름(아이디)' 형식으로 하나의 문자열로 합침
		String mrListC_str = "";
		StringBuilder sb3 = new StringBuilder();
		for(int i=0; i<mrListC.size(); i++) {
			if(i != 0) {
				sb3.append(", ");
			}
			sb3.append(mrListC.get(i).getReceiverName() + "(" + mrListC.get(i).getReceiverId() + ")");
		}
		mrListC_str = sb3.toString();

		
		// 메일정보 + 원본 메일내용 => form에 넣어주기
		StringBuilder sb = new StringBuilder();
		sb.append("<br><br><br><br>");
		sb.append("<div id='originalMsg'>");
		sb.append("----- Original Message -----<br>");
		sb.append("<b>From:</b> " + m.getUserName() + " (" + m.getUserId() + ")<br>");
		sb.append("<b>To:</b> " + mrListR_str + "<br>");
		sb.append("<b>Cc:</b> " + mrListC_str + "<br>");
		sb.append("<b>Sent:</b> " + m.getSendDate() + "<br>");
		sb.append("<b>Subject:</b> " + m.getMailTitle());
		sb.append("</div>");
		sb.append("<br><br><br><br>");
		
		sb.append(m.getMailContent()); // 메일 원본내용
		mailContent = sb.toString();
		
		// 메일제목
		mailTitle = "RE: " + m.getMailTitle();
	};
	
%>

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
				    oEditors.getById["ir1"].exec("PASTE_HTML", [`<%= mailContent %>`]);
				},
				fCreator: "createSEditor2"
			});
		      
			//메일 보내기 버튼 클릭시 form 전송
			$("#send").click(function(){
			    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
				if(validateMailtitle() && validateMailReceiver()){
					setReceiverCheckboxesChecked();
					$("#frm").submit();
				};
			});

			//메일 임시저장 버튼 클릭시 form 전송
			$("#tempSave").click(function(){
			    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
				if(validateMailtitle()){
					setReceiverCheckboxesChecked();
					alert("임시저장버튼 수행됨!");
					changeIsSentToT();
					$("#frm").submit();
				};
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
					<% if(!currentMailbox.equals("wm")) { %>
						<h2>메일쓰기</h2>
					<% } else { %>
						<h2>내게쓰기</h2>
					<% } %>
					<div class="email-btns">
						<input type="button" class="btn" id="send" value="보내기"/>
						<input type="button" class="btn" id="tempSave" value="임시저장">
						<input type="hidden" id="isSent" name="isSent" value="S">
						<input type="button" class="btn" value="취소" onclick="cancelWritingMail()"/>
					</div>
				</div>

				<table>

					<tr class="mailtitle">
						<td class="td-left">제목</td>
						<td class="td-right">
							<input type="text" id="title" name="title" placeholder="제목을 입력해주세요." maxlength="50" value="<%= mailTitle %>" required/>
						</td>
					</tr>

					<% if(!currentMailbox.equals("wm")) { %> <!-- 내게쓰기가 아닐경우 -->
						<tr class="receiver">
							<td class="td-left">받는사람</td>
							<td class="td-right">

								<div class="search">
									<input id="searchReceiver" class="search-input" type="text" name="keyword" placeholder="검색어를 입력해주세요.">
									<span class="icon material-symbols-outlined icon">search</span>

									<div id="receiverType">
										<input type="radio" id="rTypeR" name="receiverType" value="r" checked>
										<label for="rTypeR">수신</label>
										
										<input type="radio" id="rTypeC" name="receiverType" value="c">
										<label for="rTypeC">참조</label>
										
										<input type="radio" id="rTypeS" name="receiverType" value="s">
										<label for="rTypeS">비밀</label>
									</div>
								</div>

								<div id="searchResult">
									<ul>
										<li class="searchResult-data">
											<input type="hidden" class="pkNo" value="allUsers">
											<span class="name">* 모든 사용자</span>
											<input type="hidden" class="isUser" value="false">
										</li>

										<li class="searchResult-data">
											<input type="hidden" class="pkNo" value="allStudents">
											<span class="name">* 모든 학생</span>
											<input type="hidden" class="isUser" value="false">
										</li>

										<li class="searchResult-data">
											<input type="hidden" class="pkNo" value="teacher">
											<span class="name">* 선생님 - <%= teacher.getName() %> </span>
											<input type="hidden" class="isUser" value="true">
											<span class="userId">(<%= teacher.getUserId() %>)</span>
										</li>

										<% for(MailWriteSearchResult sr : searchResultList) { %>
											<li class="searchResult-data">
												<input type="hidden" class="pkNo" value="<%= sr.getPkNo() %>">
												<span class="name"><%= sr.getName() %></span>
												<input type="hidden" class="isUser" value="<%= sr.isUser() %>">
												<% if(sr.isUser()) { %>
													<span class="userId">(<%= sr.getUserId() %>)</span>
												<% } %>
											</li>
										<% } %>
									</ul>
								</div>

								<ul class="list-header">
									<li>
										<div class="rUserName">받는사람</div>
										<div class="rType">수신구분</div>
										<div class="rDelete">
											<span class="icon material-symbols-rounded">close</span>
										</div>
									</li>
								</ul>

								<!-- 수신인 동적으로 추가될 공간 -->
								<ul class="list-contents"></ul>

								<div class="listSummary">
									<p>총 <span class="total">0</span>명</p>
									<p class="detailCount">( 수신 <span class="r">0</span>, 참조 <span class="c">0</span>, 비밀 <span class="s">0</span> )</p>
								</div>

							</td>
						</tr>
					<% } else { %>
						<!-- 내게쓰기 -->
						<input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>">
						<input type="hidden" name="rType" value="r">
					<% } %>
						

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
		
		<div class="btn" id="btnToGoUp">
			<span class="icon material-icons-round">arrow_upward</span>
		</div>
	</main>
	
</body>
</html>