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

setInterval(mailNotification, 2000); // 2초마다 새로운 메일을 확인

// sessionStorage에 활성 알림을 전체 메일 데이터와 타임스탬프와 함께 저장
const activeNotifications = new Map(
    JSON.parse(sessionStorage.getItem("activeNotifications") || "[]")
);

async function mailNotification() {
    const mList = await selectNewMailList();

    const $notificationDiv = $(".mailNotification.bottom-right");

    // 각 새로운 메일 알림에 대해
    for (let i = 0; i < mList.length; i++) {
        const mail = mList[i];
        const mailNo = mail.mailNo;

        // 알림이 이미 활성화되어 있는지 확인
        if (!activeNotifications.has(mailNo)) {
            // 전체 메일 데이터와 타임스탬프와 함께 활성 알림에 추가
            const notificationData = {
                mail: mail,
                timestamp: Date.now() // 나중에 참조할 수 있도록 현재 시간을 저장
            };
            activeNotifications.set(mailNo, notificationData);

            // 전체 메일 데이터와 함께 알림 생성 및 추가
            const notificationHtml = createHtmlForMailNotification(mail);
            $notificationDiv.append(notificationHtml);
            $notificationDiv.removeClass("hidden");
            
            // 각 알림의 개별 제거 예약
            scheduleNotificationRemoval(mailNo, 10000); // 10초 후 제거
        }
    }

    // activeNotifications를 sessionStorage에 저장
    saveActiveNotificationsToSessionStorage();
}

// 특정 지연 시간 후 개별 알림을 제거하는 함수
function scheduleNotificationRemoval(mailNo, delay) {
    const notification = activeNotifications.get(mailNo);
    if (!notification) return;

    const timeElapsed = Date.now() - notification.timestamp;
    const timeRemaining = delay - timeElapsed;

    if (timeRemaining > 0) {
        setTimeout(function () {
            const $notificationDiv = $(".mailNotification.bottom-right");
            // 특정 알림 제거
            $notificationDiv.find(`input[name=mailNo][value=${mailNo}]`).parent().remove();

            // 활성 알림에서 제거
            activeNotifications.delete(mailNo);

            // activeNotifications를 sessionStorage에 저장
            saveActiveNotificationsToSessionStorage();

            // 더 이상 알림이 없으면 알림 div 숨기기
            if ($notificationDiv.children().length === 0) {
                $notificationDiv.addClass("hidden");
            }
        }, timeRemaining);
    }
}

// 활성 알림을 sessionStorage에 저장
function saveActiveNotificationsToSessionStorage() {
    const activeNotificationsArray = Array.from(activeNotifications.entries());
    sessionStorage.setItem("activeNotifications", JSON.stringify(activeNotificationsArray));
}

// 페이지 로드 시 sessionStorage에서 알림 복원
$(document).ready(function () {
    const $notificationDiv = $(".mailNotification.bottom-right");

    // 전체 메일 데이터와 타임스탬프와 함께 sessionStorage에서 활성 알림 복원
    const storedActiveNotifications = JSON.parse(sessionStorage.getItem("activeNotifications") || "[]");
    storedActiveNotifications.forEach(([mailNo, notification]) => {
        const { mail, timestamp } = notification;

        // 저장된 알림을 전체 메일 데이터와 함께 DOM에 추가
        const storedHtml = createHtmlForMailNotification(mail);
        $notificationDiv.append(storedHtml);

        // 각 복원된 알림의 남은 시간 계산
        const timeElapsed = Date.now() - timestamp;
        const remainingTime = 10000 - timeElapsed; // 총 10초 수명

        if (remainingTime > 0) {
            // 각 복원된 알림의 개별 제거 예약
            scheduleNotificationRemoval(mailNo, remainingTime);
        } else {
            // 시간이 다 되었으면 즉시 알림 제거
            $notificationDiv.find(`input[name=mailNo][value=${mailNo}]`).parent().remove();
            activeNotifications.delete(mailNo);
        }
    });

    // 알림이 있으면 알림 div 표시
    if ($notificationDiv.children().length > 0) {
        $notificationDiv.removeClass("hidden");
    }

    // 업데이트된 알림 상태 저장
    saveActiveNotificationsToSessionStorage();
});

// 새로운 메일 목록 가져오기
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
        });
    });
}

// 메일 알림에 대한 HTML 생성
function createHtmlForMailNotification(mail) {
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

// 메일 상세 페이지로 이동하고 클릭된 알림 제거
function moveToMailDetail(mailNo) {
    // DOM에서 특정 알림 제거
    const $notificationDiv = $(".mailNotification.bottom-right");
    $notificationDiv.find(`input[name=mailNo][value=${mailNo}]`).parent().remove();

    // 활성 알림에서 제거
    activeNotifications.delete(mailNo);

    // sessionStorage에 업데이트된 알림 상태 저장
    saveActiveNotificationsToSessionStorage();

    // 더 이상 알림이 없으면 알림 div 숨기기
    if ($notificationDiv.children().length === 0) {
        $notificationDiv.addClass("hidden");
    }

    // 메일 상세 페이지로 이동
    location.href = `${contextPath}/mail.detail?mb=i&m=${mailNo}`;
}



// =============================== 메일 알림 관련 (브라우저에서 알람을 처리하는 코드)====================================
/*
// ############ 아래도 가능하긴 하나 브라우저마다 세팅을 해줘야하는 번거로움이 있음. 그래서 코드는 남겨놓지만 사용은 안함 ##############

// 브라우저에서 알람을 처리하는 코드
async function mailNotification() {
    const mList = await selectNewMailList();

    for (let i = 0; i < mList.length; i++) {
        notify(mList[i]);
    }
}

function notify(mail) {
    if (Notification.permission !== 'granted') {
        alert('notification is disabled');
    // } else if(mail.userNo !== loginUser.userNo ) {
    } else {
        var notification = new Notification(`${mail.mailTitle}`, {
            icon: `${mail.profilePath}`,
            body: `${mail.userName} (${mail.userId}) 님이 메일을 보내셨습니다.`,
        });
        
        notification.onclick = function () {
            window.open(`${contextPath}/mail.detail?mb=i&m=${mail.mailNo}`);
        };
    }
}

// Fetch new mail list
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
        });
    });
}

// ##################### 아래는 index.jsp에 추가할것 (알람을 허용할지 묻는 코드)#################
<script type="text/javascript">
    window.onload = function () {
        if (window.Notification) {
            Notification.requestPermission();
        }
    }
</script>

*/