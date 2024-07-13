document.addEventListener('DOMContentLoaded', function () {
    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);
    const maxDate = new Date(today);
    maxDate.setDate(maxDate.getDate() + 30);

    const tomorrowISO = tomorrow.toISOString().split('T')[0];
    const maxDateISO = maxDate.toISOString().split('T')[0];

    document.getElementById('measurementDate').setAttribute('min', tomorrowISO);
    document.getElementById('measurementDate').setAttribute('max', maxDateISO);
});

function updateAvailableTimes(date) {
    fetch(`/api/available-times?date=${date}`)
        .then(response => response.json())
        .then(data => {
            const timeSelect = document.getElementById('measurementTime');
            timeSelect.innerHTML = '';
            if (data.length === 0) {
                const option = document.createElement('option');
                option.textContent = 'Нет доступного времени';
                timeSelect.appendChild(option);
            } else {
                data.forEach(time => {
                    const option = document.createElement('option');
                    option.value = time;
                    option.textContent = time;
                    timeSelect.appendChild(option);
                });
            }
        })
        .catch(error => console.error('Ошибка при получении доступного времени:', error));
}

//начало contactsRotate

let contactsBar = document.querySelectorAll('.about-contacts-bar-content');
let contactsBarBackface = document.querySelectorAll('.about-contacts-bar-content-backface');
let contactsBarArea = document.querySelectorAll('.about-contacts-bar, .about-contacts-bar-right');

for (let i = 0; i < contactsBarArea.length; i++) {
    contactsBarArea[i].addEventListener('mouseenter', () => {
        contactsBar[i].classList.add('active');
        contactsBarBackface[i].classList.add('active');
    })
    contactsBarArea[i].addEventListener('mouseleave', () => {
        contactsBar[i].classList.remove('active');
        contactsBarBackface[i].classList.remove('active');
    })

    document.addEventListener('click', e => {
        let targetContactsBar = e.target;
        let its_contactsBar = targetContactsBar === contactsBarArea[i] || contactsBarArea[i].contains(targetContactsBar);

        if (!its_contactsBar) {
            contactsBar[i].classList.remove('active');
            contactsBarBackface[i].classList.remove('active');
        }
    })
}

//конец contactsRotate