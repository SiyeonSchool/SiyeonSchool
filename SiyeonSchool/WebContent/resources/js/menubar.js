// 알림 아이콘 클릭시
$("#notification").click(function() {
    $(".hidden-notification").toggleClass("hidden"); // 숨겨진창(알림내용) 보여주거나 숨김. 
    $(".hidden-profile").addClass("hidden"); // 열려있는 프로필 창 숨기기
});

// 프로필 아이콘 클릭시
$(".profile").click(function() {
    $(".hidden-profile").toggleClass("hidden"); // 숨겨진창(마이페이지, 로그아웃) 보여주거나 숨김.
    $(".hidden-notification").addClass("hidden"); // 열려있는 프로필 숨겨진창 숨기기
});

// 열려있던 알림창 or 프로필창 외에 다른 곳 클릭시 -> 열려있던창 닫기.
$(window).on('click', function(event) {
    if (!$(event.target).closest('#notification, .hidden-notification, .profile, .hidden-profile').length) { // 클릭된 요소가 괄호안에 있는 요소가 아니면, ("closest()"는 조상중에서 괄호안에 있는 요소들이 있는지 찾음.)
        $(".hidden-notification, .hidden-profile").addClass("hidden"); // 열려있던창 닫기 (참고... "쉼표"를 사용하면 여러개를 한번에 선택가능!)
    }
});
