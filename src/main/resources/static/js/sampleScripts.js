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