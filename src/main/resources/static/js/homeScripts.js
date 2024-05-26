    let openPopupCategoryButtonSticky = document.querySelector('.menu-category-item-sticky'); // Кнопка для показа окна

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

// начало resizeBloks

    window.addEventListener('resize', function(event) {

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