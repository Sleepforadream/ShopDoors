window.onload = function () {
//начало tooltipSocial

    let tooltipInner;
    let arrow;

    document.onmouseover = function (event) {
        let target = event.target;

        // если у нас есть подсказка...

        let tooltipInnerHtml = target.dataset.tooltip;
        let arrowHtml = target.dataset.tooltip;

        if (!tooltipInnerHtml) return;
        if (!arrowHtml) return;

        // ...создадим элемент для подсказки

        tooltipInner = document.createElement('div');
        arrow = document.createElement('div');

        tooltipInner.classList.add('tooltip-inner');

        arrow.className = 'tooltip-arrow';

        tooltipInner.innerHTML = tooltipInnerHtml;

        document.body.append(tooltipInner);
        document.body.append(arrow);

        // спозиционируем его сверху от аннотируемого элемента (top-center)
        let coords = target.getBoundingClientRect();

        let leftTooltip = coords.left + (target.offsetWidth - tooltipInner.offsetWidth) / 2;
        let leftTooltip2 = coords.left + (target.offsetWidth) / 2

        let topTooltip = coords.top + window.pageYOffset + 2 + target.offsetHeight;

        tooltipInner.style.left = leftTooltip + 'px';
        tooltipInner.style.top = topTooltip + 2 + 'px';

        arrow.style.left = leftTooltip2 + 'px';
        arrow.style.top = topTooltip + 'px';

        tooltipInner.style.transition = "0.5s";
        tooltipInner.style.opacity = "0.9";

        arrow.style.transition = "0.5s";
        arrow.style.opacity = "0.9";

    };

    document.onmouseout = function () {

        if (tooltipInner) {
            tooltipInner.remove();
            tooltipInner = null;
        }

        if (arrow) {
            arrow.remove();
            arrow = null;
        }

    };

//конец tooltipSocial

//начало userBlock

    let popup = document.querySelector('.user-popup'); // Само окно
    let openPopupButton = document.querySelector('.menu-user-panel'); // Кнопка для показа окна
    let userBlock = document.querySelector('.user-box'); // Весь user block (само окно + кнопка для показа)
    let userCheckbox = document.querySelector('.remember-checkbox'); // Чекбокс
    let userLogin = document.querySelector('.input-login-user-popup'); // Поле ввода логина
    let userPassword = document.querySelector('.input-password-user-popup'); // Поле ввода пароля

    let coordsPopupButton = openPopupButton.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftUserBlock = coordsPopupButton.left + (openPopupButton.offsetWidth - popup.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
    let topUserBlock = coordsPopupButton.top + openPopupButton.offsetHeight; // Координаты позиционирования окна по вертикали
    popup.style.left = leftUserBlock + 'px'; // Применение позиционирования окна по горизонтали
    popup.style.top = topUserBlock + 'px'; // Применение позиционирования окна по вертикали

    userBlock.addEventListener('mouseenter', () => {
        popup.classList.add('active');

        if(userLogin != null){
            userLogin.addEventListener('click', () => {
                popup.classList.add('activetwo');
            });
        }

        if(userPassword != null){
            userPassword.addEventListener('click', () => {
                popup.classList.add('activetwo');
            });
        }

        if(userCheckbox != null){
            userCheckbox.addEventListener('click', () => {
                popup.classList.add('activetwo');
            });
        }

        if (popup.classList.contains('activetwo')) {
            document.addEventListener('click', (e) => {
                if (e.target !== userLogin && e.target !== userPassword) {
                    popup.classList.remove('activetwo');
                }
            });
        }

        userBlock.addEventListener('mouseleave', () => {
            popup.classList.remove('active');
        });
    });

//конец userBlock
//начало searchBlock

    let popupSearch = document.querySelector('.menu-search'); // Само окно
    let openSearchPopupButton = document.querySelector('.search-icon-box'); // Кнопка для показа окна
    let headerMenu = document.querySelector('.header-menu'); //Хэдер
    let InputSearch = document.querySelector('.input-search'); //Поле ввода для поиска

    let coordsSearchPopupButton = openSearchPopupButton.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftSearchPopup = coordsSearchPopupButton.left + (openSearchPopupButton.offsetWidth - popupSearch.offsetWidth); // Координаты позиционирования окна по горизонтали
    let topSearchPopup = coordsSearchPopupButton.top + openSearchPopupButton.offsetHeight + (headerMenu.offsetHeight / 2) - 1.5; // Координаты позиционирования окна по вертикали
    popupSearch.style.left = leftSearchPopup + 'px'; // Применение позиционирования окна по горизонтали
    popupSearch.style.top = topSearchPopup + 'px'; // Применение позиционирования окна по вертикали

    const toggleMenu = () => {
        popupSearch.classList.toggle('active');
        InputSearch.focus();
    }

    openSearchPopupButton.addEventListener('click', e => {
        e.stopPropagation();
        toggleMenu();
    });

    document.addEventListener('click', e => {
        let target = e.target;
        let its_SearchMenu = target === popupSearch || popupSearch.contains(target);
        let its_button = target === openSearchPopupButton;
        let menu_is_active = popupSearch.classList.contains('active');

        if (!its_SearchMenu && !its_button && menu_is_active) {
            toggleMenu();
        }
    })
//конец searchBlock

//начало hamburgerBlock

    let popupMobileMenu = document.querySelector('.mobile-menu-box'); // Само окно
    let openMobileMenuButton = document.querySelectorAll('.hamburger-menu'); // Кнопка для показа окна

    let mobileMenuCategories = document.querySelector('.mobile-sub-menu'); // Расширенное меню категорий
    let openMobileSubMenuButton = document.querySelector('.mobile-menu-categories'); // Кнопка для показа категорий

    const toggleMobileMenu = () => {
        popupMobileMenu.classList.toggle('active');
    }

    openMobileMenuButton.forEach((button) => {
        button.addEventListener('click', e => {
            e.stopPropagation();
            toggleMobileMenu();
        });
    });

    const toggleMobileSubMenu = () => {
        mobileMenuCategories.classList.toggle('active');
    }

    openMobileSubMenuButton.addEventListener('click', e => {
        e.stopPropagation();
        toggleMobileSubMenu();
    });
//конец hamburgerBlock

//начало categoryBlock

    let popupCategory = document.querySelector('.widget-menu-category'); // Само окно
    let openPopupCategoryButton = document.querySelector('.menu-category-item'); // Кнопка для показа окна
    let categoryBlock = document.querySelector('.menu-category-item'); // Весь блок (само окно + кнопка для показа)
    let imageCategoryBlock = document.querySelectorAll('.widget-menu-imageframe'); //Картинка в блоке
    let textMenuCategories = document.querySelector('.text-menu-categories'); //Текст кнопки
    let arrowMenuCategories = document.querySelector('.dropdown-category-icon'); //Стрелка


    let coordsPopupCategoryButton = openPopupCategoryButton.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftCategoryBlock = coordsPopupCategoryButton.left + (openPopupCategoryButton.offsetWidth - popupCategory.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
    let topCategoryBlock = coordsPopupCategoryButton.top + openPopupCategoryButton.offsetHeight; // Координаты позиционирования окна по вертикали
    popupCategory.style.left = leftCategoryBlock + 'px'; // Применение позиционирования окна по горизонтали
    popupCategory.style.top = topCategoryBlock + 'px'; // Применение позиционирования окна по вертикали


    openPopupCategoryButton.addEventListener('mouseenter', () => {
        openPopupCategoryButton.classList.add('active');
        textMenuCategories.classList.add('active');
        arrowMenuCategories.classList.add('active');

        openPopupCategoryButton.addEventListener('mouseleave', () => {
            openPopupCategoryButton.classList.remove('active');
            textMenuCategories.classList.remove('active');
            arrowMenuCategories.classList.remove('active');
        });
    });

    categoryBlock.addEventListener('mouseenter', () => {
        popupCategory.classList.add('active');

        categoryBlock.addEventListener('mouseleave', () => {
            popupCategory.classList.remove('active');
        });
    });

    imageCategoryBlock.forEach((img) => {
        img.addEventListener('mouseenter', () => {
            img.classList.add('active');

            img.addEventListener('mouseleave', () => {
                img.classList.remove('active');
            });
        });
    });

//конец categoryBlock

//начало footerTextHover

    let hoverBlock = document.querySelectorAll('.footer-fresh-articles'); // Блок для показа hover'а

    function hoverFooterText(text, icon) {
        text.classList.toggle('active');
        icon.classList.toggle('active');
    }

    hoverBlock.forEach((button) => {
        let footerText = button.querySelector('.text-footer-list')
        let footerDropdown = button.querySelector('.dropdown-footer-icon')
        button.addEventListener('mouseenter', () => {
            hoverFooterText(footerText, footerDropdown);
        });

        button.addEventListener('mouseleave', () => {
            hoverFooterText(footerText, footerDropdown);
        });
    });

//конец footerTextHover

//начало stickyMenu
    //начало searchBlock

    let popupSearchSticky = document.querySelector('.menu-search-sticky'); // Само окно
    let openSearchPopupButtonSticky = document.querySelector('.search-icon-box-sticky'); // Кнопка для показа окна
    document.querySelector('.header-sticky-menu'); //Хэдер
    let InputSearchSticky = document.querySelector('.input-search-sticky'); //Поле ввода для поиска

    let coordsSearchPopupButtonSticky = openSearchPopupButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftSearchPopupSticky = coordsSearchPopupButtonSticky.left + (openSearchPopupButtonSticky.offsetWidth - popupSearchSticky.offsetWidth); // Координаты позиционирования окна по горизонтали
    let topSearchPopupSticky = window.pageYOffset + 75; // Координаты позиционирования окна по вертикали
    popupSearchSticky.style.left = leftSearchPopupSticky + 'px'; // Применение позиционирования окна по горизонтали
    popupSearchSticky.style.top = topSearchPopupSticky + 'px'; // Применение позиционирования окна по вертикали

    const toggleMenuSticky = () => {
        popupSearchSticky.classList.toggle('active');
        InputSearchSticky.focus();
    }

    openSearchPopupButtonSticky.addEventListener('click', e => {
        e.stopPropagation();
        toggleMenuSticky();
    });

    document.addEventListener('click', e => {
        let targetSticky = e.target;
        let its_SearchMenuSticky = targetSticky === popupSearchSticky || popupSearchSticky.contains(targetSticky);
        let its_buttonSticky = targetSticky === openSearchPopupButtonSticky;
        let menu_is_activeSticky = popupSearchSticky.classList.contains('active');

        if (!its_SearchMenuSticky && !its_buttonSticky && menu_is_activeSticky) {
            toggleMenuSticky();
        }
    })
    //конец searchBlock

    let popupCategorySticky = document.querySelector('.widget-menu-category-sticky'); // Само окно
    let openPopupCategoryButtonSticky = document.querySelector('.menu-category-item-sticky'); // Кнопка для показа окна
    let categoryBlockSticky = document.querySelector('.menu-category-item-sticky'); // Весь блок (само окно + кнопка для показа)
    let imageCategoryBlockSticky = document.querySelectorAll('.widget-menu-imageframe-sticky'); //Картинка в блоке
    let textMenuCategoriesSticky = document.querySelector('.text-menu-categories-sticky'); //Текст кнопки
    let arrowMenuCategoriesSticky = document.querySelector('.dropdown-category-icon-sticky'); //Стрелка


    let coordsPopupCategoryButtonSticky = openPopupCategoryButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftCategoryBlockSticky = coordsPopupCategoryButtonSticky.left + (openPopupCategoryButtonSticky.offsetWidth - popupCategorySticky.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
    let topCategoryBlockSticky = coordsPopupCategoryButtonSticky.top + openPopupCategoryButtonSticky.offsetHeight; // Координаты позиционирования окна по вертикали
    popupCategorySticky.style.left = leftCategoryBlockSticky + 'px'; // Применение позиционирования окна по горизонтали
    popupCategorySticky.style.top = topCategoryBlockSticky + 'px'; // Применение позиционирования окна по вертикали


    openPopupCategoryButtonSticky.addEventListener('mouseenter', () => {
        openPopupCategoryButtonSticky.classList.add('active');
        textMenuCategoriesSticky.classList.add('active');
        arrowMenuCategoriesSticky.classList.add('active');

        openPopupCategoryButtonSticky.addEventListener('mouseleave', () => {
            openPopupCategoryButtonSticky.classList.remove('active');
            textMenuCategoriesSticky.classList.remove('active');
            arrowMenuCategoriesSticky.classList.remove('active');
        });
    });

    categoryBlockSticky.addEventListener('mouseenter', () => {
        popupCategorySticky.classList.add('active');

        categoryBlockSticky.addEventListener('mouseleave', () => {
            popupCategorySticky.classList.remove('active');
        });
    });

    imageCategoryBlockSticky.forEach((img) => {
        img.addEventListener('mouseenter', () => {
            img.classList.add('active');

            img.addEventListener('mouseleave', () => {
                img.classList.remove('active');
            });
        });
    });

    window.onscroll = function () {
        toggleStickyMenu()
    };

    let navbar = document.querySelector('.header-sticky-menu'); // Само меню
    let rightStickyMenu = document.querySelector('.menu-right-sticky');
    let toTopMenu = document.querySelector('.to-top-container');

    let sticky = navbar.offsetTop; // Получаем позицию верха меню

    function toggleStickyMenu() {   // Добавляем класс sticky когда прокручиваем ниже позиции верха меню. Удаляем "sticky" когда прокручиваем выше позиции.
        if (window.pageYOffset >= sticky && window.innerWidth > 1050) {

            toTopMenu.classList.add("active")
            navbar.classList.add("sticky")
            rightStickyMenu.classList.add("scroll")

            let coordsSearchPopupButtonSticky = openSearchPopupButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
            let leftSearchPopupSticky = coordsSearchPopupButtonSticky.left + (openSearchPopupButtonSticky.offsetWidth - popupSearchSticky.offsetWidth); // Координаты позиционирования окна по горизонтали
            let topSearchPopupSticky = window.pageYOffset + 75; // Координаты позиционирования окна по вертикали
            popupSearchSticky.style.left = leftSearchPopupSticky + 'px'; // Применение позиционирования окна по горизонтали
            popupSearchSticky.style.top = topSearchPopupSticky + 'px'; // Применение позиционирования окна по вертикали
        } else {
            toTopMenu.classList.remove("active")
            navbar.classList.remove("sticky")
            rightStickyMenu.classList.remove("scroll")

            let coordsSearchPopupButtonSticky = openSearchPopupButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
            let leftSearchPopupSticky = coordsSearchPopupButtonSticky.left + (openSearchPopupButtonSticky.offsetWidth - popupSearchSticky.offsetWidth); // Координаты позиционирования окна по горизонтали
            let topSearchPopupSticky = window.pageYOffset + 127; // Координаты позиционирования окна по вертикали
            popupSearchSticky.style.left = leftSearchPopupSticky + 'px'; // Применение позиционирования окна по горизонтали
            popupSearchSticky.style.top = topSearchPopupSticky + 'px'; // Применение позиционирования окна по вертикали
        }

        coordsPopupCategoryButtonSticky = openPopupCategoryButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftCategoryBlockSticky = coordsPopupCategoryButtonSticky.left + (openPopupCategoryButtonSticky.offsetWidth - popupCategorySticky.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topCategoryBlockSticky = coordsPopupCategoryButtonSticky.top + openPopupCategoryButtonSticky.offsetHeight; // Координаты позиционирования окна по вертикали
        popupCategorySticky.style.left = leftCategoryBlockSticky + 'px'; // Применение позиционирования окна по горизонтали
        popupCategorySticky.style.top = topCategoryBlockSticky + 'px'; // Применение позиционирования окна по вертикали

    }

// конец stickyMenu

// начало resizeBloks

    window.addEventListener('resize', function () {
        let coordsSearchPopupButton = openSearchPopupButton.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftSearchPopup = coordsSearchPopupButton.left + (openSearchPopupButton.offsetWidth - popupSearch.offsetWidth); // Координаты позиционирования окна по горизонтали
        let topSearchPopup = coordsSearchPopupButton.top + openSearchPopupButton.offsetHeight + (headerMenu.offsetHeight / 2) - 1; // Координаты позиционирования окна по вертикали
        popupSearch.style.left = leftSearchPopup + 'px'; // Применение позиционирования окна по горизонтали
        popupSearch.style.top = topSearchPopup + 'px'; // Применение позиционирования окна по вертикали
        popupSearch.style.transition = "none"; // Удаление анимации окна при изменении окна

        coordsPopupButton = openPopupButton.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftUserBlock = coordsPopupButton.left + (openPopupButton.offsetWidth - popup.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topUserBlock = coordsPopupButton.top + openPopupButton.offsetHeight; // Координаты позиционирования окна по вертикали
        popup.style.left = leftUserBlock + 'px'; // Применение позиционирования окна по горизонтали
        popup.style.top = topUserBlock + 'px'; // Применение позиционирования окна по вертикали

        coordsPopupCategoryButton = openPopupCategoryButton.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftCategoryBlock = coordsPopupCategoryButton.left + (openPopupCategoryButton.offsetWidth - popupCategory.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topCategoryBlock = coordsPopupCategoryButton.top + openPopupCategoryButton.offsetHeight; // Координаты позиционирования окна по вертикали
        popupCategory.style.left = leftCategoryBlock + 'px'; // Применение позиционирования окна по горизонтали
        popupCategory.style.top = topCategoryBlock + 'px'; // Применение позиционирования окна по вертикали
        if (leftCategoryBlock < 0) {
            popupCategory.style.left = 0 + 'px';
        }

        let leftCategoryBlockSticky = coordsPopupCategoryButtonSticky.left + (openPopupCategoryButtonSticky.offsetWidth - popupCategorySticky.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topCategoryBlockSticky = coordsPopupCategoryButtonSticky.top + openPopupCategoryButtonSticky.offsetHeight; // Координаты позиционирования окна по вертикали
        popupCategorySticky.style.left = leftCategoryBlockSticky + 'px'; // Применение позиционирования окна по горизонтали
        popupCategorySticky.style.top = topCategoryBlockSticky + 'px'; // Применение позиционирования окна по вертикали
        if (leftCategoryBlockSticky < 0) {
            popupCategorySticky.style.left = 0 + 'px';
        }

        let coordsSearchPopupButtonSticky = openSearchPopupButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftSearchPopupSticky = coordsSearchPopupButtonSticky.left + (openSearchPopupButtonSticky.offsetWidth - popupSearchSticky.offsetWidth); // Координаты позиционирования окна по горизонтали
        let topSearchPopupSticky = window.pageYOffset + 75; // Координаты позиционирования окна по вертикали
        popupSearchSticky.style.left = leftSearchPopupSticky + 'px'; // Применение позиционирования окна по горизонтали
        popupSearchSticky.style.top = topSearchPopupSticky + 'px'; // Применение позиционирования окна по вертикали

    }, false);

//конец resizeBlocks
}