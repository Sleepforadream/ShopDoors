window.onload = function () {

//начало tooltipSocial

    initTooltips();

//конец tooltipSocial

//начало contactsRotate

    let contactsBar = document.querySelectorAll('.about-contacts-bar-content');
    let contactsBarBackface = document.querySelectorAll('.about-contacts-bar-content-backface');
    let contactsBarArea = document.querySelectorAll('.about-contacts-bar, .about-contacts-bar-right');

    function activateRotate(numberIndex) {
        contactsBar[numberIndex].classList.add('active');
        contactsBarBackface[numberIndex].classList.add('active');
    }

    for (let i = 0; i < contactsBarArea.length; i++) {
        contactsBarArea[i].addEventListener('mouseenter', e => {
            contactsBar[i].classList.add('active');
            contactsBarBackface[i].classList.add('active');
        })
        contactsBarArea[i].addEventListener('mouseleave', e => {
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

//начало userBlock

    let {popup, openPopupButton, userBlock, userCheckbox, userLogin, userPassword} = positionPopup();

    userBlock.addEventListener('mouseenter', (e) => {
        popup.classList.add('active');

        userLogin.addEventListener('click', (e) => {
            popup.classList.add('activetwo');
        });

        userPassword.addEventListener('click', (e) => {
            popup.classList.add('activetwo');
        });

        userCheckbox.addEventListener('click', (e) => {
            popup.classList.add('activetwo');
        });

        if (popup.classList.contains('activetwo')) {
            document.addEventListener('click', (e) => {
                if (e.target !== userLogin && e.target !== userPassword) {
                    popup.classList.remove('activetwo');
                }
            });
        }

        userBlock.addEventListener('mouseleave', (e) => {
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
    let openMobileMenuButton = document.querySelector('.hamburger-menu'); // Кнопка для показа окна

    let mobileMenuCategories = document.querySelector('.mobile-sub-menu'); // Расширенное меню категорий
    let openMobileSubMenuButton = document.querySelector('.indicator-dropdown-medium'); // Кнопка для показа категорий

    const toggleMobileMenu = () => {
        popupMobileMenu.classList.toggle('active');
    }

    openMobileMenuButton.addEventListener('click', e => {
        e.stopPropagation();
        toggleMobileMenu();
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


    openPopupCategoryButton.addEventListener('mouseenter', (e) => {
        openPopupCategoryButton.classList.add('active');
        textMenuCategories.classList.add('active');
        arrowMenuCategories.classList.add('active');

        openPopupCategoryButton.addEventListener('mouseleave', (e) => {
            openPopupCategoryButton.classList.remove('active');
            textMenuCategories.classList.remove('active');
            arrowMenuCategories.classList.remove('active');
        });
    });

    categoryBlock.addEventListener('mouseenter', (e) => {
        popupCategory.classList.add('active');

        categoryBlock.addEventListener('mouseleave', (e) => {
            popupCategory.classList.remove('active');
        });
    });

    imageCategoryBlock.forEach((img) => {
        img.addEventListener('mouseenter', (e) => {
            img.classList.add('active');

            img.addEventListener('mouseleave', (e) => {
                img.classList.remove('active');
            });
        });
    });

//конец categoryBlock

//начало footerTextHover

    let hoverBlock = document.querySelectorAll('.footer-fresh-articles'); // Блок для показа ховера

    function hoverFooterText(text,icon) {
        text.classList.toggle('active');
        icon.classList.toggle('active');
    }

    hoverBlock.forEach((button) => {
        let footerText = button.querySelector('.text-footer-list')
        let footerDropdown = button.querySelector('.dropdown-footer-icon')
        button.addEventListener('mouseenter', e => {
            hoverFooterText(footerText,footerDropdown);
        });

        button.addEventListener('mouseleave', e => {
                hoverFooterText(footerText,footerDropdown);
        });
    });

//конец footerTextHover

//начало stickyMenu
    //начало searchBlock

    let popupSearchSticky = document.querySelector('.menu-search-sticky'); // Само окно
    let openSearchPopupButtonSticky = document.querySelector('.search-icon-box-sticky'); // Кнопка для показа окна
    let headerMenuSticky = document.querySelector('.header-sticky-menu'); //Хэдер
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


    openPopupCategoryButtonSticky.addEventListener('mouseenter', (e) => {
        openPopupCategoryButtonSticky.classList.add('active');
        textMenuCategoriesSticky.classList.add('active');
        arrowMenuCategoriesSticky.classList.add('active');

        openPopupCategoryButtonSticky.addEventListener('mouseleave', (e) => {
            openPopupCategoryButtonSticky.classList.remove('active');
            textMenuCategoriesSticky.classList.remove('active');
            arrowMenuCategoriesSticky.classList.remove('active');
        });
    });

    categoryBlockSticky.addEventListener('mouseenter', (e) => {
        popupCategorySticky.classList.add('active');

        categoryBlockSticky.addEventListener('mouseleave', (e) => {
            popupCategorySticky.classList.remove('active');
        });
    });

    imageCategoryBlockSticky.forEach((img) => {
        img.addEventListener('mouseenter', (e) => {
            img.classList.add('active');

            img.addEventListener('mouseleave', (e) => {
                img.classList.remove('active');
            });
        });
    });

    window.onscroll = function() {toggleStickyMenu()};

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

      let coordsPopupCategoryButtonSticky = openPopupCategoryButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
      let leftCategoryBlockSticky = coordsPopupCategoryButtonSticky.left + (openPopupCategoryButtonSticky.offsetWidth - popupCategorySticky.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
      let topCategoryBlockSticky = coordsPopupCategoryButtonSticky.top + openPopupCategoryButtonSticky.offsetHeight; // Координаты позиционирования окна по вертикали
      popupCategorySticky.style.left = leftCategoryBlockSticky + 'px'; // Применение позиционирования окна по горизонтали
      popupCategorySticky.style.top = topCategoryBlockSticky + 'px'; // Применение позиционирования окна по вертикали

    }

// конец stickyMenu

//начало setHeightSlider

    let sliderContainer = document.querySelectorAll('.sliders-container'); // Блок со слайдами
    let secondHeaderMenu = document.querySelector('.second-header-menu'); // Второе меню
    //Первое меню было объявлено выше
    let mobileMenu = document.querySelector('.mobile-menu-box'); //Мобильное меню

    let coordsSecondHeaderMenu = openPopupCategoryButtonSticky.clientHeight; //Высота второго меню
    let coordsHeaderMenu = openPopupCategoryButtonSticky.clientHeight; //Высота первого меню
    let coordsMobileMenu = openPopupCategoryButtonSticky.clientHeight; //Высота мобильного меню

    let heightHeader = coordsSecondHeaderMenu + coordsHeaderMenu;

    let heightSliderContainer = window.innerHeight - heightHeader + 4;

    sliderContainer.forEach((slide) => {
        slide.style.height = heightSliderContainer + 'px'; // Применение позиционирования слайдера по вертикали
    });



    if(window.innerWidth < 1050){
            let secondMobileMenu = document.querySelector('.second-header-menu');
            let headerMobileMenu = document.querySelector('.header-menu');

            let coordsSecondMobileMenu = openPopupCategoryButtonSticky.clientHeight;
            let coordsHeaderMobileMenu = openPopupCategoryButtonSticky.clientHeight;

            let heightHeader = 172;
            //let heightHeader = (coordsSecondMobileMenu * 2) + coordsHeaderMobileMenu;
            let heightSliderContainer = window.innerHeight - heightHeader + 4;
            sliderContainer.forEach((slide) => {
                slide.style.height = heightSliderContainer + 'px'; // Применение позиционирования слайдера по вертикали
            });
        }

//конец setHeightSlider

//начало sliderCarousel

    let slidersContainer = document.querySelector('.sliders-container');
    let buttonNextSlider = document.querySelector('.flex-next'); // Кнопка переключения на следующий слайд
    let buttonPrevSlider = document.querySelector('.flex-prev'); // Кнопка переключения на предыдущий слайд
    let slide1 = document.querySelector('.slides'); // Слайд 1
    let slide2 = document.querySelector('.slides2'); // Слайд 2
    let slide3 = document.querySelector('.slides3'); // Слайд 3

    const nextActiveSlide = () => {
        if (slide1.classList.contains('active')){
            slide1.classList.toggle('active');
            slide2.classList.toggle('active');
        }
        else if (slide2.classList.contains('active')){
            slide2.classList.toggle('active');
            slide3.classList.toggle('active');
        }
        else if (slide3.classList.contains('active')){
            slide3.classList.toggle('active');
            slide1.classList.toggle('active');
        }
    }

    buttonNextSlider.addEventListener('click', e => {
        e.stopPropagation();
        nextActiveSlide();
    });

    const prevActiveSlide = () => {
        if (slide1.classList.contains('active')){
            slide1.classList.toggle('active');
            slide3.classList.toggle('active');
        }
        else if (slide2.classList.contains('active')){
            slide2.classList.toggle('active');
            slide1.classList.toggle('active');
        }
        else if (slide3.classList.contains('active')){
            slide3.classList.toggle('active');
            slide2.classList.toggle('active');
        }
    }

    buttonPrevSlider.addEventListener('click', e => {
        e.stopPropagation();
        prevActiveSlide();
    });

    let timerSlide = setInterval(() => nextActiveSlide(), 5000);

    slidersContainer.addEventListener('mouseenter', e => {
        clearInterval(timerSlide);
    });

    slidersContainer.addEventListener('mouseleave', e => {
        timerSlide = setInterval(() => nextActiveSlide(), 5000);
    });

//конец sliderCarousel

//начало tooltipGuaranties

    let popoverGuaranties = document.querySelector('.popover-guaranties');
    let popoverGuaranties2 = document.querySelector('.popover-guaranties2');
    let buttonImageGuaranties = document.querySelector('.button-image-guaranties'); // Кнопка переключения на следующий слайд
    let buttonImageGuaranties2 = document.querySelector('.button-image-guaranties2'); // Кнопка переключения на предыдущий слайд
    let popupTitleGuaranties = document.querySelectorAll('.popover-title'); // Кнопка переключения на следующий слайд
    let popupTextGuaranties = document.querySelectorAll('.popover-content'); // Кнопка переключения на предыдущий слайд
    let arrowGuaranties = document.querySelectorAll('.arrow');

    //позиционирование первого окна с всплывающей информацией
    let coordsPopoverButton = buttonImageGuaranties.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftPopoverGuaranties = coordsPopoverButton.left + buttonImageGuaranties.offsetWidth; // Координаты позиционирования окна по горизонтали
    let topPopoverGuaranties = coordsPopoverButton.top - (popoverGuaranties.offsetHeight / 2 - buttonImageGuaranties.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties.style.left = leftPopoverGuaranties + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties.style.top = topPopoverGuaranties + 'px'; // Применение позиционирования окна по вертикали
    //позиционирование второго окна с всплывающей информацией
    let coordsPopoverButton2 = buttonImageGuaranties2.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftPopoverGuaranties2 = coordsPopoverButton2.left + buttonImageGuaranties2.offsetWidth; // Координаты позиционирования окна по горизонтали
    let topPopoverGuaranties2 = coordsPopoverButton2.top - (popoverGuaranties2.offsetHeight / 2 - buttonImageGuaranties2.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties2.style.left = leftPopoverGuaranties2 + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties2.style.top = topPopoverGuaranties2 + 'px'; // Применение позиционирования окна по вертикали

    if(window.innerWidth > 800 && popoverGuaranties2.offsetWidth < 200){
        popupTitleGuaranties.forEach((title) => {
            title.style.fontSize = 10 + "px";
        });
        popupTextGuaranties.forEach((text) => {
            text.style.fontSize = 10 + "px";
        });
        popoverGuaranties2.style.width = 13 + "vw";
    }

    coordsPopoverButton = buttonImageGuaranties.getBoundingClientRect(); //Координаты кнопки показа окна
    leftPopoverGuaranties = coordsPopoverButton.left + buttonImageGuaranties.offsetWidth; // Координаты позиционирования окна по горизонтали
    topPopoverGuaranties = coordsPopoverButton.top - (popoverGuaranties.offsetHeight / 2 - buttonImageGuaranties.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties.style.left = leftPopoverGuaranties + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties.style.top = topPopoverGuaranties + 'px'; // Применение позиционирования окна по вертикали

    coordsPopoverButton2 = buttonImageGuaranties2.getBoundingClientRect(); //Координаты кнопки показа окна
    leftPopoverGuaranties2 = coordsPopoverButton2.left + buttonImageGuaranties2.offsetWidth; // Координаты позиционирования окна по горизонтали
    topPopoverGuaranties2 = coordsPopoverButton2.top - (popoverGuaranties2.offsetHeight / 2 - buttonImageGuaranties2.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties2.style.left = leftPopoverGuaranties2 + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties2.style.top = topPopoverGuaranties2 + 'px'; // Применение позиционирования окна по вертикали

    if(window.innerWidth < 800 && window.innerWidth > 500){
        popoverGuaranties.style.width = 70 + "vw";
        popoverGuaranties2.style.width = 70 + "vw";

        coordsPopoverButton = buttonImageGuaranties.getBoundingClientRect(); //Координаты кнопки показа окна
        leftPopoverGuaranties = coordsPopoverButton.left + (popoverGuaranties.offsetWidth / 2 - buttonImageGuaranties.offsetHeight / 2); // Координаты позиционирования окна по горизонтали
        topPopoverGuaranties = coordsPopoverButton.top - (popoverGuaranties.offsetHeight / 2 - buttonImageGuaranties.offsetHeight / 2); // Координаты позиционирования окна по вертикали
        popoverGuaranties.style.left = leftPopoverGuaranties + 'px'; // Применение позиционирования окна по горизонтали
        popoverGuaranties.style.top = topPopoverGuaranties + 'px'; // Применение позиционирования окна по вертикали

        coordsPopoverButton2 = buttonImageGuaranties2.getBoundingClientRect(); //Координаты кнопки показа окна
        leftPopoverGuaranties2 = coordsPopoverButton2.left + (popoverGuaranties2.offsetWidth / 2 - buttonImageGuaranties2.offsetHeight / 2); // Координаты позиционирования окна по горизонтали
        topPopoverGuaranties2 = coordsPopoverButton2.top - (popoverGuaranties2.offsetHeight / 2 - buttonImageGuaranties2.offsetHeight / 2); // Координаты позиционирования окна по вертикали
        popoverGuaranties2.style.left = leftPopoverGuaranties2 + 'px'; // Применение позиционирования окна по горизонтали
        popoverGuaranties2.style.top = topPopoverGuaranties2 + 'px'; // Применение позиционирования окна по вертикали
    }

    if (window.innerWidth < 500){
        popoverGuaranties.style.width = 80 + "vw";
        popoverGuaranties2.style.width = 80 + "vw";

        arrowGuaranties.forEach((arrow) => {
            arrow.classList.toggle('active');
        });

        coordsPopoverButton = buttonImageGuaranties.getBoundingClientRect(); //Координаты кнопки показа окна
        leftPopoverGuaranties = (window.innerWidth / 2) - (popoverGuaranties.offsetWidth / 2) ; // Координаты позиционирования окна по горизонтали
        topPopoverGuaranties = coordsPopoverButton.top - popoverGuaranties.offsetHeight - 10; // Координаты позиционирования окна по вертикали
        popoverGuaranties.style.left = leftPopoverGuaranties + 'px'; // Применение позиционирования окна по горизонтали
        popoverGuaranties.style.top = topPopoverGuaranties + 'px'; // Применение позиционирования окна по вертикали

        coordsPopoverButton2 = buttonImageGuaranties2.getBoundingClientRect(); //Координаты кнопки показа окна
        leftPopoverGuaranties2 = (window.innerWidth / 2) - (popoverGuaranties2.offsetWidth / 2) ; // Координаты позиционирования окна по горизонтали
        topPopoverGuaranties2 = coordsPopoverButton2.top - popoverGuaranties2.offsetHeight - 10; // Координаты позиционирования окна по вертикали
        popoverGuaranties2.style.left = leftPopoverGuaranties2 + 'px'; // Применение позиционирования окна по горизонтали
        popoverGuaranties2.style.top = topPopoverGuaranties2 + 'px'; // Применение позиционирования окна по вертикали
    }

    buttonImageGuaranties.addEventListener('click', e => {
        popoverGuaranties.classList.toggle('active');
    });

    buttonImageGuaranties2.addEventListener('click', e => {
        popoverGuaranties2.classList.toggle('active');
    });

    document.addEventListener('click', e => {
        let targetGuaranties = e.target;
        let its_popupGuaranties = targetGuaranties === popoverGuaranties || popoverGuaranties.contains(targetGuaranties);
        let its_buttonGuaranties = targetGuaranties === buttonImageGuaranties;
        let popup_is_activeGuaranties = popoverGuaranties.classList.contains('active');

        if (!its_popupGuaranties && !its_buttonGuaranties && popup_is_activeGuaranties) {
            popoverGuaranties.classList.toggle('active');
        }
    })

    document.addEventListener('click', e => {
        let targetGuaranties2 = e.target;
        let its_popupGuaranties2 = targetGuaranties2 === popoverGuaranties2 || popoverGuaranties2.contains(targetGuaranties2);
        let its_buttonGuaranties2 = targetGuaranties2 === buttonImageGuaranties2;
        let popup_is_activeGuaranties2 = popoverGuaranties2.classList.contains('active');

        if (!its_popupGuaranties2 && !its_buttonGuaranties2 && popup_is_activeGuaranties2) {
            popoverGuaranties2.classList.toggle('active');
        }
    })



//конец tooltipGuaranties

// начало sliderReview

    let buttonSliderReview = document.querySelector('.slider-button-review');
    let buttonSliderReview2 = document.querySelector('.slider-button-review2');
    let avatarImageReview = document.querySelector('.avatar-image-review'); // Кнопка переключения на следующий слайд
    let avatarImageReview2 = document.querySelector('.avatar-image-review2'); // Кнопка переключения на следующий слайд
    let titleHeadingReview = document.querySelector('.title-heading-review'); // Кнопка переключения на предыдущий слайд
    let titleHeadingReview2 = document.querySelector('.title-heading-review2'); // Кнопка переключения на предыдущий слайд
    let subtitleHeadingReview = document.querySelector('.subtitle-heading-review'); // Кнопка переключения на следующий слайд
    let subtitleHeadingReview2 = document.querySelector('.subtitle-heading-review2'); // Кнопка переключения на следующий слайд
    let sliderReviewBox = document.querySelector('.slider-review-box');

    buttonSliderReview.addEventListener('click', e => {
        activeSlideReview1();
    });

    buttonSliderReview2.addEventListener('click', e => {
        activeSlideReview2();
    });

    const activeSlideReview1 = () => {
        buttonSliderReview.classList.add('active');
        buttonSliderReview2.classList.remove('active');
        avatarImageReview.classList.add('active');
        avatarImageReview2.classList.remove('active');
        titleHeadingReview.classList.add('active');
        titleHeadingReview2.classList.remove('active');
        subtitleHeadingReview.classList.add('active');
        subtitleHeadingReview2.classList.remove('active');
    };

    const activeSlideReview2 = () => {
        buttonSliderReview2.classList.add('active');
        buttonSliderReview.classList.remove('active');
        avatarImageReview2.classList.add('active');
        avatarImageReview.classList.remove('active');
        titleHeadingReview2.classList.add('active');
        titleHeadingReview.classList.remove('active');
        subtitleHeadingReview2.classList.add('active');
        subtitleHeadingReview.classList.remove('active');
    };

    const nextActiveSlideReview = () => {
        if(buttonSliderReview.classList.contains('active')) {
            activeSlideReview2();
        }
        else if (buttonSliderReview2.classList.contains('active')){
            activeSlideReview1();
        }
    };

    let timerSlideReview = setInterval(() => nextActiveSlideReview(), 5000);

    sliderReviewBox.addEventListener('mouseenter', e => {
        clearInterval(timerSlideReview);
    });

    sliderReviewBox.addEventListener('mouseleave', e => {
        timerSlideReview = setInterval(() => nextActiveSlideReview(), 5000);
    });


// конец sliderReview

//начало carouselBrands

    let carouselIcons = document.querySelectorAll('.carousel-icon');
    let carouselArea = document.querySelector('.our-brands-carousel');

    //let timerCarouselIcons = setInterval(() => nextActiveCarouselIcon(), 5000);

    /*const nextActiveCarouselIcon = () => {
        carouselIcons.forEach((icon) => {
            setTimeout(() => icon.remove(), 5000);
        })
    }

    nextActiveCarouselIcon();*/

//конец carouselBrands

//начало contactsRotate

//    let contactsBar = document.querySelectorAll('.about-contacts-bar-content');
//    let contactsBarBackface = document.querySelector('.about-contacts-bar-content-backface');
//
//    contactsBar.addEventListener('click', e => {
//        contactsBar.classList.add('active');
//    });

//конец contactsRotate

// начало resizeBloks

    window.addEventListener('resize', function(event) {
        let coordsSearchPopupButton = openSearchPopupButton.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftSearchPopup = coordsSearchPopupButton.left + (openSearchPopupButton.offsetWidth - popupSearch.offsetWidth); // Координаты позиционирования окна по горизонтали
        let topSearchPopup = coordsSearchPopupButton.top + openSearchPopupButton.offsetHeight + (headerMenu.offsetHeight / 2) - 1; // Координаты позиционирования окна по вертикали
        popupSearch.style.left = leftSearchPopup + 'px'; // Применение позиционирования окна по горизонтали
        popupSearch.style.top = topSearchPopup + 'px'; // Применение позиционирования окна по вертикали
        popupSearch.style.transition = "none"; // Удаление анимации окна при изменении окна

        let coordsPopupButton = openPopupButton.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftUserBlock = coordsPopupButton.left + (openPopupButton.offsetWidth - popup.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topUserBlock = coordsPopupButton.top + openPopupButton.offsetHeight; // Координаты позиционирования окна по вертикали
        popup.style.left = leftUserBlock + 'px'; // Применение позиционирования окна по горизонтали
        popup.style.top = topUserBlock + 'px'; // Применение позиционирования окна по вертикали

        let coordsPopupCategoryButton = openPopupCategoryButton.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftCategoryBlock = coordsPopupCategoryButton.left + (openPopupCategoryButton.offsetWidth - popupCategory.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topCategoryBlock = coordsPopupCategoryButton.top + openPopupCategoryButton.offsetHeight; // Координаты позиционирования окна по вертикали
        popupCategory.style.left = leftCategoryBlock + 'px'; // Применение позиционирования окна по горизонтали
        popupCategory.style.top = topCategoryBlock + 'px'; // Применение позиционирования окна по вертикали
        if (leftCategoryBlock < 0){
            popupCategory.style.left = 0 + 'px';
        }

        let coordsPopupCategoryButtonSticky = openPopupCategoryButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftCategoryBlockSticky = coordsPopupCategoryButtonSticky.left + (openPopupCategoryButtonSticky.offsetWidth - popupCategorySticky.offsetWidth) / 2; // Координаты позиционирования окна по горизонтали
        let topCategoryBlockSticky = coordsPopupCategoryButtonSticky.top + openPopupCategoryButtonSticky.offsetHeight; // Координаты позиционирования окна по вертикали
        popupCategorySticky.style.left = leftCategoryBlockSticky + 'px'; // Применение позиционирования окна по горизонтали
        popupCategorySticky.style.top = topCategoryBlockSticky + 'px'; // Применение позиционирования окна по вертикали
        if (leftCategoryBlockSticky < 0){
            popupCategorySticky.style.left = 0 + 'px';
        }

        let coordsSearchPopupButtonSticky = openSearchPopupButtonSticky.getBoundingClientRect(); //Координаты кнопки показа окна
        let leftSearchPopupSticky = coordsSearchPopupButtonSticky.left + (openSearchPopupButtonSticky.offsetWidth - popupSearchSticky.offsetWidth); // Координаты позиционирования окна по горизонтали
        let topSearchPopupSticky = window.pageYOffset + 75; // Координаты позиционирования окна по вертикали
        popupSearchSticky.style.left = leftSearchPopupSticky + 'px'; // Применение позиционирования окна по горизонтали
        popupSearchSticky.style.top = topSearchPopupSticky + 'px'; // Применение позиционирования окна по вертикали

        //изменение размера контейнера со слайдами при изменении размера окна
        let coordsSecondHeaderMenu = openPopupCategoryButtonSticky.clientHeight;
        let coordsHeaderMenu = openPopupCategoryButtonSticky.clientHeight;
        let coordsMobileMenu = openPopupCategoryButtonSticky.clientHeight;
        let heightHeader = coordsSecondHeaderMenu + coordsHeaderMenu;
        let heightSliderContainer = window.innerHeight - heightHeader + 4;
        sliderContainer.forEach((slide) => {
            slide.style.height = heightSliderContainer + 'px'; // Применение позиционирования слайдера по вертикали
        });

        //изменение размера текста контейнера со слайдами при изменении размера окна
        if(window.innerWidth < 1050){
            let secondMobileMenu = document.querySelector('.second-header-menu');
            let headerMobileMenu = document.querySelector('.header-menu');

            let coordsSecondMobileMenu = openPopupCategoryButtonSticky.clientHeight;
            let coordsHeaderMobileMenu = openPopupCategoryButtonSticky.clientHeight;

            let heightHeader = 172;
            //let heightHeader = (coordsSecondMobileMenu * 2) + coordsHeaderMobileMenu;
            let heightSliderContainer = window.innerHeight - heightHeader + 4;
            sliderContainer.forEach((slide) => {
                slide.style.height = heightSliderContainer + 'px'; // Применение позиционирования слайдера по вертикали
            });
        }

    let coordsPopoverButton = buttonImageGuaranties.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftPopoverGuaranties = coordsPopoverButton.left + buttonImageGuaranties.offsetWidth; // Координаты позиционирования окна по горизонтали
    let topPopoverGuaranties = coordsPopoverButton.top - (popoverGuaranties.offsetHeight / 2 - buttonImageGuaranties.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties.style.left = leftPopoverGuaranties + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties.style.top = topPopoverGuaranties + 'px'; // Применение позиционирования окна по вертикали

    let coordsPopoverButton2 = buttonImageGuaranties2.getBoundingClientRect(); //Координаты кнопки показа окна
    let leftPopoverGuaranties2 = coordsPopoverButton2.left + buttonImageGuaranties2.offsetWidth; // Координаты позиционирования окна по горизонтали
    let topPopoverGuaranties2 = coordsPopoverButton2.top - (popoverGuaranties2.offsetHeight / 2 - buttonImageGuaranties2.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties2.style.left = leftPopoverGuaranties2 + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties2.style.top = topPopoverGuaranties2 + 'px'; // Применение позиционирования окна по вертикали

    if(popoverGuaranties2.offsetWidth < 200){
        popupTitleGuaranties.forEach((title) => {
            title.style.fontSize = 10 + "px";
        });
        popupTextGuaranties.forEach((text) => {
            text.style.fontSize = 10 + "px";
        });
        popoverGuaranties2.style.width = 13 + "vw";
    }

    coordsPopoverButton = buttonImageGuaranties.getBoundingClientRect(); //Координаты кнопки показа окна
    leftPopoverGuaranties = coordsPopoverButton.left + buttonImageGuaranties.offsetWidth; // Координаты позиционирования окна по горизонтали
    topPopoverGuaranties = coordsPopoverButton.top - (popoverGuaranties.offsetHeight / 2 - buttonImageGuaranties.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties.style.left = leftPopoverGuaranties + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties.style.top = topPopoverGuaranties + 'px'; // Применение позиционирования окна по вертикали

    coordsPopoverButton2 = buttonImageGuaranties2.getBoundingClientRect(); //Координаты кнопки показа окна
    leftPopoverGuaranties2 = coordsPopoverButton2.left + buttonImageGuaranties2.offsetWidth; // Координаты позиционирования окна по горизонтали
    topPopoverGuaranties2 = coordsPopoverButton2.top - (popoverGuaranties2.offsetHeight / 2 - buttonImageGuaranties2.offsetHeight / 2); // Координаты позиционирования окна по вертикали
    popoverGuaranties2.style.left = leftPopoverGuaranties2 + 'px'; // Применение позиционирования окна по горизонтали
    popoverGuaranties2.style.top = topPopoverGuaranties2 + 'px'; // Применение позиционирования окна по вертикали

    }, false);

//конец resizeBlocks


}

