// JSP에서 가져온 로그인유저정보
const loginUser = JSON.parse(loginUserJson); // json string -> object 타입으로 형변환함.

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

            let classValue;
            if(userList[i].star === "Y"){
                classValue = "material-icons-round icon star fill"; // 노랑색 색칠된 별
            }else {
                classValue = "material-symbols-rounded icon star"; // 회색 테두리만 있는 별
            }
    
            str += `<!-- 한 줄의 사용자 데이터 -->
                    <li class="userInfo">
                        <div class="checkbox">
                            <input type="checkbox" name="userNo" value="${userList[i].userNo}">
                        </div>
                        <div class="star">
                            <span class="${classValue}">star</span>
                        </div>`;

            // 카테고리주소록 유저리스트일경우
            if((userList[i]).contactsNo != 0) {
                str += `<div class="contactsInfo">
                                <input type="hidden" name="contactsNo" value="${userList[i].contactsNo}">
                                ${userList[i].contactsName}
                        </div>`;
            }

            str +=      `<div class="userName">
                            <span class="material-symbols-rounded icon profile-pic">account_circle</span>
                            ${userList[i].userName}
                        </div>
                        <div class="userId">${userList[i].userId}</div>
                        <div class="role">${userList[i].role}</div>
                        <div class="birthday">${userList[i].birthday}</div>
                        <div class="phone">${userList[i].phone}</div>
                    </li>\n`;
        }
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
    if ($(event.target).is('span.star')) {
        return;
    }else if($(event.target).is('span.profile-pic')) {
        return;
    }else if($(event.target).is(':checkbox')) {
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

/* -------------- modal창 - "주소록에추가"-------------- */

// modal창을 감싸고있는 배경 element. (전체화면)
const modalAddMember = $("main .modal-addMember-bg");

// "주소록에추가" 버튼 클릭시, modal창 보여줌.
$("main .section__serach-bar .btn-group .addBtn").click(function(){

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
            resultStr +=   `<hr>
                            <input type="hidden" name="checkedUsersNoList" value=${checkedUsersNoList}>
                            <input type="submit" value="추가" onclick="insertContactsMember();">\n`

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
            }else if(result == -1){
                alert("선택한 주소록에 이미 해당 구성원이 있습니다. 확인후 다시 시도해주세요.");
            }else {
                alert("주소록에 구성원 추가를 실패하였습니다.");
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

    updatedEl.click(); // 사이드바에서 클릭하면, 이미 정의된 이벤트 덕분에 메인컨텐츠영역에도 주소록구성원목록을 뿌려주게됨.
}


// -------------- "주소록에서 제거" 버튼 --------------
// 유저를 주소록에서 제거할때 사용.

const mainBtnGroupArea = $(".section__serach-bar .btn-group"); // 버튼을 넣을 공간
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

    if(confirm("정말로 해당 구성원을 주소록에서 제외하시겠습니까?")) {
        const activeEl = $("aside .active");
        let contactsNo = $(activeEl).filter(".sm-cate").find(":hidden").val(); // 유저번호
        const checkedUserElList = $(".section__list-content .userInfo div.checkbox :checkbox:checked"); // 체크박스로 선택된 유저 리스트
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

function showModal(modalBgEl) {
    $(modalBgEl).addClass("show");
}

function hideModal(modalBgEl){
    $(modalBgEl).removeClass("show");
}

function closeModal(closeBtn){
    const modalBg = $(closeBtn).parent().parent();
    hideModal(modalBg);
}


/* -------------- modal창 - 개인주소록 추가 -------------- */
const modalAddPrivateContactBg = $(".modal-addPrivateContact-bg");

// 사이드바에서 개인주소록 추가(+) 버튼 클릭시, modal창 열기.
function showModal_AddPrivateContacts() {
    showModal(modalAddPrivateContactBg);
}

// modal창 바깥 클릭시, modal창 닫힘.
$(window).on('click', function(event) {
    if ($(event.target).is(modalAddPrivateContactBg)) {
        hideModal(modalAddPrivateContactBg);
    }
});

// modal창에서 "추가" 버튼 클릭시
function insertPrivateContacts() {
    const contactsName = $("#newPrivateContactsName").val();

    $.ajax({
        url:"contacts/insert.privateContacts",
        type:"post",
        data:{
            contactsName:contactsName,
        },
        success:function(result){
            if(result > 0) {
                alert("성공적으로 개인주소록을 추가하였습니다.");
                
                // ########### 작업중 ###########
                // 후속처리 해줘야함.
                // 1) modal 창 닫기
                // 2) 새로생긴 주소록 클릭하기

            }else {
                alert("개인주소록 추가에 실패하였습니다.\n중복되는 주소록이름이 있는지 확인 후 다시 시도해주세요.");
                // ########### 작업중 ###########
                // 실제 중복되는 이름 입력시 실패가 뜨는지 테스트하기!
            }
        },
        error:function(){
            console.log(`ajax 통신 실패`);
        },
    })
}