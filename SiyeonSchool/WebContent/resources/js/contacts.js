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

// ajax. 사이드바 주소록 카테고리 클릭시, 해당 카테고리에 속한 주소록 데이터 가져오기
// ex) "세미 프로젝트" 클릭시, 데이터 조회하여 하위의 "1조" ~ "5조" 리스트 화면에 뿌려주기. (조이름 + 인원수)
const contactsNoList = []; // ajax가 실행되었었는지 기록하기 위한 배열
$(".public-contacts .mid-cate__title.dynamic").click(function(){ // 동적으로 생성된 카테고리만 적용됨(.dynamic)
    const categoryNo = $(this).find("input").val();
    const contentsArea = $(this).next(); // ul.mid-cate__contents
    // console.log(categoryNo);
    
    if(!contactsNoList.includes(categoryNo)) { // 기존에 ajax를 실행한 적이 없을 경우에만 실행.
        contactsNoList.push(categoryNo);
        // console.log("ajax 실행됨");

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
SelectAllUsersList(); // 주소록 페이지 들어오면 바로 실행함. (전체사용자조회)

// ajax. 전체사용자조회 하여 화면에 뿌려주기
function SelectAllUsersList() {
    $.ajax({
        url:"contacts/list.allUsers",
        data:{},
        success:function(result){
            let value = "";
            for(let i=0; i<result.length; i++) {

                let role = (result[i].userAuth === "A") ? "선생님" : "학생";
                let phone = (result[i].phonePublic === "N") ? "비공개" : result[i].phone;

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
                                    ${result[i].userName}
                                </div>
                                <div class="userId">${result[i].userId}</div>
                                <div class="role">${role}</div>
                                <div class="birthday">${result[i].birthday}</div>
                                <div class="phone">${phone}</div>
                            </li>\n`;
            }

            $(".section__list-content ul").html(value);

            $(".allUsers .userCount").text(`(${result.length})`); // 사이드바 카테고리 중, 모든사용자(??)에 전체사용자 숫자 채워넣기.
        },
        error:function(){
            console.log("ajax 통신 실패: 전체사용자조회 (주소록 " + contactsNo +  "번 유저리스트의 데이터 조회실패).");
        },
    })
}

// ajax. 주소록 클릭시, 주소록 번호로 데이터 조회하여 해당하는 주소록 구성원 화면에 뿌려주기.
$(".mid-cate__contents").on("click", ".sm-cate", function(){ // 동적으로 생성된 객체에 효과를 주기 위해 이 방식을 사용함.
    const contactsNo = $(this).find("input").val(); // 클릭된 주소록 번호
    console.log(contactsNo);

    $.ajax({
        url:"contacts/list.users",
        data:{contactsNo:contactsNo},
        success:function(result){

            let value = "";
            for(let i=0; i<result.length; i++) {

                let role = (result[i].userAuth === "L") ? "팀장" : "팀원";
                let phone = (result[i].phonePublic === "N") ? "비공개" : result[i].phone;

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
                                    ${result[i].userName}
                                </div>
                                <div class="userId">${result[i].userId}</div>
                                <div class="role">${role}</div>
                                <div class="birthday">${result[i].birthday}</div>
                                <div class="phone">${phone}</div>
                            </li>\n`;
            }

            $(".section__list-content ul").html(value);
        },
        error:function(){
            console.log("ajax 통신 실패: 주소록 " + contactsNo +  "번 유저리스트의 데이터 조회실패.");
        },
    })

})

