// 커리큘럼 바 관련 jQuery
$(function(){
    $(".java-cir").click(function(){
        $(".progress-bar").css("width","8%");
        $(".java-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".oracle-cir").click(function(){
        $(".progress-bar").css("width","16%");
        $(".oracle-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".jdbc-cir").click(function(){
        $(".progress-bar").css("width","24%");
        $(".jdbc-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".html-cir").click(function(){
        $(".progress-bar").css("width","32%");
        $(".html-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".css-cir").click(function(){
        $(".progress-bar").css("width","40%");
        $(".css-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".js-cir").click(function(){
        $(".progress-bar").css("width","48%");
        $(".js-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".jQuery-cir").click(function(){
        $(".progress-bar").css("width","56%");
        $(".jQuery-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".servlet-cir").click(function(){
        $(".progress-bar").css("width","64%");
        $(".servlet-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".jsp-cir").click(function(){
        $(".progress-bar").css("width","72%");
        $(".jsp-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".ajax-cir").click(function(){
        $(".progress-bar").css("width","82%");
        $(".ajax-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".myBatis-cir").click(function(){
        $(".progress-bar").css("width","90%");
        $(".myBatis-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })

    $(".spring-cir").click(function(){
        $(".progress-bar").css("width","100%");
        $(".spring-cir").css({backgroundColor:"#C2F0FF", color:"#333"});
    })
})

//현재 년도 및 월 변수 추가
let currentYear = new Date().getFullYear();
let currentMonth = new Date().getMonth();

//달력을 생성하고 표시하는 함수
function generateCalendar() {
    // 달력의 헤더 생성
    let headerHTML = '<span style="color:#000; text-align:center; position:relative;">' + currentYear + '년 ' + (currentMonth + 1) + '월</span>'

    // 달력 영역에 헤더 추가
    let headerContainer = document.createElement("div");
    headerContainer.classList.add("header");
    headerContainer.innerHTML = headerHTML;

    // wrapper에 헤더 추가
    let wrapper = document.querySelector(".wrapper");
    wrapper.innerHTML = ''; // 헤더를 지우고 다시 추가
    wrapper.appendChild(headerContainer);

    // 달력 본문 영역 생성
    
    // 달력의 본문 생성
    let calendarHTML = '<table>';
    // 요일 표시
    calendarHTML += '<tr>';
    const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
    for (let day of daysOfWeek) {
        if(day == '일'){
            calendarHTML += '<th style="color:red">' + day + '</th>'
        }else if(day == '토'){
            calendarHTML += '<th style="color:blue">' + day + '</th>'
        }else{
            calendarHTML += '<th>' + day + '</th>';
        }
    }
    calendarHTML += '</tr>';

    // 각 주와 날짜 표시
    const totalDaysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();

    let dayCounter = 1;

    for (let i = 0; i < 6; i++) { // 최대 6주 (일주일이 6주일 경우도 있음)
        calendarHTML += '<tr>';

        for (let j = 0; j < 7; j++) { // 7일 (요일)
            let cellStyle = ''; // 셀에 적용할 스타일 초기화

            if (i === 0 && j < firstDayOfMonth) {
                // 첫 주의 시작일 이전은 빈 셀로 채움
                calendarHTML += '<td></td>';
            } else if (dayCounter > totalDaysInMonth) {
                // 마지막 날 이후는 빈 셀로 채움
                calendarHTML += '<td></td>';
            } else {
                // 유효한 날짜일 경우 날짜 표시
                if (j === 0) {
                    // 일요일은 빨간색
                    cellStyle = 'color: red;';
                } else if (j === 6) {
                    // 토요일은 파란색
                    cellStyle = 'color: blue;';
                }
                calendarHTML += '<td style="' + cellStyle +'">' + dayCounter + '</td>';
                dayCounter++;
            }
        }

        calendarHTML += '</tr>';

        // 모든 날짜를 표시한 경우 종료
        if (dayCounter > totalDaysInMonth) {
            break;
        }
    }

    calendarHTML += '</table>';
    // 생성된 달력을 달력 컨테이너에 추가
    var calendarContainer = document.getElementById("calendar");
    calendarContainer.innerHTML = calendarHTML;
}

// 페이지 로드 시 실행할 함수들
window.onload = function () {
    generateCalendar();
    // 현재 날짜에 스타일 추가
    highlightCurrentDate();

    todoHeader();
    dDay();
};

// 현재 날짜 스타일 추가 함수
function highlightCurrentDate(){
    let currentDate = new Date();
    let currentDay = currentDate.getDate();

    let calendarCells = document.querySelectorAll('td');
    calendarCells.forEach(function (cell) {
        let cellDay = parseInt(cell.innerText);

        if(cellDay === currentDay){
            cell.style.color = '#000000';
            cell.style.borderRadius = '10px';
            cell.style.backgroundColor = '#C2F0FF';
        }
    })
}

// 현재 날짜 요일 todo 헤더에 넣는 함수

function todoHeader(){
    let currentDate = new Date();
    let currentDay = currentDate.getDay();
    
    switch(currentDay){
        case 0:
            currentDay = '일';
            break;
        case 1:
            currentDay = '월';
            break;
        case 2:
            currentDay = '화';
            break;
        case 3:
            currentDay = '수';
            break;
        case 4:
            currentDay = '목';
            break;
        case 5:
            currentDay = '금';
            break;
        case 6:
            currentDay = '토';
            break;
        default:
            currentDay = 'undefined';
            break;
    }
    
    document.querySelector('.todo-header').innerHTML = '<span style="color:#000"> <' + currentDay + '요일> </span><span>오늘의 일정</span>';
}

function setProgress(percentage) {
    const progressCircle = document.querySelector('.progress-circle');
    const progressText = document.querySelector('.progress-text');
  
    // 각도 계산 (percentage * 3.6 = 각도)
    const angle = percentage * 3.6;
  
    // 배경을 업데이트 (conic-gradient)
    progressCircle.style.background = `conic-gradient(#C2F0FF 0% ${angle}deg, #e6f9ff ${angle}deg 360deg)`;
    // 텍스트 업데이트
    progressText.textContent = percentage + '%';
  
}

// 종강일까지 남은 일 수 계산하는 함수
function dDay(){
    // Thu Aug 22 2024 12:36:18 GMT+0900 (한국 표준시) => Date 객체의 기본값
    let startDate = new Date('Thu May 9 2024 09:00:00 GMT+0900 (한국 표준시)')
    let nowDate = new Date();
    let endDate = new Date('Fri Oct 25 2024 17:50:00 GMT+0900 (한국 표준시)')

    // 총 기간
    let totalDate = endDate - startDate;
    // 경과된 기간
    let elapseDate = nowDate - startDate;
    // 진행도 퍼센드
    let perDate =  Math.floor((elapseDate / totalDate) * 100);

    // 종강까지 남은 일 수
    let dDay = Math.ceil((endDate - nowDate) / (1000 * 60 * 60 * 24));

    document.querySelector('#D-day').innerHTML = '<p>D - '+ dDay +'</p>';
    document.querySelector('.comment-area').innerHTML = '<p> 종강까지 '+ dDay +'일 남았다잉~</p><br><p>힘내자!!!</p>';

    setProgress(perDate)

}
  