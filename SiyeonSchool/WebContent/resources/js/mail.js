console.log("메일 js 실행됨!");




// ================================ 메인 상세조회 ================================ 

// 뒤로가기(목록으로) 버튼
$("main.mail-detail .btnToGoBack").click(()=>{
    location.href = sessionStorage.getItem("previousPage");
})


// 위로가기 버튼
$(function() {
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