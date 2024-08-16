/* ==================== 사이드바 ==================== */

// 주소록 카테고리 클릭시: 클릭된 카테고리 하이라이트 + 하위 주소록 숨기거나 보여주기
// ex) "세미 프로젝트" 클릭시, 접어두었던 하위의 "1조" ~ "5조" 펼치기 + 배경색 변경
$(".mid-cate__title").click(function(){

    // 모든 카테고리 하이라이트 제거 (기존 .active 제거)
    $(".mid-cate__title").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 모든 카테고리 하위 주소록 하이라이트 제거 (기존 .active 제거)
    $(".sm-cate").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 클릭된 카테고리 하이라이트 (.active 추가)
    if(!$(this).hasClass("active")){
        $(this).addClass("active");
    };

    // 클릭된 카테고리 하위 주소록 숨기거나 보여주기
    if($(this).next()) { // .mid-cate__contents 가 있으면,
        const contentsArea = $(this).next(); //ul.mid-cate__contents
        const foldIcon = $(this).find("span.icon.fold");

        if(contentsArea.hasClass("hidden")) {
            contentsArea.removeClass("hidden");
            foldIcon.text("keyboard_arrow_up"); // 접기 아이콘도 변경
        }else{
            contentsArea.addClass("hidden");
            foldIcon.text("keyboard_arrow_down");
        }
    };
});

// 주소록 클릭시: 클릭된 주소록 하이라이트 + 해당 주소록 카테고리 하이라이트
// ex) "세미 1조" 클릭시 해당 주소록 하이라이트 + 상위의 "세미 프로젝트" 하이라이트
$(".mid-cate__contents").on("click", ".sm-cate", function(){ // 동적으로 생성된 객체에 효과를 주기 위해 이 방식을 사용함.

    // 모든 카테고리 하이라이트 제거 (기존 .active 제거)
    $(".mid-cate__title").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 모든 주소록 하이라이트 제거 (기존 .active 제거)
    $(".sm-cate").each(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }
    });

    // 클릭된 주소록의 상위 주소록 카테고리 하이라이트 (.active 추가)
    if(!$(this).parent().prev().hasClass("active")){
        $(this).parent().prev().addClass("active");
    }

    // 클릭된 주소록 하이라이트 (.active 추가)
    if(!$(this).hasClass("active")){
        $(this).addClass("active");
    };

});

// 카테고리 클릭시, 해당 카테고리에 속한 주소록 (이름 + 인원수) 사이드바에 뿌려주기
// + 해당 카테고리에 속한 카테고리구성원 메인컨텐츠에 뿌려주기.
// ex) "세미 프로젝트" 클릭시, 하위의 "1조" ~ "5조" 
$(".public-contacts .mid-cate__title.dynamic").click(function(){ // 동적으로 생성된 카테고리 클릭시(.dynamic). (== "모든사용자"를 제외한 나머지 공유주소록 클릭시)
    const categoryNo = $(this).find("input").val();
    const contentsArea = $(this).next();
    
    $.ajax({
        url:"contacts/list.contacts",
        data:{categoryNo:categoryNo},
        success:function(result){
            let value = "";
            for(let i=0; i<result.length; i++) {
                value +=   `<li class="sm-cate">
                            <input type="hidden" name="contactsNo" value="${result[i].contactsNo}">
                                <div>
                                    <span class="material-icons icon">subdirectory_arrow_right</span>
                                    <span> ${result[i].contactsName}</span>
                                    <span class="userCount">(${result[i].userCount})</span>
                                </div>
                            </li>\n`
            }
            contentsArea.html(value); // ul.mid-cate__contents 안에 li 추가
        },
        error:function(){
            console.log("ajax 통신 실패: 주소록 카테고리 " + categoryNo + "번의 데이터 조회실패.");
        },
    })

    selectCategoryUsersList(categoryNo); // 카테고리구성원 메인컨텐츠에 뿌려주기.
});



/* ==================== 메인 컨텐츠 ==================== */

// -------------- 메인 컨텐츠 - 기본조회 --------------

selectAllUsersList(); // 전체사용자조회 실행. 주소록 페이지 들어오면 바로 실행함.

// 사이드바에서 공유주소록 클릭시, 해당하는 주소록구성원 화면에 뿌려주기.
$("aside .mid-cate__contents").on("click", ".sm-cate", function(){ // 동적으로 생성된 객체에 효과를 주기 위해 이 방식을 사용함.
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    selectContactsMemberList(contactsNo);
})

// 사이드바에서 개인주소록 클릭시, 해당하는 주소록구성원 화면에 뿌려주기.
$("aside .big-cate.private-contacts .mid-cate").click(function(){
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    selectContactsMemberList(contactsNo);
});

// 사이드바에서 "모든사용자" 클릭시, 모든사용자를 메인화면에 뿌려줌.
$("aside .public-contacts li.allUsers").click(function(){
    selectAllUsersList();
});


const mainContentsUserListArea = $(".section__list-content ul");

// 전체사용자조회
function selectAllUsersList() {
    $.ajax({
        url:"contacts/list.allUsers",
        data:{},
        success:function(result){
            mainContentsUserListArea.html(convertUserListToStr(result)); // 화면에 전체사용자 리스트 뿌려주기.
            $(".allUsers .userCount").text(`(${result.length})`); // 사이드바 카테고리 중, "모든사용자"의 인원수 채워넣기. ex) 모든사용자(31)
        },
        error:function(){
            console.log("ajax 통신 실패: 전체사용자 조회실패).");
        },
    })
}

// 카테고리구성원조회 : 카테고리 클릭시, 해당하는 카테고리구성원 화면에 뿌려주기
function selectCategoryUsersList(categoryNo){
    $.ajax({
        url:"contacts/list.categoryUsers",
        data:{categoryNo:categoryNo},
        success:function(result){
            mainContentsUserListArea.html(convertUserListToStr(result));
        },
        error:function(){
            console.log("ajax 통신 실패: 카테고리 " + categoryNo +  "번 구성원 조회실패.");
        },
    })
}

// 주소록구성원조회 : 주소록 클릭시, 해당하는 주소록구성원 화면에 뿌려주기
function selectContactsMemberList(contactsNo){
    $.ajax({
        url:"contacts/list.contactsUsers",
        data:{contactsNo:contactsNo},
        success:function(result){
            mainContentsUserListArea.html(convertUserListToStr(result));
        },
        error:function(){
            console.log("ajax 통신 실패: 주소록 " + contactsNo +  "번 구성원 조회실패.");
        },
    })
}

// 리스트문자열변환 : 유저리스트를 화면에 뿌려줄수있는 문자열로 바꿔주기
function convertUserListToStr(userList){
    let str = "";
    for(let i=0; i<userList.length; i++) {

        let classValue;
        if(userList[i].star === "Y"){
            classValue = "material-icons-round icon star fill"; // 노랑색 색칠된 별
        }else {
            classValue = "material-symbols-rounded icon star"; // 회색 테두리만 있는 별
        }

        str += `<!-- 한 줄의 사용자 데이터 -->
                <li class="userInfo">
                    <div class="checkbox">
                        <input type="checkbox" value="${userList[i].userNo}">
                    </div>
                    <div class="star">
                        <span class="${classValue}">star</span>
                    </div>
                    <div class="userName">
                        <span class="material-symbols-rounded icon profile-pic">account_circle</span>
                        ${userList[i].userName}
                    </div>
                    <div class="userId">${userList[i].userId}</div>
                    <div class="role">${userList[i].role}</div>
                    <div class="birthday">${userList[i].birthday}</div>
                    <div class="phone">${userList[i].phone}</div>
                </li>\n`;
    }
    return str;
}

// -------------- 메인 컨텐츠 - 정렬 --------------

// 주소록구성원 정렬 : 클릭한 정렬기준으로 해당하는 주소록 구성원들을 정렬하여 화면에 뿌려주기.
$("main .section__list-header li div span").click(function(){
    
    // 정렬기준
    const sortBy = $(this).parent().attr("class"); //star, userName, userId, role, birthday, phone
    console.log(sortBy);
    
    // 정렬순서 (내림차순/오름차순)
    const arrowSpan = $(this).parent().find("span.drop_down");
    let isDesc; // 내림차순? (true: 내림차순 / false: 오름차순)
    if(arrowSpan.text() == "arrow_drop_down") {
        arrowSpan.text("arrow_drop_up");
        isDesc = false;
    }else {
        arrowSpan.text("arrow_drop_down");
        isDesc = true;
    }
    
    // 다른 정렬기준을 화면에서 초기화
    $(this).parent().siblings().find("span.drop_down").text("arrow_drop_down");

    // 정렬시키는 메소드 실행
    const activeEl = $(".active"); // 사이드바에서 해당 주소록카테고리 혹은 주소록 번호를 가져오기 위함.

    if(activeEl.length == 1) { // 주소록카테고리 클릭한 경우
        const hiddenInput = activeEl.first().find("input");
        
        if(hiddenInput.attr("name") == "categoryNo"){ // 주소록카테고리
            sortUsersList(hiddenInput.val(), null, sortBy, isDesc); // 매개변수 (categoryNo, contactsNo, sortBy, isDesc)
        }else { // 개인주소록
            sortUsersList(null, hiddenInput.val(), sortBy, isDesc); // 매개변수 (categoryNo, contactsNo, sortBy, isDesc)
        };

    } else if(activeEl.length == 2) { // 주소록카테고리 하위의 주소록 클릭한경우
        const hiddenInput = activeEl.last().find("input");
        sortUsersList(null, hiddenInput.val(), sortBy, isDesc); // 매개변수 (categoryNo, contactsNo, sortBy, isDesc)
    }
});

// 유저정렬 : 정렬조건에 따라 db에서 조회하여 화면에 뿌려주기.
function sortUsersList(categoryNo, contactsNo, sortBy, isDesc) {
    $.ajax({
        url:"contacts/list.orderBy",
        data:{
            categoryNo:categoryNo,
            contactsNo:contactsNo,
            sortBy:sortBy,
            isDesc:isDesc,
        },
        success:function(result){
            mainContentsUserListArea.html(convertUserListToStr(result));
        },
        error:function(){
            if(contactsNo == null) {
                console.log(`ajax 통신 실패: 주소록카테고리${categoryNo}번 ${sortBy} ${isDesc}로 구성원정렬실패.`);
            } else if(categoryNo == null) {
                console.log(`ajax 통신 실패: 주소록${contactsNo}번 ${sortBy} ${isDesc}로 구성원정렬실패.`);
            }
        },
    })
}


