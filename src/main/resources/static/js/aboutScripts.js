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

    //начало dialogPolitic

    let modalPoliticPopup = document.querySelector('.modal-politic-popup');
    let modalPoliticDialog = document.querySelector('.modal-content');
    let modalDialog = document.querySelector('.modal-dialog');
    let mainContainer = document.querySelector('.main-container');
    let buttonPolitic = document.querySelector('.politic-confidentiality-box');
    let buttonPoliticClose = document.querySelector('.modal-close-button');

    let backdrop = document.createElement('div');

    function createBackdrop() {
        document.body.append(backdrop);
    }

    function removeBackdrop() {
        backdrop.remove();
    }

    function activatePopupPolitic() {
        modalPoliticPopup.classList.add('active');
        modalPoliticPopup.classList.add('fade');
        modalDialog.classList.add('active');
        backdrop.classList.add('modal-backdrop');
        backdrop.classList.add('fade');
        backdrop.classList.add('in');
        mainContainer.classList.add('passive');
    }

    function hidePopupPolitic() {
        modalPoliticPopup.classList.remove('active');
        modalPoliticPopup.classList.remove('fade');
        modalDialog.classList.remove('active');
        backdrop.classList.remove('modal-backdrop');
        backdrop.classList.remove('fade');
        backdrop.classList.remove('in');
        mainContainer.classList.remove('passive');
    }

    buttonPolitic.addEventListener('click', () => {
        createBackdrop();
        activatePopupPolitic();
    })

    document.addEventListener('click', e => {
        let targetModalPoliticDialog = e.target;
        let its_modalPoliticContent = targetModalPoliticDialog === modalPoliticDialog || modalPoliticDialog.contains(targetModalPoliticDialog);
        let its_buttonPolitic = targetModalPoliticDialog === buttonPolitic || buttonPolitic.contains(targetModalPoliticDialog);
        let popup_is_activePoliticContent = modalPoliticPopup.classList.contains('active');

        if (!its_modalPoliticContent && !its_buttonPolitic && popup_is_activePoliticContent) {
            removeBackdrop();
            hidePopupPolitic();
        }
    })

    buttonPoliticClose.addEventListener('click', () => {
        removeBackdrop();
        hidePopupPolitic();
    })





//конец dialogPolitic