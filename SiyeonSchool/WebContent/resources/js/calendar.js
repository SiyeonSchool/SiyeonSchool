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
    
    let itemList = [];
    const todoInput = document.getElementById("todo-input");
    const todoAddButton = document.getElementById("todo-add");
    const todoList = document.getElementById("todo-list");

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
    
            const eventContainer = document.createElement('div');
            eventContainer.classList.add('event-container');
    
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
                    eventContainer.appendChild(eventDiv);
                });
            }
    
            dayDiv.appendChild(eventContainer);
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

    document.querySelectorAll('.toggle-btn').forEach(button => {
        button.addEventListener('click', () => {
            const menuId = button.getAttribute('onclick').match(/'(.+?)'/)[1];
            toggleSubMenu(menuId);
        });
    });

    function toggleSubMenu(menuId) {
        const subMenus = document.querySelectorAll('.sub');
        const currentMenu = document.getElementById(menuId);

        subMenus.forEach(menu => {
            if (menu !== currentMenu) {
                menu.style.display = 'none';
            }
        });

        currentMenu.style.display = currentMenu.style.display === 'block' ? 'none' : 'block';
    }

    document.querySelectorAll('.list').forEach(item => {
        item.addEventListener('click', event => {
            document.querySelectorAll('.list').forEach(i => i.classList.remove('active'));
            event.currentTarget.classList.add('active');
        });
    });

    document.querySelectorAll('.toggle-btn').forEach(button => {
        button.addEventListener('click', () => {
            const icon = button.querySelector('.material-symbols-rounded');
            icon.textContent = icon.textContent === 'keyboard_arrow_down' ? 'keyboard_arrow_up' : 'keyboard_arrow_down';
        });
    });

    function floatingObject(selector) {
        gsap.to(selector, 1, {
            y: 10,
            repeat: -1,
            yoyo: true,
            ease: Power1.easeInOut,
        });
    }

    floatingObject('#btn-today');

    function addItem() {
        const item = todoInput.value.trim();
        if (item) {
            itemList.push(item);
            todoInput.value = "";
            showList();
        }
    }

    function showList() {
        todoList.innerHTML = itemList.map((item, index) =>
            `<li>${item}<span class='close' data-id="${index}">&#10005;</span></li>`
        ).join('');

        document.querySelectorAll(".close").forEach(button => {
            button.addEventListener("click", deleteItem);
        });
    }

    function deleteItem() {
        const id = this.getAttribute("data-id");
        itemList.splice(id, 1);
        showList();
    }

    todoAddButton.addEventListener("click", addItem);

    todoList.addEventListener('click', event => {
        if (event.target.tagName === 'LI') {
            event.target.classList.toggle('checked');
        }
    });

    document.querySelectorAll('#solo .list').forEach(item => {
        item.addEventListener('click', () => {
            if (item.textContent.includes('할 일')) {
                showToDoView();
            }
        });
    });

    document.querySelectorAll('#team .list').forEach(item => {
        item.addEventListener('click', () => {
            if (item.textContent.includes('할 일')) {
                showToDoView();
            }
        });
    });

    document.querySelectorAll('#class .list').forEach(item => {
        item.addEventListener('click', () => {
            if (item.textContent.includes('일정표')) {
                showCalendarView();
            }
        });
    });

    function showCalendarView() {
        document.getElementById('calendar-view').style.display = 'block';
        document.getElementById('todo-view').style.display = 'none';
    }

    function showToDoView() {
        document.getElementById('calendar-view').style.display = 'none';
        document.getElementById('todo-view').style.display = 'block';
    }

    showCalendarView();
});

document.addEventListener("DOMContentLoaded", function() {
    const addButton = document.getElementById("todo-add");
    const todoInput = document.getElementById("todo-input");
    const todoList = document.getElementById("todo-list");

    addButton.addEventListener("click", function() {
        const taskText = todoInput.value.trim();

        if (taskText !== "") {
            const listItem = document.createElement("li");

            listItem.textContent = taskText;

            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.addEventListener("click", function() {
                todoList.removeChild(listItem);
            });

            listItem.appendChild(deleteButton);
            todoList.appendChild(listItem);

            todoInput.value = "";
        }
    });
});