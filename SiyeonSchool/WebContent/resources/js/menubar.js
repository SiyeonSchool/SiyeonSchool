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


// =============================== 메일 알림 관련 ====================================

setInterval(mailNotification, 1000); // (1000ms = 1s)

async function mailNotification() {
    const mList = await selectNewMailList();
    console.log(mList);

    const $notificationDiv = $(".mailNotification.bottom-right");
    let html = sessionStorage.getItem("mailNotificationHtml") || $notificationDiv.html(); // Load from sessionStorage
    
    if(mList.length > 0) {
        for(let i=0; i<mList.length; i++) {
            html += createHtmlForMailNotification(mList[i]);
            removeNewMailHtmlInTenSec(mList[i].mailNo);
        }
        sessionStorage.setItem("mailNotificationHtml", html); // Save to sessionStorage
    }
    $notificationDiv.removeClass("hidden");
    $notificationDiv.html(html);
}

// 새로운메일 목록조회 (최근 몇초내 받은 메일 DB에서 조회- mail-mapper.xml 참고)
function selectNewMailList() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "mail.list.new",
            type: "get",
            data: {},
            success: function (result) {
                resolve(result);
            },
            error: function () {
                reject(new Error('AJAX 통신실패: selectNewMailList()'));
            }
        })
    })
}

function createHtmlForMailNotification(mail){
    const htmlText = `<div class="mail" onclick="moveToMailDetail('${mail.mailNo}')">
                            <input type="hidden" name="mailNo" value="${mail.mailNo}">
                            <div class="leftDiv">
                                <img src="${mail.profilePath}" class="profile-img">
                            </div>
                            <div class="rightDiv">
                                <h4 class="mailTitle">${mail.mailTitle}</h4>
                                <div class="subText">
                                    <span class="userName">${mail.userName}</span>
                                    <span class="userId">(${mail.userId})</span>
                                    <span>님이 메일을 보내셨습니다.</span>
                                </div>
                            </div>
                        </div>`;
    return htmlText;
}

function moveToMailDetail(mailNo){
    location.href = `${contextPath}/mail.detail?mb=i&m=${mailNo}`;
}

function removeNewMailHtmlInTenSec(mailNo) {
    setTimeout(function() {
        const $notificationDiv = $(".mailNotification.bottom-right");
        $notificationDiv.find(`input[name=mailNo][value=${mailNo}]`).parent().remove(); // Remove notification
        
        // Update sessionStorage after the notification is removed
        const updatedHtml = $notificationDiv.html();
        sessionStorage.setItem("mailNotificationHtml", updatedHtml);
    }, 5000); // 10초 뒤에
}