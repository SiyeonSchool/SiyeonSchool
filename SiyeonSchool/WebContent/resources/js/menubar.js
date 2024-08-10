console.log("js 테스트");

// 알림 아이콘 클릭시, 숨겨진 창 보여주거나 숨김. (알림내용)
$("#notification").click(function(){
    if($(".hidden-notification").hasClass("hidden")) {
        $(".hidden-notification").removeClass("hidden");
    }else {
        $(".hidden-notification").addClass("hidden");
    }
})

// 프로필 아이콘 클릭시, 숨겨진 창 보여주거나 숨김. (마이페이지, 로그아웃)
$(".profile").click(function(){
    if($(".hidden-profile").hasClass("hidden")) {
        $(".hidden-profile").removeClass("hidden");
    }else {
        $(".hidden-profile").addClass("hidden");
    }
})