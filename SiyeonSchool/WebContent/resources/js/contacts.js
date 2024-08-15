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

// (ajax) 사이드바 주소록 카테고리 클릭시, 해당 카테고리에 속한 주소록 데이터 가져오기
// ex) "세미 프로젝트" 클릭시, 데이터 조회하여 하위의 "1조" ~ "5조" 리스트 화면에 뿌려주기. (조이름 + 인원수)
const categoryNoList = []; // ajax가 실행되었었는지 기록하기 위한 배열
$(".public-contacts .mid-cate__title.dynamic").click(function(){ // 동적으로 생성된 카테고리만 적용됨(.dynamic). 즉, "모든사용자"에는 적용안됨(모든사용자는 주소록메인페이지 로딩시 이미 조회됨)
    const categoryNo = $(this).find("input").val();
    const contentsArea = $(this).next(); // ul.mid-cate__contents
    // console.log(categoryNo);
    
    if(!categoryNoList.includes(categoryNo)) { // 기존에 ajax를 실행한 적이 없을 경우에만 실행.
        categoryNoList.push(categoryNo);
        // console.log("ajax 실행됨 : 공용주소록 리스트 조회 (contacts/list.publicContacts)");

        // 카테고리 번호로 조회 후, 각 주소록 기본정보 가져옴.
        // result는 ArrayList<Contacts> list임.
        $.ajax({
            url:"contacts/list.publicContacts",
            data:{categoryNo:categoryNo},
            success:function(result){
                let value = "";
                for(let i=0; i<result.length; i++) {
                    value +=    `<li class="sm-cate">
                                    <div>
                                        <input type="hidden" value="${result[i].contactsNo}">
                                        <span class="material-icons icon">subdirectory_arrow_right</span>
                                        <span> ${result[i].contactsName}</span>
                                        <span class="userCount">(${result[i].userCount})</span>
                                    </div>
                                </li>\n`
                }
                contentsArea.html(value);
            },
            error:function(){
                console.log("ajax 통신 실패: 주소록 카테고리 " + categoryNo + "번의 데이터 조회실패.");
            },
        })
    }
});



/* ==================== 메인 컨텐츠 ==================== */

// 조회된 데이터를 보관할 변수들.
// (동일한 데이터 요청시, 다시 db로부터 데이터를 조회하지 않고 이 변수들을 다시 사용함. 클릭할때마다 db에서 조회하는것 방지)
let allUsersList; // 전체사용자의 정보를 객체로 저장.
let allUsersListStrByUsername = ""; // 전체사용자의 정보를 문자열로 저장. (이름순 정렬된 상태)

const contactsMemberList = [];  // 주소록번호별 구성원 정보를 객체로 저장.
let contactsMemberListByUsernameStr = []; // 주소록번호별 구성원 정보를 문자열로 변환후, 객체로 저장. (이름순 정렬된 상태) (key:주소록번호, value:문자열로된정보)



// ----------------- 전체사용자 조회 -----------------

SelectAllUsersList(); // 전체사용자조회 실행. 주소록 페이지 들어오면 바로 실행함.

// (ajax) 전체사용자조회
function SelectAllUsersList() {
    $.ajax({
        url:"contacts/list.allUsers",
        data:{},
        success:function(result){
            allUsersList = result; 
            displayAllUsersList(allUsersList, "userName"); // 화면에 뿌려주기. (매개변수: 유저리스트, 정렬기준)
        },
        error:function(){
            console.log("ajax 통신 실패: 전체사용자 조회실패).");
        },
    })
}

// 전체사용자목록 화면에 뿌려주기
function displayAllUsersList(allUsersList, sortBy) { // (매개변수: 유저리스트, 정렬기준)
    const allUsersListStr = convertAllUsersListToStr(allUsersList); // 배열을 문자열로 변환
    
    if(sortBy === "userName") { // 이름순 정렬일 경우 (주소록페이지 처음 로딩시만 딱 한번 실행됨)
        // 만들어둔 문자열 데이터를 바깥 변수에 저장해놓기. "모든사용자"를 다시 클릭했을때 새로 문자열 만들지 않고, 기존에 만들어둔것 사용하기 위함.
        allUsersListStrByUsername = allUsersListStr;
        $(".allUsers .userCount").text(`(${allUsersList.length})`); // 사이드바 카테고리 중, 모든사용자의 인원수 채워넣기. ex) 모든사용자(31)
    }

    $(".section__list-content ul").html(allUsersListStr); // 화면에 전체사용자 리스트 뿌려주기.
}

// 전체사용자목록을 화면에 뿌려줄 수 있도록 문자열로 변환하는 메소드
function convertAllUsersListToStr(allUsersList) {
    let allUsersListStr = "";

    for(let i=0; i<allUsersList.length; i++) {
        let role = (allUsersList[i].userAuth === "A") ? "선생님" : "학생";
        let phone = (allUsersList[i].phonePublic === "N") ? "비공개" : allUsersList[i].phone;

        allUsersListStr += `<!-- 한 줄의 사용자 데이터 -->
                        <li class="userInfo">
                            <div class="checkbox">
                                <input type="checkbox" name="" id="">
                            </div>
                            <div class="star">
                                <span class="material-symbols-rounded icon star">star</span>
                            </div>
                            <div class="userName">
                                <span class="material-symbols-rounded icon profile-pic">account_circle</span>
                                ${allUsersList[i].userName}
                            </div>
                            <div class="userId">${allUsersList[i].userId}</div>
                            <div class="role">${role}</div>
                            <div class="birthday">${allUsersList[i].birthday}</div>
                            <div class="phone">${phone}</div>
                        </li>\n`;
    } // for
    return allUsersListStr;
};

// 사이드바에서 "모든사용자" 클릭시, 모든사용자를 메인화면에 뿌려줌. (db에 재조회 없이 기존 데이터 사용) (이름순 정렬)
$(".public-contacts li.allUsers").click(function(){
    $(".section__list-content ul").html(allUsersListStrByUsername); // 화면에 전체사용자 리스트 뿌려주기.
});


// ----------------- 전체사용자 정렬 -----------------

// 메인컨텐츠 상단에 각 정렬항목 클릭시 해당 항목으로 정렬하기.
$(".section__list-header li div span").click(function(){
    const sortBy = $(this).parent().attr("class");
    console.log(sortBy);

    let sortedList;
    switch(sortBy) {
        case 'userName': sortedList = sortByUserName(allUsersList); break;
        case 'userId': sortedList = sortByUserId(allUsersList); break;
        case 'role': sortedList = sortByRole(allUsersList); break;
        case 'birthday': sortedList = sortByBirthday(allUsersList); break;
        case 'phone': sortedList = sortByPhone(allUsersList); break;
    }

    displayAllUsersList(sortedList, sortBy);
});

// 각 정렬의 오름차순/내림차순을 추적하기 위한 변수들
// 내림차순이었다면 오름차순으로, 오름차순이었다면 내림차순으로 정렬함.
let isAlreadySortedByUserName = false;
let isAlreadySortedByUserId = false;
let isAlreadySortedByRole = false;
let isAlreadySortedByBirthday = false;
let isAlreadySortedByPhone = false;

// 이름으로 정렬
function sortByUserName(userList){
    if(isAlreadySortedByUserName) {
        isAlreadySortedByUserName = false;
        return userList.sort((a, b) => a.userName.toLowerCase() < b.userName.toLowerCase() ? 1 : -1);
    }else {
        isAlreadySortedByUserName = true;
        return userList.sort((a, b) => a.userName.toLowerCase() < b.userName.toLowerCase() ? -1 : 1);
    }
}

// 아이디로 정렬
function sortByUserId(userList){
    if(isAlreadySortedByUserId) {
        isAlreadySortedByUserId = false;
        return userList.sort((a, b) => a.userId.toLowerCase() < b.userId.toLowerCase() ? 1 : -1);
    }else {
        isAlreadySortedByUserId = true;
        return userList.sort((a, b) => a.userId.toLowerCase() < b.userId.toLowerCase() ? -1 : 1);
    }
}

// 역할로 정렬
function sortByRole(userList) {
    if (isAlreadySortedByRole) {
        isAlreadySortedByRole = false;
        return userList.sort((a, b) => {
            if (a.userAuth === b.userAuth) {
                return a.userName.toLowerCase() < b.userName.toLowerCase() ? -1 : 1;
            }
            return a.userAuth < b.userAuth ? 1 : -1;
        });
    } else {
        isAlreadySortedByRole = true;
        return userList.sort((a, b) => {
            if (a.userAuth === b.userAuth) {
                return a.userName.toLowerCase() < b.userName.toLowerCase() ? -1 : 1; 
            }
            return a.userAuth < b.userAuth ? -1 : 1;
        });
    }
}

// 생일로 정렬
function sortByBirthday(userList){
    
    // 문자열을 JS Date형식으로 변환하는 메소드
    const parseBirthday = (birthday) => {
        // chatGPT 형님의 도움 받음. (이렇게도 가능하구나...)
        const [month, day] = birthday.split('월 ').map(part => parseInt(part, 10));
        return new Date(2000, month - 1, day); // 날짜 비교를 위해서 모든 생일의 년도를 2000년으로 지정함.
    };

    if (isAlreadySortedByBirthday) {
        isAlreadySortedByBirthday = false;
        return userList.sort((a, b) => parseBirthday(b.birthday) - parseBirthday(a.birthday));
    } else {
        isAlreadySortedByBirthday = true;
        return userList.sort((a, b) => parseBirthday(a.birthday) - parseBirthday(b.birthday));
    }
}

// 전화번호로 정렬
function sortByPhone(userList) {
    const getPhone = (user) => {
        return user.phonePublic === "Y" ? user.phone : "000-0000-0000"; // 비공개인 경우 "000-0000-0000"으로 설정함.
    };

    if (isAlreadySortedByPhone) {
        isAlreadySortedByPhone = false;
        return userList.sort((a, b) => {
            return getPhone(a).localeCompare(getPhone(b));
        });
    } else {
        isAlreadySortedByPhone = true;
        return userList.sort((a, b) => {
            return getPhone(b).localeCompare(getPhone(a));
        });
    }
}

// ----------------- 주소록구성원 조회 -----------------

// 공유주소록 클릭시, 주소록 번호로 데이터 조회하여 해당하는 주소록 구성원 화면에 뿌려주기.
$(".mid-cate__contents").on("click", ".sm-cate", function(){ // 동적으로 생성된 객체에 효과를 주기 위해 이 방식을 사용함.
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    selectContactsMemberList(contactsNo, "userName"); // 매개변수: 주소록번호, 정렬기준
})

// 개인주소록 클릭시, 주소록 번호로 데이터 조회하여 해당하는 주소록 구성원 화면에 뿌려주기.
$(".big-cate.private-contacts .mid-cate").click(function(){
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    selectContactsMemberList(contactsNo, "userName"); // 매개변수: 주소록번호, 정렬기준
});


// (ajax) 주소록 클릭시, 주소록 번호로 해당하는 주소록구성원 조회
function selectContactsMemberList(contactsNo, sortBy){ // 매개변수: 주소록번호, 정렬기준
    // console.log(contactsNo);

    if(!contactsMemberList.find((member) => member.contactsNo === contactsNo)) { // 기존에 검색된 적이 없는 주소록 번호일 경우에만 실행
        // console.log("ajax 실행됨 : 주소록 구성원 조회(contacts/list.users)");
        
        $.ajax({
            url:"contacts/list.users",
            data:{contactsNo:contactsNo},
            success:function(result){
                // console.log(result);

                for(let i=0; i<result.length; i++) {
                    const contactsMember = {
                        contactsNo: contactsNo,
                        memberList: result,
                    };

                    contactsMemberList.push(contactsMember);
                }
                // console.log(contactsMemberList);

                displayContactsMemberList(contactsNo, sortBy);
            },
            error:function(){
                console.log("ajax 통신 실패: 주소록 " + contactsNo +  "번 유저리스트의 데이터 조회실패.");
            },
        })

    } else { // 기존에 검색된 적이 있는 주소록 번호일 경우 : contactsMemberListByUsernameStr(배열)에 저장된 객체를 꺼내서 화면에 뿌려줌.
        // console.log("기존에 검색된 적이 있는 주소록 번호일 경우");
        const index = contactsMemberListByUsernameStr.findIndex((member) => member.contactsNo === contactsNo);
        $(".section__list-content ul").html(contactsMemberListByUsernameStr[index].memberList);
    }
}

// 주소록구성원 화면에 뿌려주기
function displayContactsMemberList(contactsNo, sortBy){
    let value = "";
    for(let i=0; i<contactsMemberList.length; i++) {

        let role;
        switch(contactsMemberList[i].role) {
            case "L": role = "팀장"; break;
            case "F": role = "팀원"; break;
            default : role = "-";
        };

        let phone = (contactsMemberList[i].phonePublic === "N") ? "비공개" : contactsMemberList[i].phone;

        value +=   `<!-- 한 줄의 사용자 데이터 -->
                    <li class="userInfo">
                        <div class="checkbox">
                            <input type="checkbox" name="" id="">
                        </div>
                        <div class="star">
                            <span class="material-symbols-rounded icon star">star</span>
                        </div>
                        <div class="userName">
                            <span class="material-symbols-rounded icon profile-pic">account_circle</span>
                            ${contactsMemberList[i].userName}
                        </div>
                        <div class="userId">${contactsMemberList[i].userId}</div>
                        <div class="role">${role}</div>
                        <div class="birthday">${contactsMemberList[i].birthday}</div>
                        <div class="phone">${phone}</div>
                    </li>\n`;
    }
    
    // console.log(value);
    $(".section__list-content ul").html(value);
    
    // 이름순 정렬일 경우, 만들어둔 문자열 데이터를 바깥 변수에 저장해놓기.
    // 동일한 요청있을시 새로 문자열 만들지 않고, 만들어둔것 사용하기위함.
    if(sortBy === "userName") { 
        contactsMemberListByUsernameStr.push({
            contactsNo:contactsNo,
            memberListStr:value,
        })
    }
} //displayContactsMemberList()

