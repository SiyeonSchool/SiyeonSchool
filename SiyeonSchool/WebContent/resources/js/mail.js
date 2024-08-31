console.log("메일 js 실행됨!");




// ================================ 메인 상세조회 ================================ 

// 뒤로가기(목록으로) 버튼
$("main.mail-detail .btnToGoBack").click(()=>{
    location.href = sessionStorage.getItem("previousPage");
})


// 위로가기 버튼
$(()=>{
    // Use the main.mail-detail element for scrolling
    var $mailDetail = $('.mail-detail');
    
    $mailDetail.scroll(function() {
        if ($(this).scrollTop() > 500) {
            $('#btnToGoUp').fadeIn();
        } else {
            $('#btnToGoUp').fadeOut();
        }
    });

    $("#btnToGoUp").click(function() {
        $mailDetail.animate({
            scrollTop: 0
        }, 400);
        return false;
    });
});


// ================================ 메일쓰기 ================================ 

// ------------- 수신인 검색 관련 -------------
const $searchReceiverInput = $("main.mail-write #searchReceiver"); // 검색창
const $searchResultDiv = $("main.mail-write #searchResult");       // 검색결과 div

// 검색창 클릭시: 모든사용자 & 모든주소록 리스트 보여주기
$searchReceiverInput.on('focus', function() {
    $searchResultDiv.show(); // 검색결과 div 보여주기
});

// 문서전체 클릭 이벤트: 검색결과 div 숨기기 (input이나 검색결과를 제외하고 클릭한 경우)
$(document).on('click', function(event) {
    // 클릭이 input이나 검색결과창을 벗어난 경우: 검색결과 div 숨기기
    if (!$(event.target).closest($searchReceiverInput).length && !$(event.target).closest($searchResultDiv).length) {
        $searchResultDiv.hide();
    }
});

// 검색어 입력 이벤트: 해당 키워드를 포함한 리스트만 보여주기
$searchReceiverInput.on('keyup', function() {
    $searchResultDiv.show(); // 검색결과 div 보여주기
    const keyword = $(this).val().toLowerCase();

    // 각각의 데이터를 순회하면서 키워드를 포함한 데이터만 보여주기
    let matchCount = 0;
    $("#searchResult ul .searchResult-data").each(function() {
        const contentsToFind = $(this).find(".name").text().toLowerCase();
        if (contentsToFind.includes(keyword)) {
            $(this).show();
            matchCount++;
        } else {
            $(this).hide();
        }
    });

    // 매칭되는게 없는 경우 div를 숨겨줌.
    if (matchCount === 0) {
        $searchResultDiv.hide();
    }
});

// 검색결과 클릭 이벤트 (Optional): 선택한 항목 처리
$searchResultDiv.on('click', '.searchResult-data', function(event) {
    event.stopPropagation(); // 이벤트 버블링 막기

    console.log('Selected item:', $(this).find('.name').text()); // Handle the click on the search result item here
    
    $searchResultDiv.hide(); // 검색결과 div 숨기기
});


function addToReceiverList(){

}