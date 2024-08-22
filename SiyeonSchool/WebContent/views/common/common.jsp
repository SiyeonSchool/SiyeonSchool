<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String contextPath = request.getContextPath(); // "/SiS"
%>

<!-- 
    모든 페이지에 공통으로 적용할 속성을 모아놓은 곳입니다.

    각 jsp 파일에 이"common.jsp"를 include 하실때, <!DOCTYPE html> 이전에 하시길 권장드립니다.

    이유 1) 각자 선언하는 CSS가 적용이 안될 수 있습니다.
    만약 body 태그 안에 "common.jsp"를 include 하시면, 본인이 head내에 style을 주셨던게 적용이 안됩니다.
    "common.jsp"에 있는 "CSS 초기화 CDN"이 본인이 작성하신 CSS 뒤에 읽히기 때문에, 덮혀쓰이면서 모든 CSS를 초기화 해버립니다.

    이유 2) title을 공통으로 적용하기 유용합니다.
    브라우저 탭에 보이게 되는 title 부분은, CSS와 다르게 가장 먼저 읽힌 것이 적용이 됩니다.
    따라서 "common.jsp"를 먼저 include 하게 된다면, 각 페이지 마다 별도의 title이 있더라도 무시하고 일관되게 title을 표시합니다.

    이유 3) 공통으로 쓰이게 될 라이브러리들을 잘 적용할 수 있습니다. (예상)
    모든 jsp가 열리자마자 실행되기 때문에, body태그 내에 적용할때보다 더 좋습니다. (style에서 끌어다 쓸 수있기때문)
-->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<title>시연스쿨</title>

    <!-- favicon -->
    <link rel="icon" href="./favicon.png">

    <!-- CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"> <!-- CSS 초기화 CDN -->
    <link rel="stylesheet" href="resources/css/common.css"> <!-- 공통 CSS -->

    <!-- 구글 아이콘 (material io) : div class에 "material-icons" 추가하고, 해당 텍스트를 div의 내용에 넣으면 적용가능 
                                    ex) <div class="material-icons">search</div> -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp|Material+Symbols+Rounded|Material+Symbols+Outlined" rel="stylesheet">

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
    <!-- common으로 head 태그내 내용들을 쓰기 위한 파일이라서, body 부분 필요없음 -->
</html>