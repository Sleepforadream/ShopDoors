    let profileBox = document.querySelector('.profile-box');
    let mainHeader = document.querySelector('.main-header');
    let profileInfoBox = document.querySelector('.profile-info-box');
    let shopFooter = document.querySelector('.shop-footer');
    let secondMenu = document.querySelector('.second-header-menu');

    secondMenu.style.display = "none";

    let coordsShopFooter = shopFooter.getBoundingClientRect();

    let heightHeader = mainHeader.offsetHeight;

    let heightProfileBox = window.innerHeight - heightHeader;

    profileBox.style.minHeight = heightProfileBox + 'px'; // Применение позиционирования слайдера по вертикал

    let coordsProfileInfoBox = profileInfoBox.getBoundingClientRect();
    let coordsProfileBox = profileBox.getBoundingClientRect();

    if(coordsProfileInfoBox.bottom > coordsProfileBox.bottom || coordsProfileInfoBox.top < coordsProfileBox.top){

    profileBox.style.height = 450 + 'px';
    }


    let editPasswordMenu = document.querySelector('.edit-password-menu'); // Само окно
    let openEditPasswordMenuButton = document.querySelector('.button-enter-user.default'); // Кнопка для показа окна

    const toggleEditPasswordMenu = () => {
        editPasswordMenu.classList.toggle('active');
    }

    openEditPasswordMenuButton.addEventListener('click', e => {
        e.stopPropagation();
        toggleEditPasswordMenu();
    });

    let modalPasswordPopup = document.querySelector('.modal-password-popup');
    let modalPasswordDialog = document.querySelector('.modal-password-content');
    let modalDialog = document.querySelector('.modal-password-dialog');
    let mainPasswordPopupContainer = document.querySelector('.main-container');
    let deleteUserButton = document.querySelector('.button-enter-user.danger'); // Кнопка для показа окна
    let buttonPasswordClose = document.querySelector('.modal-close-button');
    let cancelDeleteButton = document.getElementById('cancelDeleteButton');

    let backdrop = document.createElement('div');

    function createBackdrop() {
        document.body.append(backdrop);
    }

    function removeBackdrop() {
        backdrop.remove();
    }

    function activatePopupPassword() {
        modalPasswordPopup.classList.add('active');
        modalPasswordPopup.classList.add('fade');
        modalDialog.classList.add('active');
        backdrop.classList.add('modal-backdrop');
        backdrop.classList.add('fade');
        backdrop.classList.add('in');
        mainPasswordPopupContainer.classList.add('passive');
    }

    function hidePopupPassword() {
        modalPasswordPopup.classList.remove('active');
        modalPasswordPopup.classList.remove('fade');
        modalDialog.classList.remove('active');
        backdrop.classList.remove('modal-backdrop');
        backdrop.classList.remove('fade');
        backdrop.classList.remove('in');
        mainPasswordPopupContainer.classList.remove('passive');
    }

    deleteUserButton.addEventListener('click', () => {
        createBackdrop();
        activatePopupPassword();
    })

    document.addEventListener('click', e => {
        let targetModalPasswordDialog = e.target;
        let its_modalPasswordContent = targetModalPasswordDialog === modalPasswordDialog || modalPasswordDialog.contains(targetModalPasswordDialog);
        let its_buttonPassword = targetModalPasswordDialog === deleteUserButton || deleteUserButton.contains(targetModalPasswordDialog);
        let popup_is_activePasswordContent = modalPasswordPopup.classList.contains('active');

        if (!its_modalPasswordContent && !its_buttonPassword && popup_is_activePasswordContent) {
            removeBackdrop();
            hidePopupPassword();
        }
    })

    buttonPasswordClose.addEventListener('click', () => {
        removeBackdrop();
        hidePopupPassword();
    })

    cancelDeleteButton.addEventListener('click', () => {
        removeBackdrop();
        hidePopupPassword();
    })