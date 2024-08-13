/* ==================== 사이드바 ==================== */

// 중분류 클릭시: 하위 소분류 숨기거나 보여주기 + 선택된 중분류 하이라이트
// ex) "세미 프로젝트" 클릭시, 하위의 "1조" ~ "5조" 보여주기 + 배경색 변경
$(".mid-cate__title").click(function(){

    // 선택된 곳 하이라이트 - 기존 .active 제거
    $(".mid-cate__title").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 다른 소분류 하이라이트 - 기존 .active 제거
    $(".sm-cate").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 선택된 곳 하이라이트 - .active 추가
    if(!$(this).hasClass("active")){
        $(this).addClass("active");
    };

    // 선택된 곳 하위 소분류 숨기거나 보여주기
    if($(this).next()) { // .mid-cate__contents 가 있으면,
        if($(this).next().hasClass("hidden")) {
            $(this).next().removeClass("hidden");
            $(this).find("span.icon.fold").text("keyboard_arrow_up"); // 접기 아이콘도 변경
        }else{
            $(this).next().addClass("hidden");
            $(this).find("span.icon.fold").text("keyboard_arrow_down");
        }
    };
});

// 소분류 클릭시: 소분류 하이라이트 + 상위 중분류 하이라이트
$(".sm-cate").click(function(){

    // 선택된 곳의 중분류 하이라이트 - 기존 .active 제거
    $(".mid-cate__title").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 선택된 소분류 하이라이트 - 기존 .active 제거
    $(".sm-cate").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 선택된 곳의 중분류 하이라이트 - .active 추가
    if(!$(this).parent().prev().hasClass("active")){
        $(this).parent().prev().addClass("active");
    }

    // 선택된 소분류 하이라이트 - .active 추가
    if(!$(this).hasClass("active")){
        $(this).addClass("active");
    };

});


// ############## 아직 미완성!!! #####################################
// ajax로 클릭된 주소록 데이터 가져오기
$(".public-contacts .mid-cate__title").click(function(){

    console.log($(this).find("input").val());

});



/* ==================== 메인 컨텐츠 ==================== */

// 컨텐츠 헤더 클릭시, 클릭된 헤더를 기준으로 정렬함.
$(".section__list-header ul li>div").click(function(){
    const elClass = $(this).attr("class");

    if(elClass !== "checkbox") { // 체크박스가 아닐때만
        console.log(elClass);

        // ############## 아직 미완성!!! #####################################

    }
});
