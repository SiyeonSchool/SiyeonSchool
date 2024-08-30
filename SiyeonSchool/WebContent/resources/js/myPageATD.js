const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월",
                    "7월", "8월", "9월", "10월", "11월", "12월"];
const dayNames = ["일", "월", "화", "수", "목", "금", "토"];
let selectedDate = new Date();

// 특정 월의 날짜와 요일을 생성하는 함수
function generateCalendar(date) {
    const year = date.getFullYear();
    const month = date.getMonth();
    const lastDay = new Date(year, month + 1, 0).getDate();

    const dateRow = document.getElementById('dateRow');
    const dayRow = document.getElementById('dayRow');
    const attendanceRow = document.getElementById('attendanceData');
    dateRow.innerHTML = ''; // 기존 날짜 초기화
    dayRow.innerHTML = '';  // 기존 요일 초기화
    attendanceRow.innerHTML = ''; // 기존 출석 데이터 초기화

    const currentMonth = new Date(year, month + 1).toISOString().split('-').slice(0,2).join('-');

    console.log(currentMonth);

    for (let i = 1; i <= lastDay; i++) {
        const currentDate = new Date(year, month, i);
        const dayName = dayNames[currentDate.getDay()];

        // 날짜 셀 추가
        const dateCell = document.createElement('th');
        dateCell.textContent = i;
        dateRow.appendChild(dateCell);

        // 요일 셀 추가
        const dayCell = document.createElement('th');
        dayCell.textContent = dayName;
        dayRow.appendChild(dayCell);

        // 출석 데이터 셀 추가
        const attendanceCell = document.createElement('td');
        attendanceRow.appendChild(attendanceCell);

        if (currentDate.getDay() === 6 || currentDate.getDay() === 0) {
            attendanceCell.textContent = '주말'; // 토요일(6)이나 일요일(0)이라면 "주말"을 표시
            attendanceCell.style.color = 'red';
        } else {
        }
    }
    // AJAX 요청

    $.ajax({
        url: 'atdState.li',
        data: { currentMonth: currentMonth },
        success: (result) => {
            let value = result.stateName;
            cell.textContent = value; // 해당 셀에 값 설정
            if (value === "출석") {
                cell.style.color = 'blue';
            } else if (value === "결석") {
                cell.style.color = 'red';
            } else {
                cell.style.color = 'black';
            }
        },
        error: () => {
            cell.textContent = '에러 발생';
            cell.style.color = 'red';
        }
    });

    // 현재 월을 표시
    document.getElementById('currentMonth').textContent = `${year}년 ${monthNames[month]}`;
}

// 이전 월로 이동
document.getElementById('prevMonth').addEventListener('click', () => {
    selectedDate.setMonth(selectedDate.getMonth() - 1);
    generateCalendar(selectedDate);
});

// 다음 월로 이동
document.getElementById('nextMonth').addEventListener('click', () => {
    selectedDate.setMonth(selectedDate.getMonth() + 1);
    generateCalendar(selectedDate);
});

// 페이지 로드 시 초기화
window.onload = () => {
    generateCalendar(selectedDate);
};

