/* ====================  JSP에서 가져온 정보 ==================== */
// 로그인유저정보
const loginUser = JSON.parse(loginUserJson); // json string -> object 타입으로 형변환함.

// console.log("currentCategoryNo:" + currentCategoryNo);
// console.log("currentContactsNo:" + currentContactsNo);

/* ==================== DB BYTE 길이 제한 ==================== */
const BYTE_LENGTH_LIMIT = 50; // "주소록명", "카테고리명"의 byte 제한길이 <-- VARCHAR2(50)


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
        url:"contacts/list.categoryContacts",
        type:"get",
        data:{categoryNo:categoryNo},
        success:function(result){
            let value = "";
            for(let i=0; i<result.length; i++) {
                value +=   `<li class="sm-cate">
                                <input type="hidden" name="contactsNo" value="${result[i].contactsNo}">
                                <div>
                                    <span class="material-icons icon">subdirectory_arrow_right</span>
                                    <span class="contactsName"> ${result[i].contactsName}</span>
                                    <span class="userCount">(${result[i].userCount})</span>
                                </div>
                                <div>
								    <span class="material-symbols-rounded icon edit">edit</span>
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

    if (loginUser.userAuth == "A") { // 관리자일경우만
        displayDeleteContactsUserBtn(); // "주소록에서 제외"버튼 보여주기
    }else {
        hideDeleteContactsUserBtn(); // "주소록에서 제외"버튼 숨기기
    }

    addContentsInfoHeader(); // 메인컨텐츠 헤더부분에 "주소록"칸 추가하기
});



/* ==================== 메인 컨텐츠 ==================== */

// -------------- 메인 컨텐츠 - 기본조회 --------------

selectAllUsersList(); // 전체사용자조회 실행. 주소록 페이지 들어오면 바로 실행함.

// 사이드바에서 공유주소록 클릭시, 해당하는 주소록구성원 화면에 뿌려주기.
$("aside .mid-cate__contents").on("click", ".sm-cate", function(){ // 동적으로 생성된 객체에 효과를 주기 위해 이 방식을 사용함.
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    selectContactsMemberList(contactsNo);
    
    if (loginUser.userAuth == "A") { // 관리자일경우만
        displayDeleteContactsUserBtn(); // "주소록에서 제외"버튼 보여주기
    }else {
        hideDeleteContactsUserBtn(); // "주소록에서 제외"버튼 숨기기
    }
})

// 사이드바에서 개인주소록 클릭시, 해당하는 주소록구성원 화면에 뿌려주기.
$("aside .big-cate.private-contacts .mid-cate").click(function(){
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    selectContactsMemberList(contactsNo);
    displayDeleteContactsUserBtn(); // "주소록에서 제외"버튼 보여주기
    removeContentsInfoHeader(); // 메인컨텐츠 헤더부분에 "주소록"칸 제거하기
});

// 사이드바에서 "모든사용자" 클릭시, 모든사용자를 메인화면에 뿌려줌.
$("aside .public-contacts li.allUsers").click(function(){
    selectAllUsersList();
    hideDeleteContactsUserBtn(); // "주소록에서 제외"버튼 숨기기
    removeContentsInfoHeader(); // 메인컨텐츠 헤더부분에 "주소록"칸 제거하기
});

// 메인컨텐츠 공간: 사용자리스트를 표기하기 위한 공간
const mainContentsUserListArea = $(".section__list-content ul");

// 모든사용자조회
function selectAllUsersList() {
    $.ajax({
        url:"contacts/list.allUsers",
        type:"get",
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
        type:"get",
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
        type:"get",
        data:{contactsNo:contactsNo},
        success:function(result){
            mainContentsUserListArea.html(convertUserListToStr(result));
            handleSetLeaderBtn();
        },
        error:function(){
            console.log("ajax 통신 실패: 주소록 " + contactsNo +  "번 구성원 조회실패.");
        },
    })

    removeContentsInfoHeader(); // 메인컨텐츠 헤더부분에 "주소록"칸 제거하기
}

// 리스트문자열변환 : 유저리스트를 화면에 뿌려줄수있는 문자열로 바꿔주기
function convertUserListToStr(userList){
    let str = "";
    if(userList.length == 0) { // 유저가 없는 경우
        str =  `<li class="userInfo noUsers">해당 주소록에 속한 구성원이 없습니다.</li>`;

    }else { // 유저가 있는 경우
        for(let i=0; i<userList.length; i++) {

            // 별: 중요표시가 되어있으면 노란색 별, 없으면 빈 회색 별
            let starClassValue;
            if(userList[i].star === "Y"){
                starClassValue = "material-icons-round icon star fill"; // 노랑색 색칠된 별
            }else {
                starClassValue = "material-symbols-rounded icon star"; // 회색 테두리만 있는 별
            }
            
            // 프로필 이미지: 이미지가 있으면 이미지로, 없으면 회색 아이콘으로 표기
            let profileImg;
            if(userList[i].profilePath){
                profileImg = `<img src="${contextPath}/${userList[i].profilePath}" class="profile-img">`;
            }else {
                profileImg = `<span class="material-symbols-rounded icon profile-icon">account_circle</span>`;
            }

            str += `<!-- 한 줄의 사용자 데이터 -->
                    <li class="userInfo">
                        <div class="checkbox">
                            <input type="checkbox" name="userNo" value="${userList[i].userNo}">
                        </div>
                        <div class="star">
                            <span class="${starClassValue}">star</span>
                        </div>`;

            // 카테고리주소록 유저리스트일경우 : 소속 "카테고리명"도 표기
            if((userList[i]).contactsNo != 0) {
                str += `<div class="contactsInfo">
                                <input type="hidden" name="contactsNo" value="${userList[i].contactsNo}">
                                ${userList[i].contactsName}
                        </div>`;
            }

            str +=      `<div class="userName">
                            ${profileImg}
                            <span class="userNameText">${userList[i].userName}</span>
                        </div>
                        <div class="userId">${userList[i].userId}</div>
                        <div class="role">${userList[i].role}</div>
                        <div class="birthday">${userList[i].birthday}</div>
                        <div class="phone">${userList[i].phone}</div>
                    </li>\n`;
        }
        str += '<li class="noUsers hidden" id="noUsersText">일치하는 검색결과가 없습니다.</li>';
    }
    return str;
}

// 사이드바에서 카테고리주소록 클릭시, 메인컨텐츠 헤더부분에 "주소록"칸 추가하기
function addContentsInfoHeader(){
    const contactsInfoHeader = `<div class="contactsInfo">
                                    <span class="text">주소록</span>
                                    <span class="material-symbols-rounded icon drop_down">arrow_drop_down</span>
                                </div>`;

    if($(".section__list-header li").find("div.contactsInfo").length == 0) { // 기존에 "주소록"칸이 없었을 경우에만
        $(contactsInfoHeader).insertAfter(".section__list-header div.star");
    }
}

// 사이드바에서 카테고리주소록 유저리스트가 아닌 다른 주소록 클릭시, 메인컨텐츠 헤더부분에 "주소록"칸 제거하기
function removeContentsInfoHeader(){
    const contentsInfoHeader = $(".section__list-header li").find("div.contactsInfo"); //"주소록"칸
   
    if(contentsInfoHeader.length > 0) { // 기존에 "주소록"칸이 있었을 경우
        $(contentsInfoHeader).remove();
    }
}


// -------------- 팀장으로 지정 --------------

// "팀장으로 지정" 버튼 보여주거나 숨기기 (관리자한테만)
function handleSetLeaderBtn() {
    const setLeaderBtn = $(".set-leader-btn");

    const isPublicContacts = $(".active").hasClass("sm-cate"); // true:공유주소록, false:개인주소록

    if (loginUser.userAuth == "A" && isPublicContacts) { // 관리자일경우만
        $(setLeaderBtn).removeClass("hidden");
    }else {
        $(setLeaderBtn).addClass("hidden");
    }
}

// "팀장으로 지정" 버튼 클릭시,
function validateSetLeader(){

    // 이미 팀장이 있는지 검증
    if(isThereAlreadyTeamLeader()){
        if(!confirm("이미 팀장이 있습니다. 변경하시겠습니까?")){ // 변경안하겠다면
            return; // 내보내기
        }
    };

    // 체크박스 유저선택 검증
    const checkedUsersEls = getCheckedUsersEl();  // 선택된 체크박스 요소들
    if(checkedUsersEls.length == 0) { 
        alert("선택한 유저가 없습니다.\n팀장으로 지정할 유저를 선택 후 다시 시도해주세요.");
        return;
    } else if(checkedUsersEls.length > 1) {
        alert("너무 많은 유저를 선택하였습니다.\n한명만 선택 후 다시 시도해주세요.");
        return;
    };

    setLeader(checkedUsersEls); //모든 검증을 통과하면 "팀장으로 지정" 기능 실행
}

// 이미 팀장으로 지정된 사람이 있는지 확인 => 있으면 true, 없으면 false 반환
function isThereAlreadyTeamLeader() {
    const isLeaderPresent = $(".section__list-content .userInfo").toArray().some(function(element) {
        const contentsToFind = $(element).find(".role").text().toLowerCase();
        return contentsToFind.includes("팀장");
    });
    return isLeaderPresent;
}

// "팀장으로 지정" 기능
function setLeader(checkedUsersEls){
    const selectedContactsNo = getContactsNoFromSidebar(); // 선택된 주소록번호
    const selectedUserNo = $(checkedUsersEls).val(); // 선택된 유저번호
    updateContactsMember(selectedContactsNo, selectedUserNo); // db에 반영
}

// "팀장으로 지정" - DB에 반영
function updateContactsMember(contactsNo, userNo) {
    // 주소록 구성원 테이블에서
    // 1) 해당주소록 모든 유저를 F로 변경후,
    // 2) 전달받은 userNo만 L로 변경.
    $.ajax({
        url:"contacts/update.member",
        type:"post",
        data:{
            contactsNo:contactsNo,
            userNo:userNo,
        },
        success:function(result){
            alert("성공적으로 팀장으로 지정하였습니다.");
            moveToContactsPage(contactsNo);
        },
        error:function(){
            console.log(`ajax 통신 실패: updateContactsMember()`);
        },
    })
}

// -------------- 메인 컨텐츠 - 정렬 --------------

// 주소록구성원 정렬 : 클릭한 정렬기준으로 해당하는 주소록 구성원들을 정렬하여 화면에 뿌려주기.
$("main .section__list-header li").on("click", "div span", function(){
    // 정렬기준
    const sortBy = $(this).parent().attr("class"); //star, contactsInfo, userName, userId, role, birthday, phone

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
        url:"contacts/list.sortUsers",
        type:"get",
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

// -------------- "메일" 버튼 --------------
$("main .section__search-bar button.email").click(function(){
    // 선택된 체크박스 요소들
    const checkedUsersEls = getCheckedUsersEl(); 

    // 선택한 유저가 있는지 검증
    if(checkedUsersEls.length == 0) {
        alert("선택한 유저가 없습니다.\n메일을 보낼 유저를 선택 후 다시 시도해주세요.");
        return;
    };

    /*
    // "메일" 버튼 클릭한 시점에 체크박스에 선택된 유저들의 번호를 구하여 배열에 저장
    const checkedUsersNoList = []; // 선택된 유저들의 실제 번호가 담기는 배열.
    for(let i=0; i<checkedUsersEls.length; i++){
        checkedUsersNoList.push(checkedUsersEls[i].value);
    }
    */

    alert("메일을 보내고 싶으나, 아직 메일쪽 구현을 안해서...\n\n언젠가는 보낼 수 있겠죠? ^^;");
});

// 체크박스로 체크된 유저들의 요소를 반환함.
function getCheckedUsersEl(){
    return $("main .section__list-content li.userInfo div.checkbox input:checkbox:checked");
}


/* -------------- 검색  -------------- */
// 검색창에 키워드 입력시 모든 사용자를 숨기고, 매칭되는 사용자만 화면에 보이도록함.
$('main .section__search-bar .search-bar input[name="keyword"]').keyup(function(){
    const keyword = $(this).val().toLowerCase(); // 사용자가 입력한 키워드
    hideAllUsers(); // 모든 유저 숨기기
    const searchOption = $(`select[name="search-option"]`).val(); // 검색창 옆에서 선택한 검색옵션 ex) 이름, 아이디, 역할...
    const matchCount = showOnlyMatchingUserName(searchOption, keyword); // 매칭되는유저만 화면에 보여주고 매칭되는 카운트 반환
    handleNoUsersTextEl(matchCount); // 매칭되는게 없는경우 -> 검색결과가 없다고 화면에 표시
});

// 검색 - 모든 유저 숨기기
function hideAllUsers(){
    $(".section__list-content .userInfo").hide();
}

// 검색 - 검색어를 매칭(포함)되는 유저만 보여주기 + 매칭 카운트 반환
function showOnlyMatchingUserName(searchOption, keyword){
    let contentsToFind;
    let matchCount = 0;

    $(".section__list-content .userInfo").each(function() {

        switch(searchOption){
            case "userName": contentsToFind = $(this).find(".userNameText").text().toLowerCase(); break;
            case "userId": contentsToFind = $(this).find(".userId").text().toLowerCase(); break;
            case "role": contentsToFind = $(this).find(".role").text().toLowerCase(); break;
            case "birthday": contentsToFind = $(this).find(".birthday").text().toLowerCase(); break;
            case "phone": contentsToFind = $(this).find(".phone").text().toLowerCase(); break;
        };

        if (contentsToFind.includes(keyword)) {
            $(this).show();
            matchCount ++;
        } else {
            $(this).hide();
        };
    });

    return matchCount ;
};

// 검색결과에 따라 "일치하는 검색결과가 없습니다." 텍스트를 보여주거나 숨기기
function handleNoUsersTextEl(matchCount){
    const noUsersTextEl = $("#noUsersText"); // "검색결과가 없습니다" 글자
    if (matchCount === 0) { // 매칭되는게 없는경우
        if($(noUsersTextEl).hasClass("hidden")){
            $(noUsersTextEl).removeClass("hidden");
        }
    }else { // 매칭되는게 있는경우
        if(!$(noUsersTextEl).hasClass("hidden")){
            $(noUsersTextEl).addClass("hidden");
        }
    }
}

// -------------- 체크박스 --------------
// 헤더의 체크박스 클릭시, 리스트 전체의 체크박스 선택or해제
$("main .section__list-header :checkbox").click(function(){
    const headerCheckbox = $(".section__list-header :checkbox");
    const contentsCheckbox = $(".section__list-content :checkbox");

    if(headerCheckbox.prop("checked")) {
        contentsCheckbox.prop("checked", true);
    }else {
        contentsCheckbox.prop("checked", false);
    };
});

// 각각의 유저줄 클릭시 해당 체크박스 선택or해제
$("main .section__list-content").on("click", "li.userInfo", function(event){

    // 클릭한 요소가 "별"이거나 "사진"인 경우, 이벤트 실행안하고 빠져나감.
    if ($(event.target).is('span.star')) { // 별
        return;
    }else if($(event.target).is('span.profile-icon')) { // 사진없을때 대신 들어올 회색프로필아이콘
        return;
    }else if($(event.target).is('img.profile-img')) { // 프로필사진
        return;
    }else if($(event.target).is(':checkbox')) { // 체크박스
        return;
    }

    const clickedCheckbox = $(this).find(":checkbox");

    if(clickedCheckbox.prop("checked")) { // 이미 체크된경우
        clickedCheckbox.prop("checked", false);
    }else { //체크 안된경우
        clickedCheckbox.prop("checked", true);
    }
});


// -------------- 별 --------------
// 각 유저의 "별"을 클릭하면 DB에 반영 ("별"로 등록/해제)

// "별"에 사용되는 클래스명 선언
const filledStar = "material-icons-round icon star fill"; // "채워진별"의 클래스명
const emptyStar = "material-symbols-rounded icon star";   // "빈별"의 클래스명

// "별" 클릭시 이벤트
$("main .section__list-content").on("click", "span.star", function(){
    const star = $(this); // 클릭된 "별"
    const otherUserNo = star.parent().prev().find(":checkbox").val(); // 바로 이전 div에 있는 checkbox의 value인 userNo를 가져옴.

    if(star.hasClass(emptyStar)) { // "빈별"인 경우 -> "채워진별"로 변경
        insertStar(otherUserNo, star);
    }else {                        // "채워진별" -> "빈별"로 변경
        deleteStar(otherUserNo, star);
    }
});

// "별" 추가
function insertStar(otherUserNo, star){
    $.ajax({
        url:"contacts/insert.star",
        type:"post",
        data:{
            otherUserNo:otherUserNo,
        },
        success:function(result){
            if (result > 0) {
                // 성공시, 화면에 해당 아이콘 변경(빈별 -> 채워진별)
                star.removeClass(emptyStar);
                star.addClass(filledStar);
            }
        },
        error:function(){
            console.log(`ajax 통신 실패: 별 추가 실패`);
        },
    })
}

// "별" 삭제
function deleteStar(otherUserNo, star){
    $.ajax({
        url:"contacts/delete.star",
        type:"post",
        data:{
            otherUserNo:otherUserNo,
        },
        success:function(result){
            if (result > 0) {
                // 성공시, 화면에 해당 아이콘 변경(채워진별 -> 빈별)
                star.removeClass(filledStar);
                star.addClass(emptyStar);
            }
        },
        error:function(){
            console.log(`ajax 통신 실패: 별 삭제 실패`);
        },
    })
}


/* -------------- 프로필 이미지 -------------- */
// 프로필 이미지 크게 확대
function showProfileImgBigger(imgElement) {
    var imgSrc = $(imgElement).attr('src');
    $('#modalImg').attr('src', imgSrc);
    $('#imageModal').fadeIn();
}

// Attach click event to images with a specific class
$('.section__list-content').on('click', 'img.profile-img', function(event) {
    event.stopPropagation(); // Stop the event from bubbling up
    showProfileImgBigger(this);
});


// Function to close the modal
$('#closeModal').click(function() {
    $('#imageModal').fadeOut();
});

// Optional: close the modal when clicking outside the image
$('#imageModal').click(function(event) {
    if (event.target.id === 'imageModal') {
        $('#imageModal').fadeOut();
    }
});



/* -------------- modal창 - "주소록에추가"-------------- */

// modal창을 감싸고있는 배경 element. (전체화면)
const modalAddMember = $("main .modal-addMember-bg");

// "주소록에추가" 버튼 클릭시, modal창 보여줌.
$("main .section__search-bar .btn-group .addBtn").click(function(){

    // 선택된 체크박스 요소들
    const checkedUsersEls =  $("main .section__list-content li.userInfo div.checkbox input:checkbox:checked"); 

    // 선택한 유저가 있는지 검증
    if(checkedUsersEls.length == 0) {
        alert("선택한 유저가 없습니다.\n주소록에 추가할 유저를 선택 후 다시 시도해주세요.");
        return;
    };

    // "주소록에추가" 버튼 클릭한 시점에 체크박스에 선택된 유저들의 번호를 구하여 배열에 저장
    const checkedUsersNoList = []; // 선택된 유저들의 실제 번호가 담기는 배열.
    for(let i=0; i<checkedUsersEls.length; i++){
        checkedUsersNoList.push(checkedUsersEls[i].value);
    }

    // DB에서 목록조회하여 modal에 뿌리고 modal창 열기. 
    $.ajax({
        url:"contacts/list.allContacts",
        type:"get",
        data:{},
        success:function(result){
            // 화면에 뿌려줄수있도록 문자열로 변환
            let resultStr = "";
            for(let i=0; i<result.length; i++){
                resultStr +=   `<input type="radio" name="contactsNo" value="${result[i].contactsNo}" id="${result[i].contactsNo}">
                                <label for="${result[i].contactsNo}">${result[i].contactsName}</label> <br>\n`
            }
            resultStr +=   `<input type="hidden" name="checkedUsersNoList" value=${checkedUsersNoList}>\n`;

            // 전체주소록목록 화면에 뿌리기
            $("main .modal-addMember__contactsList").html(resultStr); 
            
            // modal창 열기
            modalAddMember.addClass("show"); 
        },
        error:function(){
            console.log(`ajax 통신 실패: 현유저가 소유하고 있는 주소록전체목록 조회실패`);
        },
    })
})

// modal창의 닫기(X)버튼 클릭시, modal창 닫힘.
$("main .modal-addMember-bg .modal-addMember .closeBtn").click(function(){
    modalAddMember.removeClass("show");
})

// modal창 바깥 클릭시, modal창 닫힘.
$(window).on('click', function(event) {
    if ($(event.target).is(modalAddMember)) {
        modalAddMember.removeClass('show');
    }
});

// modal창에서 "추가"버튼 클릭시, DB에 반영하고, 해당 주소록을 메인컨텐츠 화면에 뿌려줌. modal창은 닫음. 
function insertContactsMember(){
    const contactsNo = $(".modal-addMember__contactsList :radio:checked").val();
    const checkedUsersNoList = $(".modal-addMember__contactsList :hidden").val();

    $.ajax({
        url:"contacts/insert.member",
        type:"post",
        data:{
            contactsNo:contactsNo,
            checkedUsersNoList:checkedUsersNoList,
        },
        success:function(result){
            if(result > 0) {
                alert("성공적으로 주소록에 구성원을 추가하였습니다.");

                let addedUsersCount = checkedUsersNoList.split(",").length; //ex)"18,24,27,2" -> ["18","24","27","2"] -> 4
                clickSidebarContactsNo(contactsNo, true, addedUsersCount); // 선택한 주소록을 사이드바에서 클릭하기
                modalAddMember.removeClass("show");
            }else {
                alert("선택한 주소록에 이미 해당(일부) 구성원이 있습니다.\n확인후 다시 시도해주세요.");
            }
        },
        error:function(){
            console.log(`ajax 통신 실패: 주소록에 구성원 추가를 실패하였습니다. `);
        },
    })
    
}

// 사이드바에서 주소록번호에 해당하는 메뉴 클릭하기
function clickSidebarContactsNo(contactsNo, isAdded, userCountToUpdate){
    // 변경된 주소록 메뉴 찾기
    // 개인주소록에서 먼저 찾고, 없으면 공유주소록에서 찾음
    let updatedEl = $(`.mid-cate__title input[name="contactsNo"][value="${contactsNo}"]`).parent(); // 개인주소록 내
    if(updatedEl.length == 0) {
        updatedEl = $(`.mid-cate__contents input[name="contactsNo"][value="${contactsNo}"]`).parent(); // 공유주소록 내
    }

    // 사이드바 주소록명 옆에 붙는 인원수 업데이트 - ex) "자바 1조 (3)" -> "자바 1조 (7)"
    const userCountEl = $(updatedEl).find("span.userCount");
    let prevUsersCount = userCountEl.text().replace(/[()]/g, ''); //ex) "(3)" -> "3"
    prevUsersCount = parseInt(prevUsersCount, 10); // 숫자로 형변환
    
    let finalUsersCount;
    if(isAdded) {
        finalUsersCount = prevUsersCount + userCountToUpdate;
    }else {
        finalUsersCount = prevUsersCount - userCountToUpdate;
    }

    userCountEl.text(`(${finalUsersCount})`); // 최종 인원수로 업데이트
    clickContactsElOnSidebar(contactsNo); // 사이드바에서 클릭하면, 이미 정의된 이벤트 덕분에 메인컨텐츠영역에도 주소록구성원목록을 뿌려주게됨.
}


// -------------- "주소록에서 제거" 버튼 --------------
// 유저를 주소록에서 제거할때 사용.

const mainBtnGroupArea = $(".section__search-bar .btn-group"); // 버튼을 넣을 공간
const deleteContactsUserBtn = `<button class="deleteBtn" onclick="deleteContactsMember();">주소록에서 제외</button>`; // 실제 버튼

// "주소록에서 제외"버튼 - 보여주기
function displayDeleteContactsUserBtn() {
    if($(mainBtnGroupArea).find(".deleteBtn").length == 0) { // 버튼이 없다면 -> 추가
        $(mainBtnGroupArea).append(deleteContactsUserBtn);
    }
}

// "주소록에서 제외"버튼 - 제거
function hideDeleteContactsUserBtn() {
    const deleteBtn = $(mainBtnGroupArea).find(".deleteBtn");
    if(deleteBtn.length != 0){ // 버튼이 있다면 -> 제거
        $(deleteBtn).remove();
    }
}

// "주소록에서 제외"버튼 클릭시 실행되는 기능
function deleteContactsMember(){
    const checkedUserElList = $(".section__list-content .userInfo div.checkbox :checkbox:checked"); // 체크박스로 선택된 유저 리스트

    if(checkedUserElList.length == 0) {
        alert("선택된 구성원이 없습니다.\n제외하고자하는 구성원을 선택후 다시 시도해주세요.");
        return;
    } 

    if(confirm("정말로 해당 구성원을 주소록에서 제외하시겠습니까?")) {
        const activeEl = $("aside .active");
        let contactsNo = $(activeEl).filter(".sm-cate").find(":hidden").val(); // 유저번호
        const checkedUsersObjList = []; // 최종적으로 전달할 객체 리스트
    
        for(let i=0; i<checkedUserElList.length; i++) {
            const userNo = $(checkedUserElList[i]).val(); // 유저번호
    
            if(activeEl.length == 1) { // 카테고리주소록인경우, 주소록번호가 각각의 유저마다 다르므로 개별로 할당해줌.
                contactsNo = $(checkedUserElList[i]).parent().parent().find(".contactsInfo :hidden").val();
                if(contactsNo === undefined){
                    contactsNo = $(activeEl).find(":hidden").val();
                }
            }
    
            checkedUsersObjList.push({
                contactsNo: contactsNo,
                userNo: userNo,
            });
        }
    
        $.ajax({
            url:"contacts/delete.member",
            type:"post",
            data:{
                checkedUsersObjList: JSON.stringify(checkedUsersObjList),
            },
            success:function(result){
                if(result > 0) {
                    alert("성공적으로 구성원을 해당 주소록에서 제외였습니다.");
                    clickSidebarContactsNo(contactsNo, false, checkedUsersObjList.length); // 선택한 주소록을 사이드바에서 클릭하기
                }else {
                    alert("주소록에서 구성원 제외를 실패하였습니다.");
                }
            },
            error:function(){
                console.log(`ajax 통신 실패: 주소록에서 구성원 제외를 실패하였습니다. `);
            },
        })
    }
}



/* -------------- modal창 - 공통 -------------- */

// "주소록에 추가" 관련 modal은 여기 공통부분에 있는 코드 사용 안함 (재사용 못하도록 코드를 짰었음... 배우고있습니다..)

// 모달창 보여주기
function showModal(modalBgEl) {
    $(modalBgEl).addClass("show");
}

// 모달창 숨기기
function hideModal(modalBgEl){
    $(modalBgEl).removeClass("show");
}

// 닫기(x)버튼 누르면 모달창 숨기기
function closeModalByBtn(closeBtn){
    const modalBg = $(closeBtn).parent().parent();
    hideModal(modalBg);
}

// 주소록메인화면으로 이동
function moveToContactsMain(){
    location.href = `${contextPath}/contacts`;
}

// 주소록메인화면으로 이동하되, categoryNo를 parameter로 넘겨줌. => 해당 카테고리를 사이드바에서 클릭 => 메인컨텐츠에 리스트뿌려줌.
function moveToCategoryPage(categoryNo){
    location.href = `${contextPath}/contacts?categoryNo=${categoryNo}`;
}

// 주소록메인화면으로 이동하되, contactsNo를 parameter로 넘겨줌. => 해당 주소록을 사이드바에서 클릭 => 메인컨텐츠에 리스트뿌려줌.
function moveToContactsPage(contactsNo){
    location.href = `${contextPath}/contacts?contactsNo=${contactsNo}`;
}

// 페이지 재로딩시, 넘겨받은 카테고리번호 혹은 주소록번호로 해당 요소 클릭하기.
if(currentCategoryNo != 0) { // 카테고리번호를 넘겨받은 경우
    clickCategoryElOnSidebar(currentCategoryNo);
} else if(currentContactsNo != 0) { // 주소록번호를 넘겨받은 경우
    clickContactsElOnSidebar(currentContactsNo);
}

// 사이드바에서 카테고리번호로 해당 요소 클릭하기
function clickCategoryElOnSidebar(categoryNo){
    $(`aside input[type="hidden"][name="categoryNo"][value="${categoryNo}"]`).parent().click();
}

// 사이드바에서 주소록번호로 해당 요소 클릭하기
async function clickContactsElOnSidebar(contactsNo){

    // 공유주소록인경우 카테고리를 한번 클릭하고 난 후에 주소록을 클릭해야함.
    const categoryNo = await selectCategoryNo(contactsNo, null);
    let milliSeconds = 0;
    if(categoryNo !=0) {
        milliSeconds = 400;
        clickCategoryElOnSidebar(categoryNo);
    }

    // 주소록 클릭하기
    setTimeout(function(){
        const el = $(`aside input[type="hidden"][name="contactsNo"][value="${contactsNo}"]`).parent();
        el[0].scrollIntoView({ behavior: 'smooth', block: 'center' }); // 요소가 있는곳까지 스크롤내림
        el.click();
    }, milliSeconds);
}

// "주소록번호" 혹은 "카레고리명"으로 "카테고리번호" 조회
function selectCategoryNo(contactsNo, categoryName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/categoryNo",
            type: "get",
            data: {
                contactsNo: contactsNo,
                categoryName: categoryName,
            },
            success: function(result) {
                resolve(result);
            },
            error: function() {
                reject(new Error('AJAX 통신실패: selectCategoryNo()'));
            }
        });
    });
}

// 값이 비어있는지 확인 (input의 값이 비었는지 확인할때 사용)
function isEmptyValue(value){
    if(value.length == 0) {
        alert("값이 비어있습니다.\n값을 입력한 후 다시 시도해주세요.");
        return true;
    }
    return false;
}

/* -------------- modal창 - 공유주소록 추가 -------------- */
const modalAddPublicContactBg = $(".modal-addPublicContact-bg");

// 사이드바에서 공유주소록 추가(+) 버튼 클릭시, modal창 열기.
function showModal_AddPublicContacts() {
    displayCategoryListOnModal();
    showModal(modalAddPublicContactBg);
    toggleCategoryDivs(); // 카테고리 선택에 따라 해당div를 보여줌.
}

// modal창 바깥 클릭시, modal창 닫힘.
$(window).on('click', function(event) {
    if ($(event.target).is(modalAddPublicContactBg)) {
        hideModal(modalAddPublicContactBg);
    }
});

// select option에 카테고리리스트를 넣어줌.
let isCategoryListAddedOnModal = false; // 이미 추가됐었는지 확인하기 위한 변수
async function displayCategoryListOnModal() {

    if(isCategoryListAddedOnModal == false) { // 이전에 이미 추가한적이 없는 경우에만.
        const list = await selectCategoryList(); // db 조회

        let str = "";
        for(let i=0; i<list.length; i++) {
            str += `<option value="${list[i].categoryNo}">${list[i].categoryName}</option>\n`;
        }
        $("#categorySelect").append(str); // 화면에 뿌려주기

        isCategoryListAddedOnModal = true;
    }
}

// DB에서 카테고리 리스트 불러옴   
function selectCategoryList() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/list.category",
            type: "get",
            data: {},
            success: function(result) {
                resolve(result);
            },
            error: function() {
                console.error('AJAX 통신실패: selectCategoryList()');
                reject(new Error('Failed to load category list'));
            }
        });
    });
}

// 카테고리 선택: 기존 카테고리 / 새로운 카테고리 radio 버튼 선택에 따라 해당하는 div를 보여줌.
function toggleCategoryDivs() {
    if ($('#existingCategory').is(':checked')) {
        $('.categorySelectDiv').show();
        $('.categoryNameInputDiv').hide();
    } else {
        $('.categorySelectDiv').hide();
        $('.categoryNameInputDiv').show();
    }
}

// 카테고리 선택에 이벤트 발생시, 다시 실행시킴.
$('input[name="isNewCategory"]').change(function() {
    toggleCategoryDivs();
});

// modal창에서 "추가" 버튼 클릭시 수행할 기능.
function addPublicContacts() {
    const contactsName = $("#newPublicContactsName").val(); 
    if(isEmptyValue(contactsName)) { // 주소록명 입력되어있는지 검증
        return;
    }

    // 기존카테고리/새로운카테고리 에 따라 다른 기능 수행함.
    if ($('#existingCategory').is(':checked')) { 
        addPublicContacts_UsingExistingCategoryNo(contactsName);
    } else {
        addPublicContacts_UsingNewCategoryNo(contactsName)
    }
}

// 공유주소록추가 - case 1) 기존카테고리를 사용한경우
function addPublicContacts_UsingExistingCategoryNo(contactsName){ 
    const existingCategoryNo = Number($(".categorySelectDiv").find("option:selected").val()); //선택한 "카테고리번호" 가져옴
    insertPublicContacts(existingCategoryNo, contactsName); // 주소록에 추가
}

// 공유주소록추가 - case 2) 새로운 카테고리를 사용한경우
async function addPublicContacts_UsingNewCategoryNo(contactsName){
    // 새로운 카테고리명
    const newCategoryName = $("#newCategoryName").val(); 
    if(isEmptyValue(newCategoryName)) { // 카테고리명이 입력되어있는지 검증
        return false;
    }

    // 카테고리 추가 & 검증
    const insertCategoryResult = await insertCategory(newCategoryName); 
    if(insertCategoryResult == -1) {
        alert("이미 동일한 카테고리명이 있습니다. 다른 이름으로 다시 시도해주세요.");
        return;
    } else if (insertCategoryResult <= 0) {
        alert("카테고리 추가에 실패하였습니다.");
        return;
    }

    // 카테고리 번호 찾고, 주소록에 추가
    const categoryNo = await selectCategoryNo(0, newCategoryName); // selectCategoryNo(contactsNo, categoryName)
    const insertPublicContactsResult = await insertPublicContacts(categoryNo, contactsName);
    if(insertPublicContactsResult <= 0){ // 주소록이 제대로 추가가 안된경우, 기존에 추가했던 새로운 카테고리도 삭제
        deleteCategory(categoryNo);
    }
}

// 주소록카테고리 추가 
function insertCategory(newCategoryName){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/insert.category",
            type: "post",
            data: {
                newCategoryName:newCategoryName,
            },
            success: function(result) {
                resolve(result);
            },
            error: function() {
                reject(new Error('AJAX 통신실패: insertCategory()'));
            }
        });
    });
}

// 주소록카테고리 삭제 
function deleteCategory(categoryNo){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/delete.category",
            type: "post",
            data: {
                categoryNo:categoryNo,
            },
            success: function(result) {
                resolve(result);
            },
            error: function() {
                reject('AJAX 통신실패: deleteCategory()');
            }
        });
    });
}

// 공유주소록 DB에 추가 => 새 페이지 로딩 후 해당 주소록 클릭
function insertPublicContacts(categoryNo, contactsName){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/insert.publicContacts",
            type: "post",
            data: {
                categoryNo: categoryNo,
                contactsName: contactsName,
            },
            success: function(result) {
                if (result > 0) {
                    alert("성공적으로 공유주소록을 추가하였습니다.");
                    
                    selectContactNo(contactsName).then(contactsNo => {
                        moveToContactsPage(contactsNo);
                    })
                } else if (result == -1) {
                    alert("이미 동일한 주소록명이 있습니다. 다른 이름으로 다시 시도해주세요.");
                } else {
                    alert("공유주소록 추가에 실패하였습니다.");
                }
                resolve(result);
            },
            error: function() {
               reject(`ajax 통신 실패 : insertPublicContacts()`);
            },
        });
    });
}

// "주소록이름"으로 "주소록 번호"를 DB에서 조회함.
function selectContactNo(contactsName) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/contactsNo",
            type: "get",
            data: {
                contactsName: contactsName,
            },
            success: function(result) {
                resolve(result);
            },
            error: function() {
                reject('ajax 통신 실패 : selectContactNo()');
            },
        });
    });
}

/* -------------- modal창 - 개인주소록 추가 -------------- */
const modalAddPrivateContactBg = $(".modal-addPrivateContact-bg");

// 사이드바에서 개인주소록 추가(+) 버튼 클릭시, modal창 열기.
function showModal_AddPrivateContacts() {
    showModal(modalAddPrivateContactBg);
    $("#newPrivateContactsName").focus(); // text input에 포커스시키기
}

// modal창 바깥 클릭시, modal창 닫힘.
$(window).on('click', function(event) {
    if ($(event.target).is(modalAddPrivateContactBg)) {
        hideModal(modalAddPrivateContactBg);
    }
});

// modal창에서 "추가" 버튼 클릭시, db에 추가함.
function insertPrivateContacts() {
    const contactsName = $("#newPrivateContactsName").val();
    
    // 값이 비어있으면 alert띄우기 아래 코드 실행안함.
    if(isEmptyValue(contactsName)) {
        return;
    }

    $.ajax({
        url: "contacts/insert.privateContacts",
        type: "post",
        data: {
            contactsName: contactsName,
        },
        success: function(result) {
            if (result > 0) {
                alert("성공적으로 개인주소록을 추가하였습니다.");

                // selectContactNo()가 완료된 후, moveToContactsPage()를 실행함. (contactsNo를 넘겨줌)
                // 이렇게 안하면, selectContactNo에서 contactsNO를 구하는 과정이 완료되기 전에 moveToContactsPage()를 실행해서 contactsNo에 undefined가 들어감.
                selectContactNo(contactsName).then(contactsNo => {
                    moveToContactsPage(contactsNo);
                })
            } else if (result == -1) {
                alert("이미 동일한 주소록명이 있습니다. 다른 이름으로 다시 시도해주세요.");
            } else {
                alert("개인주소록 추가에 실패하였습니다.");
            }
        },
        error: function() {
            console.log(`ajax 통신 실패 : insertPrivateContacts()`);
        },
    });
}

/* -------------- modal창 - 카테고리 수정 -------------- */
const modalEditCategoryBg = $(".modal-editCategory-bg");

// 사이드바 "공유주소록"부분에서 - 카테고리 edit 아이콘 클릭시
$("aside .public-contacts").on("click", ".mid-cate__title span.icon.edit", function(){
    $(modalEditCategoryBg).find(".categoryName").text(getActiveCategoryNameFromSidebar()); // modal창에 선택한 카테고리명 명시해주기.
    showModal(modalEditCategoryBg); // modal창 열기
})

// modal창 바깥 클릭시, modal창 닫힘.
$(window).on('click', function(event) {
    if ($(event.target).is(modalEditCategoryBg)) {
        hideModal(modalEditCategoryBg);
    }
});

// 사이드바에 클릭된 카테고리명 조회 
function getActiveCategoryNameFromSidebar(){
    return $("aside").find(".active span.title").text();
}

// 사이드바에 클릭된 카테고리번호 조회 
function getCategoryNoFromSidebar(){
    return $("aside").find(`.active input[name="categoryNo"]`).val();
}

// "카테고리명 변경" 버튼 클릭시
function confirmEditCategory() {
    const existingCategoryName = getActiveCategoryNameFromSidebar();
    const newCategoryName = prompt("새로운 카테고리명을 입력하세요.", existingCategoryName); // 기존 카테고리명을 prompt에 입력해둠

    // 검증로직
    if(newCategoryName == existingCategoryName) {
        alert("기존 카테고리명과 동일합니다.\n변경하고자하는 카테고리명을 입력하고 다시 시도해주세요.");
        return;
    }else if(newCategoryName.length == 0) {
        alert("값이 비어있습니다.\n카테고리명을 입력하고 다시 시도해주세요.");
        return;
    }else if(!isValidInputTextLength(newCategoryName)) {
        alert(`글자수를 초과하였습니다. 다시 시도하여주세요.`);
        return;
    }

    // 모든 검증을 거치고 통과하면 변경로직 실행
    updateCategoryName(getCategoryNoFromSidebar(), newCategoryName);
}

// Byte 길이 검증
function isValidInputTextLength(text) {
    if (getByteLength(text) > BYTE_LENGTH_LIMIT) { 
        return false; // 글자수 초과
    }
    return true; 
}

// Byte 길이 구하기
function getByteLength(str) {
    const encoder = new TextEncoder(); // Create a new TextEncoder instance
    const encoded = encoder.encode(str); // Encode the string as a Uint8Array (UTF-8)
    return encoded.length; // Return the length of the encoded byte array
}

// DB에서 카테고리명 변경
function updateCategoryName(categoryNo, newCategoryName){
    $.ajax({
        url: "contacts/update.categoryName",
        type: "post",
        data: {
            categoryNo: categoryNo,
            newCategoryName: newCategoryName,
        },
        success: function(result) {
            if(result > 0) {
                alert(`성공적으로 카테고리명을 변경하였습니다.`);
                moveToCategoryPage(categoryNo);
            }else if(result == -1) {
                alert(`중복되는 카테고리명이 있습니다. 다른 카테고리명으로 다시 시도해주세요.`);
            }else {
                alert(`카테고리명 변경에 실패하였습니다.`);
            }
        },
        error: function() {
            console.selectCategoryUsersList(`ajax 통신 실패 : updateCategoryName()`);
        },
    });
}

// "카테고리 삭제" 버튼 클릭시
async function confirmDeleteCategory() {
    if(confirm("정말로 해당 카테고리를 삭제하시겠습니까?\n\n카테고리 삭제시, 카테고리에 속한 주소록들도 같이 삭제됩니다.")){
        
        const existingCategoryName = getActiveCategoryNameFromSidebar();
        const checkCategoryName = prompt(`실수로 삭제하는 것을 방지하기 위한 확인작업니다.\n기존의 카테고리명을 동일하게 입력하여 주세요.\n\n기존카테고리명:\n${existingCategoryName}`);
        
        if(checkCategoryName != existingCategoryName) { // 실수방지 텍스트가 다른경우, 밖으로 돌려보냄.
            alert("입력하신 카테고리명이 기존의 카테고리명과 다릅니다.\n 다시 시도해주세요.");
            return;
        }

        const result = await deleteCategory(getCategoryNoFromSidebar()); // db에서 삭제
        if (result > 0) {
            alert("성공적으로 카테고리를 삭제하였습니다.");
            moveToContactsMain(); // 주소록 메인페이지로 이동!
        } else {
            alert("카테고리 삭제에 실패하였습니다.");
        }
    }
}

/* -------------- modal창 - 주소록 수정 -------------- */
const modalEditContactsBg = $(".modal-editContacts-bg");

// 사이드바 "공유주소록"에서  - 주소록 edit 아이콘 클릭시
$("aside").on("click", ".public-contacts .sm-cate span.icon.edit", function(){
    displayModal_EditContacts();
})

// 사이드바 "공유주소록"에서  - 주소록 edit 아이콘 클릭시
$("aside").on("click", ".private-contacts span.icon.edit", function(){
    displayModal_EditContacts();
})

// modal창 띄우기 - "주소록 수정"
function displayModal_EditContacts(){
    $(modalEditContactsBg).find(".contactsName").text(getActiveContactsNameFromSidebar()); // modal창에 선택한 주소록명 명시해주기.
    showModal(modalEditContactsBg); // modal창 열기
}

// modal창 바깥 클릭시, modal창 닫힘.
$(window).on('click', function(event) {
    if ($(event.target).is(modalEditContactsBg)) {
        hideModal(modalEditContactsBg);
    }
});

// 사이드바에 클릭된 카테고리명 조회 
function getActiveContactsNameFromSidebar(){
    return $("aside").find(".active span.contactsName").text();
}

// 사이드바에 클릭된 카테고리번호 조회 
function getContactsNoFromSidebar(){
    const contactsNo = $("aside").find(`.active input[name="contactsNo"]`).val();
    return contactsNo;
}

// "주소록명 변경" 버튼 클릭시
function confirmEditContacts() {
    const existingContactsName = getActiveContactsNameFromSidebar();
    const newContactsName = prompt("새로운 주소록명을 입력하세요.", existingContactsName);

    if(newContactsName == existingContactsName) {
        alert("기존 주소록명과 동일합니다.\n변경하고자하는 주소록명을 입력하고 다시 시도해주세요.");
        return;
    }else if(newContactsName.length == 0) {
        alert("값이 비어있습니다.\n주소록명을 입력하고 다시 시도해주세요.");
        return;
    }else if(!isValidInputTextLength(newContactsName)) {
        alert(`글자수를 초과하였습니다. 다시 시도하여주세요.`);
        return;
    }

    // 모든 검증을 거치고 통과하면 변경로직 실행
    updateContactsName(getContactsNoFromSidebar(), newContactsName); // 기존 주소록명을 prompt에 입력해둠
}

// DB에서 주소록명 변경
function updateContactsName(contactsNo, newContactsName){
    $.ajax({
        url: "contacts/update.contactsName",
        type: "post",
        data: {
            contactsNo: contactsNo,
            newContactsName: newContactsName,
        },
        success: function(result) {
            if(result > 0) {
                alert(`성공적으로 주소록명을 변경하였습니다.`);
                moveToContactsPage(contactsNo);
            }else if(result == -1) {
                alert(`중복되는 주소록명이 있습니다. 다른 주소록명으로 다시 시도해주세요.`);
            }else {
                alert(`주소록명 변경에 실패하였습니다.`);
            }
        },
        error: function() {
            console.selectCategoryUsersList(`ajax 통신 실패 : updateContactsName()`);
        },
    });
}

// "카테고리 삭제" 버튼 클릭시
async function confirmDeleteContacts() {
    if(confirm("정말로 해당 주소록을 삭제하시겠습니까?")){
        
        const existingContactsName = getActiveContactsNameFromSidebar();
        const checkContactsName = prompt(`실수로 삭제하는 것을 방지하기 위한 확인작업니다.\n기존의 주소록명을 동일하게 입력하여 주세요.\n\n기존주소록명:\n${existingContactsName}`);
        
        if(checkContactsName != existingContactsName) { // 실수방지 텍스트가 다른경우, 밖으로 돌려보냄.
            alert("입력하신 주소록명이 기존의 주소록명과 다릅니다.\n 다시 시도해주세요.");
            return;
        }

        const result = await deleteContacts(getContactsNoFromSidebar()); // db에서 삭제
        if (result > 0) {
            alert("성공적으로 주소록을 삭제하였습니다.");
            moveToContactsMain(); // 주소록 메인페이지로 이동!
        } else {
            alert("주소록 삭제에 실패하였습니다.");
        }
    }
}

// 주소록 삭제 
function deleteContacts(contactsNo){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "contacts/delete.contacts",
            type: "post",
            data: {
                contactsNo:contactsNo,
            },
            success: function(result) {
                resolve(result);
            },
            error: function() {
                reject('AJAX 통신실패: deleteContacts()');
            }
        });
    });
}