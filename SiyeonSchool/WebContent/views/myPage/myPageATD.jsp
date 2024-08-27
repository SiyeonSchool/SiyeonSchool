<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/myPageATD.css">
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
        
        <%
            String userName = loginUser.getUserName();
            String phone = loginUser.getPhone();
            String birthday = loginUser.getBirthday();
            String address = loginUser.getAddress();
            String email = loginUser.getEmail();
            String githubUrl = loginUser.getGithubUrl();
            String notionUrl = loginUser.getNotionUrl();
            String userId = loginUser.getUserId();
        %>
    <div class="wrapper">
        <section class="body__wrapper">
            <%@ include file="../common/myPageSide.jsp" %>
            <div id="myATD-body">
                <div class="myATD-title">출결 / 휴가</div>
                <div class="myATD-content">
                    <div id="monthNavigation">
                        <span class="material-icons" id="nextMonth">navigate_before</span>
                        <span id="currentMonth"></span>
                        <span class="material-icons" id="prevMonth">navigate_next</span>
                    </div>
                
                    < id="attendanceTable">
                        <thead>
                            <tr class="date-inner">
                                <td colspan="2"></td>
                            </tr>

                            <tr class="day-inner">
                                <td colspan="2">출결정보</td>
                            </tr>

                            
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2">출결현황</td>
                            </tr>
                            <!-- 모시꺵이 -->
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </div>

    <script>
        // 학생 ID를 여기에 설정
        const studentId = 1;

        // 현재 날짜 기준으로 선택된 월을 설정
        let selectedDate = new Date();

        // 월 이름 배열
        const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월",
                            "7월", "8월", "9월", "10월", "11월", "12월"];
        const dayNames = ["일", "월", "화", "수", "목", "금", "토"]

        // 특정 월의 모든 날짜를 반환하는 함수
        function getAllDaysInMonth(year, month) {
            let date = new Date(year, month, 1);
            let days = [];
            while (date.getMonth() === month) {
                days.push(new Date(date));
                date.setDate(date.getDate() + 1);
            }
            return days;
        }

        // 출석 데이터를 가져오는 함수 (여기서는 예시 데이터를 사용)
        function getAttendanceDataForMonth(date) {
            // 실제 서버에서 데이터를 가져오는 부분을 대체
            const dummyData = [
                { date: '2024-08-01', status: 'Present' },
                { date: '2024-08-02', status: 'Absent' },
                { date: '2024-08-03', status: 'Late' },
                // 이곳에 더 많은 데이터를 추가할 수 있습니다.
            ];

            const month = date.getMonth() + 1; // 1월이 0이므로 +1
            const year = date.getFullYear();

            // 선택된 월의 데이터 필터링
            return dummyData.filter(record => {
                const recordDate = new Date(record.date);
                return recordDate.getMonth() + 1 === month && recordDate.getFullYear() === year;
            });
        }

        // 출석 데이터를 테이블에 업데이트하는 함수
        function updateAttendanceTable(date) {
            const tableBody = document.getElementById('attendanceTable').querySelector('thead');
            tableBody.innerHTML = ''; // 기존 데이터를 지웁니다.

            const year = date.getFullYear();
            const month = date.getMonth();
            const allDays = getAllDaysInMonth(year, month);
            const attendanceData = getAttendanceDataForMonth(date);

            allDays.forEach(day => {
                const date_inner = document.querySelector('.date-inner');
                const dateCell = document.createElement('td');
                const day_inner = document.querySelector('.day-inner');
                const dayCell = document.createElement('td');
                // const statusCell = document.createElement('td');

                const dateString = day.getDate();
                dateCell.textContent = dateString;
                const dayString = dayNames[day.getDay()];
                dayCell.textContent = dayString;
                // dateCell.setAttribute('colspan', '4'); // colspan 설정
                const attendanceRecord = attendanceData.find(record => record.date === dayString);
                // statusCell.textContent = attendanceRecord ? attendanceRecord.status : 'No Data';

                date_inner.appendChild(dateCell);
                day_inner.appendChild(dayCell);
                // date_inner.appendChild(statusCell);
                // tableBody.appendChild(date_inner);
            });

            // 현재 선택된 월 표시
            document.getElementById('currentMonth').textContent = date.getFullYear() +"년 " + monthNames[date.getMonth()];
        }

        // 이전 월로 이동
        document.getElementById('nextMonth').addEventListener('click', () => {
            selectedDate.setMonth(selectedDate.getMonth() - 1);
            updateAttendanceTable(selectedDate);
        });
        
        // 다음 월로 이동
        document.getElementById('prevMonth').addEventListener('click', () => {
            selectedDate.setMonth(selectedDate.getMonth() + 1);
            updateAttendanceTable(selectedDate);
        });

        // 페이지가 로드될 때 현재 월의 데이터를 표시
        window.onload = () => {
            updateAttendanceTable(selectedDate);
        };
     </script>

</body>
</html>