const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월",
                    "7월", "8월", "9월", "10월", "11월", "12월"];
const dayNames = ["일", "월", "화", "수", "목", "금", "토"];
let selectedDate = new Date();
const offset = new Date().getTimezoneOffset() * 60000;

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
    // AJAX 요청으로 출석 데이터 가져오기
    $.ajax({
        url: 'atdState.li',
        data: { currentMonth: currentMonth },
        success: (result) => {
            // 출석 데이터를 날짜별로 매핑
            const attendanceData = result; // 서버에서 받은 출석 데이터 (예: JSON 배열)

            // 각 날짜에 대한 출석 정보를 저장할 객체
            const attendanceMap = {};
            attendanceData.forEach((entry) => {
                attendanceMap[entry.day] = entry.stateName;
            });
            for (let i = 1; i <= lastDay; i++) {
                const currentDate = new Date(year, month, i);
                const dayName = dayNames[currentDate.getDay()];
                
                // 한국 표준시 (KST)로 YYYY-MM-DD 형식으로 날짜 변환
                let dateStr = currentDate.toLocaleDateString('ko-KR', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit'
                }).replace(/\.\s?/g, '-').trim();

                if (dateStr.endsWith('-')) {
                    dateStr = dateStr.slice(0, -1);
                }


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

                 // 주말인지 확인 (토요일: 6, 일요일: 0)
                if (currentDate.getDay() === 0 || currentDate.getDay() === 6) {
                    attendanceCell.textContent = '주말';
                    attendanceCell.style.color = 'gray'; // 주말 텍스트 색상 변경 가능
                } else {
                    // 출석 상태 설정
                    const attendanceState = attendanceMap[dateStr];
                    if (attendanceState) {
                        attendanceCell.textContent = attendanceState;
                        if (attendanceState === "출석") {
                            attendanceCell.style.color = 'blue';
                        } else if (attendanceState === "결석") {
                            attendanceCell.style.color = 'red';
                        } else {
                            attendanceCell.style.color = 'black';
                        }
                    } else {
                        attendanceCell.textContent = '';
                    }
                }
            }

            // 현재 월을 표시
            document.getElementById('currentMonth').textContent = `${year}년 ${monthNames[month]}`;
        },
        error: () => {
            console.error('출석 데이터를 가져오는 데 실패했습니다.');
        }
    });
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

