document.addEventListener('DOMContentLoaded', function() {
    const monthColors = [
        "#FFCDD2", // January
        "#F8BBD0", // February
        "#E1BEE7", // March
        "#D1C4E9", // April
        "#C5CAE9", // May
        "#BBDEFB", // June
        "#B3E5FC", // July
        "#FFCDD2", // August
        "#B2DFDB", // September
        "#C8E6C9", // October
        "#DCEDC8", // November
        "#F0F4C3"  // December
    ];

    const date = new Date();
    const currentMonth = date.getMonth(); // 0 (January) to 11 (December)
    
    const monthElement = document.querySelector('.month');
    if (monthElement) {
        monthElement.style.backgroundColor = monthColors[currentMonth];
    }
});

function toggleSubMenu(menuId) {
    const subMenu = document.getElementById(menuId);
    if (subMenu.style.display === 'block') {
        subMenu.style.display = 'none';
    } else {
        subMenu.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.toggle-btn').forEach(function (button) {
        button.addEventListener('click', function () {
            var icon = button.querySelector('.material-symbols-rounded');

            if (icon.textContent === 'keyboard_arrow_down') {
                icon.textContent = 'keyboard_arrow_up';
            } else {
                icon.textContent = 'keyboard_arrow_down';
            }
        })
    })
})

function floatingObject(selector){
    gsap.to(selector, 1, {
        y : 10,
        repeat : -1,
        yoyo : true,
        ease : Power1.easeInOut,
    });
}

floatingObject('#btn-today');


// Add this to your calendar.js

document.addEventListener('DOMContentLoaded', () => {
    const daysContainer = document.querySelector('#calendar-view .days');
    const monthName = document.querySelector('#calendar-view .month-name');
    const prev = document.querySelector('#calendar-view .prev');
    const next = document.querySelector('#calendar-view .next');
    const eventForm = document.getElementById('eventForm');
    const modal = document.getElementById('eventModal');
    const span = document.getElementsByClassName('close')[0];
    const calendar = document.querySelector('#calendar-view .calendar');
    const todayButton = document.getElementById('btn-today');
    let events = {};

    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth();

    const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    function renderCalendar() {
        monthName.textContent = `${months[month]} ${year}`;
        daysContainer.innerHTML = '';

        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        for (let i = 0; i < firstDay; i++) {
            const emptyDiv = document.createElement('div');
            daysContainer.appendChild(emptyDiv);
        }

        for (let i = 1; i <= lastDate; i++) {
            const dayDiv = document.createElement('div');
            dayDiv.textContent = i;

            const dayOfWeek = new Date(year, month, i).getDay();

            if (dayOfWeek === 0) {
                dayDiv.classList.add('sunday');
            } else if (dayOfWeek === 6) {
                dayDiv.classList.add('saturday');
            }

            dayDiv.addEventListener('click', () => openEventModal(year, month + 1, i));

            if (i === date.getDate() && month === date.getMonth() && year === date.getFullYear()) {
                dayDiv.classList.add('today');
            }

            const eventKey = `${year}-${month + 1}-${i}`;
            if (events[eventKey]) {
                events[eventKey].forEach(event => {
                    const eventDiv = document.createElement('div');
                    eventDiv.textContent = event;
                    eventDiv.classList.add('event');
                    dayDiv.appendChild(eventDiv);
                });
            }

            daysContainer.appendChild(dayDiv);
        }
    }
    

    function openEventModal(year, month, day) {
        modal.style.display = 'block';
        document.getElementById('eventStartDate').value = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
        document.getElementById('eventEndDate').value = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
    }

    function goToToday() {
        const today = new Date();
        year = today.getFullYear();
        month = today.getMonth();
        renderCalendar();
    }

    span.onclick = function () {
        modal.style.display = 'none';
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    }

    renderCalendar();

    prev.addEventListener('click', () => {
        month--;
        if (month < 0) {
            month = 11;
            year--;
        }
        renderCalendar();
    });

    next.addEventListener('click', () => {
        month++;
        if (month > 11) {
            month = 0;
            year++;
        }
        renderCalendar();
    });

    calendar.addEventListener('wheel', (event) => {
        event.preventDefault();

        if (event.deltaY < 0) {
            month--;
            if (month < 0) {
                month = 11;
                year--;
            }
        } else {
            month++;
            if (month > 11) {
                month = 0;
                year++;
            }
        }
        renderCalendar();
    });

    eventForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const eventTitle = document.getElementById('eventTitle').value;
        const eventStartDate = new Date(document.getElementById('eventStartDate').value);
        const eventEndDate = new Date(document.getElementById('eventEndDate').value);

        if (eventTitle && eventStartDate && eventEndDate) {
            let currentDate = new Date(eventStartDate);
            while (currentDate <= eventEndDate) {
                const eventKey = `${currentDate.getFullYear()}-${currentDate.getMonth() + 1}-${currentDate.getDate()}`;
                if (!events[eventKey]) {
                    events[eventKey] = [];
                }
                events[eventKey].push(eventTitle);
                currentDate.setDate(currentDate.getDate() + 1);
            }
            renderCalendar();
            eventForm.reset();
            modal.style.display = 'none';
        }
    });

    todayButton.addEventListener('click', goToToday);
});

