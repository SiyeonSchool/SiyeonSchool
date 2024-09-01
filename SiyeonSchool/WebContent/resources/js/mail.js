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
const $searchResultLi = $("main.mail-write #searchResult ul li.searchResult-data"); // 검색결과 li
const $recevierListContentsEl = $("main.mail-write .receiver .list-contents");

// 검색창 클릭시: 모든사용자 & 모든주소록 리스트 보여주기
$searchReceiverInput.on('focus', function() {
    $searchResultDiv.show(); // 검색결과 div 보여주기
});

// 검색창 바깥 클릭시: 검색결과 div 숨기기 (input이나 검색결과를 제외하고 클릭한 경우)
$(document).on('click', function(event) {
    // 클릭이 input이나 검색결과창을 벗어난 경우: 검색결과 div 숨기기
    if (!$(event.target).closest($searchReceiverInput).length && !$(event.target).closest($searchResultDiv).length) {
        $searchResultDiv.hide();
    }
});

// 검색창에 입력시: 해당 키워드를 포함한 리스트만 보여주기
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

// 검색결과를 키보드로 이동/선택
let rResultcurrentIndex = -1; // 키보드로 이동시 사용될 index
$searchReceiverInput.on('keydown', function(e) {
    if (e.key === 'ArrowDown') { // 아래 방향키: 아래로 이동
        e.preventDefault();
        if (rResultcurrentIndex < $searchResultLi.length - 1) {
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
        if (rResultcurrentIndex > -1) {
          selectItem($searchResultLi.eq(rResultcurrentIndex));
        }
    }
});

// 검색결과에서 키보드로 선택한 요소 하이라이트
function updateHighlight() {
    // 모든 하이라이트 없애기
    $searchResultLi.removeClass('highlight');
    
    // 선택된 줄만 하이라이트
    if (rResultcurrentIndex > -1) {
        const $currentItem = $searchResultLi.eq(rResultcurrentIndex);
        $currentItem.addClass('highlight');

        // 선택된 줄로 스크롤바 이동
        $currentItem[0].scrollIntoView({
            behavior: 'instant',
            block: 'nearest',
        });
    }
}

// 검색결과에 마우스 들어오면, 기존에 키보드기능으로 들어간 하이라이트 지우기
$searchResultLi.on('mouseenter', function() {
    $searchResultLi.removeClass('highlight');
    rResultcurrentIndex = -1;
});

// 검색결과 키보드로 선택시 : 주소록리스트 화면에 넣어주기.
function selectItem($item) {
    addToReceiverList($item[0]); // 클릭된 요소를 화면에 넣어주기
    $searchResultDiv.hide(); // 검색결과 div 숨기기
    $searchReceiverInput.val(""); // 검색창 input 비워주기
    $searchReceiverInput.blur(); // Remove focus from the input temporarily
    rResultcurrentIndex = -1;
};

// 검색결과 마우스로 클릭시 : 주소록리스트 화면에 넣어주기.
$searchResultDiv.on('click', '.searchResult-data', function(event) {
    event.stopPropagation(); // 이벤트 버블링 막기
    addToReceiverList(this); // 클릭된 요소를 화면에 넣어주기
    $searchResultDiv.hide(); // 검색결과 div 숨기기
    $searchReceiverInput.val(""); // 검색창 input 비워주기
});

// 클릭된 요소를 화면에 넣어주기
async function addToReceiverList(el){
    const isUser = ($(el).find('.isUser').val() === 'true');
    let newHtmlText = "";

    if(isUser) { // 유저를 선택한 경우
        const userNo = $(el).find('.pkNo').val();
        const userName = $(el).find('.name').text();
        const userId = $(el).find('.userId').text();
        newHtmlText = getNewHtmlTextForReceiverList(userNo, userName, userId); // 추가할 htmlText 만들기

    }else { // 주소록을 선택한 경우
        const contactsNo = $(el).find('.pkNo').val();
        const userNoList = await selectContactsMemberList(contactsNo); // 주소록구성원 목록 (DB로 부터 조회)
        $(userNoList).each(function(){ // 주소록구성원 각각을 돌면서...
            const userNo = this.receiverNo;
            const userName = this.receiverName;
            const userId = this.receiverId;
            newHtmlText += getNewHtmlTextForReceiverList(userNo, userName, userId); // 추가할 htmlText 만들기
        });
    }

    displaySelectedReceiver(newHtmlText); // 화면에 뿌려주기
}

// 수신인리스트에 추가할 htmlText 만들기
function getNewHtmlTextForReceiverList(userNo, userName, userId){
    // 수신 타입: r:수신, c:참조, s:비밀
    const rType = $("#receiverType input[type=radio]:checked").val(); // r, c, s
    const rTypeText = (rType === 'r') ? '수신' : ((rType === 'c') ? '참조' : '비밀'); // 수신, 참조, 비밀

    newHtmlText = `<li>
                    <div class="rCheckbox">
                        <input type="checkbox" name="userNo" value="${userNo}">
                    </div>
                    <div class="rUserName">
                        <span class="userName">${userName}</span>
                        <span class="userId">${userId}</span>
                    </div>
                    <div class="rType">
                        <input type="hidden" class="rType" name="rType" value="${rType}">
                        <span>${rTypeText}</span>
                    </div>
                    <div class="rDelete">
                        <span class="icon material-symbols-rounded">close</span>
                    </div>
                </li>`;
    return newHtmlText;
}

// 선택된 수신인을 화면에 뿌려주기.
function displaySelectedReceiver(newHtmlText){
    let htmlText = $recevierListContentsEl.html();
    htmlText += newHtmlText;
    $recevierListContentsEl.html(htmlText);
}

// 주소록구성원 리스트 조회
function selectContactsMemberList(contactsNo){
    return new Promise((resolve, reject) => {
        $.ajax({
            url:"mail.wrtieForm/list.contactsMember",
            type:"get",
            data:{
                contactsNo: contactsNo,
            },
            success: function(result){;
                resolve(result);
            },
            error:function(){
                reject(new Error('AJAX 통신실패: selectContactsMemberList()'));
            }
        })
    })

}

// 수신인 리스트에서 X아이콘 클릭시, 해당 요소 삭제.
$recevierListContentsEl.on('click', '.rDelete', function(){
    $(this).parent().remove();
})

// 수신인 리스트 헤더에서 X아이콘 클릭시, 리스트 한번에 비우기
$("main.mail-write .receiver .list-header .rDelete").on('click', '.icon', function(){
    $recevierListContentsEl.empty();
})

// 메일 보내기 버튼 클릭시, 수신인 리스트에 있는 데이터를 보내주기 위해서, 수신인리스트의 체크박스를 체크함.
function setReceiverCheckboxesChecked(){
    $("main.mail-write .receiver .list-contents li").each(function(){
        $(this).find("input[name=userNo]").attr("checked", true);
    })
}