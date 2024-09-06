console.log("메일 js 실행됨!");
console.log("현재 메일함: " + currentMailbox);
// console.log("contextPath: " + contextPath);

// ================================ 메인 상세조회 ================================ 

// 뒤로가기(목록으로) 버튼
$("main.mail-detail .btnToGoBack").click(() => {
    location.href = sessionStorage.getItem("previousPage");
})

// 위로가기 버튼
$(() => {
    var $mailDetail = $('.mail-detail');

    $mailDetail.scroll(function () {
        if ($(this).scrollTop() > 200) {
            $('#btnToGoUp').fadeIn();
        } else {
            $('#btnToGoUp').fadeOut();
        }
    });

    $("#btnToGoUp").click(function () {
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
const $searchResultLi = $("main.mail-write #searchResult ul li.searchResult-data"); // 검색결과 li
const $recevierListContentsEl = $("main.mail-write .receiver .list-contents");

let rResultcurrentIndex = -1; // 검색결과를 키보드로 이동시 사용될 index
let $visibleSearchResultLi = $searchResultLi; // 현재 보이는 검색결과리스트

// 수신인 카운트 관련 변수
let receiverTotalCount = 0; // 총합
let receiverRCount = 0; // 수신
let receiverCCount = 0; // 참조
let receiverSCount = 0; // 비밀

// 수신인 카운트 관련 요소
const $receiverTotalCountEl = $("main.mail-write .receiver .listSummary .total"); //  총합
const $receiverRCountEl = $("main.mail-write .receiver .listSummary .detailCount .r"); // 수신
const $receiverCCountEl = $("main.mail-write .receiver .listSummary .detailCount .c"); // 참조
const $receiverSCountEl = $("main.mail-write .receiver .listSummary .detailCount .s"); // 비밀

// 검색창 클릭시: 모든사용자 & 모든주소록 리스트 보여주기
$searchReceiverInput.on('focus', function () {
    $searchResultLi.each(function () { // 이전검색시 숨겨졌던 리스트 다시 보여주기
        $(this).show();
    })
    $searchResultDiv.show(); // 검색결과 div 보여주기
});

// 검색창 바깥 클릭시: 검색결과 div 숨기기 (input이나 검색결과를 제외하고 클릭한 경우)
$(document).on('click', function (event) {
    // 클릭이 input이나 검색결과창을 벗어난 경우: 검색결과 div 숨기기
    if (!$(event.target).closest($searchReceiverInput).length && !$(event.target).closest($searchResultDiv).length) {
        $searchResultDiv.hide();
    }
});

// 검색창에 입력시: 해당 키워드를 포함한 리스트만 보여주기
$searchReceiverInput.on('keyup', function (e) {
    if (e.key === 'ArrowDown' || e.key === 'ArrowUp' || e.key === 'Enter') return; // 방향키, 엔터키 무시

    $searchResultDiv.show(); // 검색결과 div 보여주기
    const keyword = $(this).val().toLowerCase();

    $visibleSearchResultLi = $(); // 현재보여지는 검색결과. 아래서 추가할 수 있도록 생성만함.

    // 각각의 데이터를 순회하면서 키워드를 포함한 데이터만 보여주기
    let matchCount = 0;
    $searchResultLi.each(function () {
        const contentsToFind = $(this).find(".name").text().toLowerCase();
        if (contentsToFind.includes(keyword)) {
            $(this).show();
            $visibleSearchResultLi = $visibleSearchResultLi.add(this);
            matchCount++;
        } else {
            $(this).hide();
        }
    });

    rResultcurrentIndex = 0; // 다음 검색결과를 위해서 인덱스 초기화함

    // 매칭되는게 없는 경우 div를 숨겨줌.
    if (matchCount === 0) {
        $searchResultDiv.hide();
    } else {
        updateHighlight(); // 검색결과가 하나라도 있으면 하이라이트함.
    }
});

// 검색결과를 키보드로 이동/선택
$searchReceiverInput.on('keydown', function (e) {
    if (e.key === 'ArrowDown') { // 아래 방향키: 아래로 이동
        e.preventDefault(); // 방향키나 엔터키가 눌렸을때 발생할 수 있는 기본동작들을 방지함. (form에서 submit이 된다던지, 다른 input으로 이동한다던지..)
        if (rResultcurrentIndex < $visibleSearchResultLi.length - 1) {
            rResultcurrentIndex++;
            updateHighlight();
        }
    } else if (e.key === 'ArrowUp') { // 위 방향키: 위로 이동
        e.preventDefault();
        if (rResultcurrentIndex > 0) {
            rResultcurrentIndex--;
            updateHighlight();
        }
    } else if (e.key === 'Enter') { // 엔터: 해당 아이템 선택
        e.preventDefault();
        if (rResultcurrentIndex >= 0 && rResultcurrentIndex < $visibleSearchResultLi.length) {
            selectItem($visibleSearchResultLi.eq(rResultcurrentIndex));
        }
    }
});

// 검색결과 키보드로 이동시 하이라이트
function updateHighlight() {
    $searchResultLi.removeClass('highlight');

    if (rResultcurrentIndex >= 0 && rResultcurrentIndex < $visibleSearchResultLi.length) {
        const $currentItem = $visibleSearchResultLi.eq(rResultcurrentIndex);
        $currentItem.addClass('highlight');

        // 선택된 줄로 스크롤바 이동
        $currentItem[0].scrollIntoView({
            behavior: 'instant',
            block: 'nearest',
        });
    }
}

// 검색결과에 마우스 들어오면, 기존에 키보드기능으로 들어간 하이라이트 지우기
$searchResultLi.on('mouseenter', function () {
    $searchResultLi.removeClass('highlight');
    rResultcurrentIndex = -1;
});

// 검색결과 키보드로 선택시 : 주소록리스트 화면에 넣어주기.
function selectItem($item) {
    addToReceiverList($item[0]); // 클릭된 요소를 화면에 넣어주기
    $searchResultDiv.hide(); // 검색결과 div 숨기기
    $searchReceiverInput.val(""); // 검색창 input 비워주기
    $searchReceiverInput.blur(); // 검색창 포커스를 잠깐 없애줌.
    rResultcurrentIndex = -1;
};

// 검색결과 마우스로 클릭시 : 주소록리스트 화면에 넣어주기.
$searchResultDiv.on('click', '.searchResult-data', function (event) {
    event.stopPropagation(); // 이벤트 버블링 막기
    addToReceiverList(this); // 클릭된 요소를 화면에 넣어주기
    $searchResultDiv.hide(); // 검색결과 div 숨기기
    $searchReceiverInput.val(""); // 검색창 input 비워주기
});

// 검색결과에서 클릭된 요소를 화면에 넣어주기
async function addToReceiverList(el) {
    let shouldSkip = false;
    let newHtmlText = "";

    // 모든사용자/모든학생/선생님 중 선택했는지 확인
    const pkNo = $(el).find('.pkNo').val();
    switch (pkNo) {
        case "allUsers":
            newHtmlText = await addToReceiverListByAllUsers();
            shouldSkip = true;
            break;
        case "allStudents":
            newHtmlText = await addToReceiverListByAllStudents();
            shouldSkip = true;
            break;
        case "teacher":
            const teacher = await selectTeacher(); // 선생님 (DB로 부터 조회)
            if (isUserNoDuplicated(teacher.pkNo)) { return; }; // 중복검사. 중복되면 아래 내용 수행안함.
            newHtmlText = await addToReceiverListByTeacher(teacher);
            shouldSkip = true;
            break;
    }

    if (!shouldSkip) { //모든사용자/모든학생/선생님이 아닌경우
        const isUser = ($(el).find('.isUser').val() === 'true');

        if (isUser) { // 유저를 선택한 경우
            const userNo = $(el).find('.pkNo').val();
            if (isUserNoDuplicated(userNo)) { return; }; // 중복검사. 중복되면 아래 내용 수행안함.
            newHtmlText = addToReceiverListByUser(userNo, el);

        } else { // 주소록을 선택한 경우
            newHtmlText = await addToReceiverListByContacts(el);
        }
    }

    displaySelectedReceiver(newHtmlText); // 선택된 수신인 화면에 뿌려주기

    displayCurrentReceiverCount(); // 수신인카운트 화면에 뿌려주기
    initializePreviousRType();
}

// 검색결과에서 유저 선택한 경우
function addToReceiverListByUser(userNo, el) {
    const userName = $(el).find('.name').text();
    const userId = $(el).find('.userId').text();
    const rType = getReceiverTypeFromJSP();

    addReceiverCount(rType); // 수신인카운트 증가
    return getNewHtmlTextForReceiverList(userNo, userName, userId, rType); // 추가할 htmlText 만들어서 반환
}

// 검색결과에서 선생님 선택한 경우
async function addToReceiverListByTeacher(teacher) {
    const userNo = teacher.pkNo;
    const userName = teacher.name;
    const userId = `(${teacher.userId})`;
    const rType = getReceiverTypeFromJSP();

    addReceiverCount(rType); // 수신인카운트 증가
    return getNewHtmlTextForReceiverList(userNo, userName, userId, rType); // 추가할 htmlText 만들어서 반환
}

// 검색결과에서 주소록 선택한 경우
async function addToReceiverListByContacts(el) {
    const contactsNo = $(el).find('.pkNo').val();
    const userList = await selectContactsMemberList(contactsNo); // 주소록구성원 목록 (DB로 부터 조회)
    return addToReceiverListAtOnce(userList);
}

// 검색결과에서 모든사용자 선택한 경우
async function addToReceiverListByAllUsers() {
    const userList = await selectUsersList(); // 모든사용자 목록 (DB로 부터 조회)
    return addToReceiverListAtOnce(userList);
}

// 검색결과에서 모든학생 선택한 경우
async function addToReceiverListByAllStudents() {
    const userList = await selectStudentList(); // 모든학생 목록 (DB로 부터 조회)
    return addToReceiverListAtOnce(userList);
}

// 검색결과에서 여러명이 들어있는 옵션을 선택한경우
function addToReceiverListAtOnce(userList) {
    let newHtmlText = "";

    for (const user of userList) {
        const userNo = user.receiverNo;
        if (isUserNoDuplicated(userNo)) { continue; }; // 중복검사. 중복되면 아래 내용 수행안하고 다음 iteration으로 넘어감.

        const userName = user.receiverName;
        const userId = `(${user.receiverId})`;
        const rType = getReceiverTypeFromJSP();

        addReceiverCount(rType); // 수신인카운트 증가

        newHtmlText += getNewHtmlTextForReceiverList(userNo, userName, userId, rType); // 추가할 htmlText 만들기
    };

    return newHtmlText;
}

// 화면에서 수신타입 받아오기 
function getReceiverTypeFromJSP() {
    return $("#receiverType input[type=radio]:checked").val(); // r:수신, c:참조, s:비밀
}

// 수신인리스트에 추가할 htmlText 만들기
function getNewHtmlTextForReceiverList(userNo, userName, userId, rType) {

    let newHtmlText = `<li>
                            <div class="rCheckbox">
                                <input type="checkbox" name="userNo" value="${userNo}">
                            </div>
                            <div class="rUserName">
                                <span class="userName">${userName}</span>
                                <span class="userId">${userId}</span>
                            </div>
                            <div class="rType">
                                <select name="rType">`;

    if (rType === 'r') {
        newHtmlText += `<option value="r" selected>수신</option>`;
    } else {
        newHtmlText += `<option value="r">수신</option>`;
    };

    if (rType === 'c') {
        newHtmlText += `<option value="c" selected>참조</option>`;
    } else {
        newHtmlText += `<option value="c">참조</option>`;
    };

    if (rType === 's') {
        newHtmlText += `<option value="s" selected>비밀</option>`;
    } else {
        newHtmlText += `<option value="s">비밀</option>`;
    };

    newHtmlText += `</select>
                    </div>
                    <div class="rDelete">
                        <span class="icon material-symbols-rounded">close</span>
                    </div>
                </li>`;
    return newHtmlText;
}

// 주소록구성원 리스트 조회
function selectContactsMemberList(contactsNo) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "mail.wrtieForm/list.contactsMember",
            type: "get",
            data: {
                contactsNo: contactsNo,
            },
            success: function (result) {
                resolve(result);
            },
            error: function () {
                reject(new Error('AJAX 통신실패: selectContactsMemberList()'));
            }
        })
    })
}

// 모든사용자 리스트 조회
function selectUsersList() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "mail.wrtieForm/list.allUsers",
            type: "get",
            data: {},
            success: function (result) {
                resolve(result);
            },
            error: function () {
                reject(new Error('AJAX 통신실패: selectUsersList()'));
            }
        })
    })
}

// 모든학생 리스트 조회
function selectStudentList() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "mail.wrtieForm/list.allStudents",
            type: "get",
            data: {},
            success: function (result) {
                resolve(result);
            },
            error: function () {
                reject(new Error('AJAX 통신실패: selectStudentList()'));
            }
        })
    })
}

// 선생님 조회
function selectTeacher() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "mail.wrtieForm/teacher",
            type: "get",
            data: {},
            success: function (result) {
                resolve(result);
            },
            error: function () {
                reject(new Error('AJAX 통신실패: selectTeacher()'));
            }
        })
    })
}

// 수신인리스트에 추가전 중복여부체크
function isUserNoDuplicated(newUserNo) {
    let isDuplicated = false;

    $("main.mail-write .receiver .list-contents li").each(function () {
        const existingUserNo = $(this).find(".rCheckbox input").val();

        //console.log("existingUserNo:" + existingUserNo + ", type:" + typeof(existingUserNo) + " / newUserNo:" + newUserNo + ", type:" + typeof(newUserNo));

        if (existingUserNo == newUserNo) { // 중복되는 경우 (일부러 ===가 아닌 ==을 사용함. type이 달라도 글자만 맞으면 중복으로 판단하도록 함.)
            isDuplicated = true;
            return; // each문 빠져나옴
        }
    })

    return isDuplicated; // true: 중복됨, false: 중복안됨.
}

// 선택된 수신인을 화면에 뿌려주기.
function displaySelectedReceiver(newHtmlText) {
    const currentSelections = captureCurrentSelections();  // 수신구분 현재 선택된것 보관

    let htmlText = $recevierListContentsEl.html();
    htmlText += newHtmlText;
    $recevierListContentsEl.html(htmlText);

    reapplySelections(currentSelections);  // 수신구분 보관해뒀던 것으로 반영
}

// 수신구분 현재 선택된것 반환
function captureCurrentSelections() {
    const selections = {};
    $('main.mail-write .receiver .list-contents li').each(function (index, li) {
        const $select = $(li).find('select[name="rType"]');
        const userNo = $(li).find('input[name="userNo"]').val();
        selections[userNo] = $select.val(); // {userNo : rType, ...} 형식으로 저장
    });
    return selections;
}

// 수신구분 보관해뒀던 것으로 반영
function reapplySelections(selections) {
    $('main.mail-write .receiver .list-contents li').each(function (index, li) {
        const $select = $(li).find('select[name="rType"]');
        const userNo = $(li).find('input[name="userNo"]').val();
        if (selections[userNo]) {
            $select.val(selections[userNo]); // 해당 userNo에 해당하는 값이 있으면 반영
        }
    });
}

// 수신인 리스트에서 X아이콘 클릭시, 해당 요소 삭제.
$recevierListContentsEl.on('click', '.rDelete', function () {
    const rType = $(this).parent().find("select[name=rType]").val(); // 수신인 타입 받아오기 (r:수신, c:참조, s:비밀)
    console.log("rType:", rType);
    subtractReceiverCount(rType); // 수신인카운트 감소시키기
    displayCurrentReceiverCount(); // 수신인카운트 화면에 반영하기

    $(this).parent().remove(); // 요소 화면에서 제거
})

// 수신인 리스트 헤더에서 X아이콘 클릭시, 리스트 한번에 비우기
$("main.mail-write .receiver .list-header .rDelete").on('click', '.icon', function () {

    if (!confirm("받는사람 목록을 초기화 하시겠습니까?")) {
        return;
    }

    // 수신인 카운트 관련 변수 초기화
    receiverTotalCount = 0; // 총합
    receiverRCount = 0; // 수신
    receiverCCount = 0; // 참조
    receiverSCount = 0; // 비밀
    displayCurrentReceiverCount(); // 수신인카운트 화면에 반영하기

    $recevierListContentsEl.empty(); // 화면에서 수신인 전체 리스트 요소 제거
})


// 수신구분 select가 바뀔경우, 카운트 반영 & 화면에 반영
$recevierListContentsEl.on('change', 'select[name="rType"]', function () {
    const $select = $(this);
    const oldRType = $select.data('previous'); // jQuery 내부메모리에 저장해뒀던 이전 값을 가져옴
    const newRType = $select.val(); // 새로운 현재 값

    if (oldRType) { // 이전값이 있는 경우 값을 감소시킴
        subtractReceiverCount(oldRType);
    }
    addReceiverCount(newRType); // 새로운값은 증가

    displayCurrentReceiverCount(); // 화면에 반영

    $select.data('previous', newRType); // 새로운 값을 다음에 쓰기 위해 저장해둠.
});


// 수신구분의 현재 값을 jQuery 내부메모리에 보관함 (previous라는 key로 저장함.)
function initializePreviousRType() {
    $('select[name="rType"]').each(function () {
        const $select = $(this);
        $select.data('previous', $select.val());
    });
}

// 수신인 타입에 따라 수신인카운트 증가
function addReceiverCount(rType) {
    switch (rType) {
        case "r": receiverRCount++; break; // 수신
        case "c": receiverCCount++; break; // 참조
        case "s": receiverSCount++; break; // 비밀
    }
    receiverTotalCount++; // 총합
}

// 수신인 타입에 따라 수신인카운트 감소
function subtractReceiverCount(rType) {
    switch (rType) {
        case "r": receiverRCount--; break; // 수신
        case "c": receiverCCount--; break; // 참조
        case "s": receiverSCount--; break; // 비밀
    }
    receiverTotalCount--; // 총합
}

// 수신인카운트 화면에 뿌려주기
function displayCurrentReceiverCount() {
    $receiverTotalCountEl.text(receiverTotalCount);
    $receiverRCountEl.text(receiverRCount);
    $receiverCCountEl.text(receiverCCount);
    $receiverSCountEl.text(receiverSCount);
}


// 메일쓰기페이지 - 위로가기 버튼
$(() => {
    var $mailWrite = $('.mail-write');

    $mailWrite.scroll(function () {
        if ($(this).scrollTop() > 50) {
            $('#btnToGoUp').fadeIn();
        } else {
            $('#btnToGoUp').fadeOut();
        }
    });

    $("#btnToGoUp").click(function () {
        $mailWrite.animate({
            scrollTop: 0
        }, 400);
        return false;
    });
});


// ------------- 메일 보내기 관련 -------------

// 메일제목 비었는지 검증
function validateMailtitle() {
    const titleEl = $("main.mail-write #title");
    if (titleEl.val() === "") {
        $(titleEl).focus();
        return false;
    }
    return true;
}

// 메일수신인 있는지 검증
function validateMailReceiver() {
    const $receiverList = $("main.mail-write .receiver .list-contents").find("li");
    if ($receiverList.length <= 0 && currentMailbox !== "wm") { // 수신인이 없거나, 내게쓰기가 아닌경우
        $("main.mail-write #searchReceiver").focus();
        return false;
    };
    return true;
}

// 메일 보내기 버튼 클릭시, 수신인 리스트에 있는 데이터를 보내주기 위해서, 수신인리스트의 (보이지않는)체크박스를 체크함.
function setReceiverCheckboxesChecked() {
    if (currentMailbox === "wm") { // 내게쓰기인 경우, 아래 수행안함.
        return;
    }

    $("main.mail-write .receiver .list-contents li").each(function () {
        $(this).find("input[name=userNo]").attr("checked", true);
    })
}

// ------------- 메일쓰기 취소 -------------

// 메일취소를 진짜 할건지 확인하는 기능
function cancelWritingMail() {

    if (!confirm("메일쓰기를 취소하시겠습니까?\n취소 진행시, 해당 내용은 저장되지 않습니다.")) {
        return;
    } else {
        location.href = `${contextPath}/mail?mb=i&cpage=1`;
    }
}

// ------------- 메일 임시저장 -------------

// isSent를 S(보내기)->T(임시저장)로 변경
function changeIsSentToT() {
    const $isSentEl = $("main.mail-write #isSent");
    $isSentEl.val("T");
}

function onClickDeleteMail(mailNo) {
    if(currentMailbox === "b") { // 휴지통인경우
        if(confirm("정말로 메일을 삭제하시겠습니까?")) {
            alert("메일 진짜 삭제 기능 ~ 은 나중에 구현할게요");
        }
    }else { // 휴지통이 아닌경우
        alert("메일을 휴지통으로 옮깁니다. 아직 구현안되서 빈페이지로 이동함.");
        location.href = `${contextPath}/mail.deleteToBin?mb=${currentMailbox}&m=${mailNo}`;
    }
}


// ================================ 메일답장 ================================ 
// 메일답장시, 기존메일 보낸사람을 수신인리스트에 추가
function addSenderToRList(userNo, userName, userId){
    const newHtmlText = getNewHtmlTextForReceiverList(userNo, userName, userId, 'r'); // 추가할 htmlText 만들어서 반환
    displayReceiver(newHtmlText); // 선택된 수신인 화면에 뿌려주기
    
    addReceiverCount('r'); //수신인카운트 증가
    displayCurrentReceiverCount(); // 수신인카운트 화면에 뿌려주기
}

// 메일 전체답장or전달시 기존메일 수신인을 수신인리스트에 추가
async function addOriginalReceiversToRList(mailNo){
    const userList = await selectOriginalReceiverList(mailNo); // 기존 수신인리스트 DB조회

    let newHtmlText = "";
    for (const user of userList) {
        const userNo = user.receiverNo;
        if (isUserNoDuplicated(userNo)) { continue; }; // 중복검사. 중복되면 아래 내용 수행안하고 다음 iteration으로 넘어감.

        const userName = user.receiverName;
        const userId = `(${user.receiverId})`;
        const rType = user.receiverType.toLowerCase();
        console.log("addOriginalReceiversToRList - rType: " + rType);

        addReceiverCount(rType); // 수신인카운트 증가

        newHtmlText += getNewHtmlTextForReceiverList(userNo, userName, userId, rType); // 추가할 htmlText 만들기
    };

    displayReceiver(newHtmlText); // 선택된 수신인 화면에 뿌려주기
    displayCurrentReceiverCount(); // 수신인카운트 화면에 뿌려주기
}

// 기존 수신인리스트에 새로운 수신인리스트 추가하여 화면에 반영
function displayReceiver(newHtmlText){
    let htmlText = $recevierListContentsEl.html();
    htmlText += newHtmlText;
    $recevierListContentsEl.html(htmlText);
}

// 기존 수신인리스트 DB조회
function selectOriginalReceiverList(mailNo){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "mail.wrtieForm/list.originalReceiver",
            type: "get",
            data: { mailNo: mailNo },
            success: function (result) {
                resolve(result);
            },
            error: function () {
                reject(new Error('AJAX 통신실패: selectOriginalReceiverList()'));
            }
        })
    })
}
