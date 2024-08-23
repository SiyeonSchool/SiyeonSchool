/* ====================  JSP에서 가져온 정보 ==================== */
// 로그인유저정보
const loginUser = JSON.parse(loginUserJson); // json string -> object 타입으로 형변환함.

console.log(loginUser);
console.log("currentBoardNo:" + currentBoardNo);


clickBoardElOnSidebar(currentBoardNo);

// 사이드바에서 "게시판번호"로 해당 요소 클릭하기
function clickBoardElOnSidebar(boardNo){
    $(`aside input[type="hidden"][name="board"][value="${boardNo}"]`).parent().click();
}

