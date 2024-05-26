
    let registerForm = document.querySelector('.signin-form-success');
    let mainHeader = document.querySelector('.main-header');
    let mainForm = document.querySelector('.login_success-block');
    let shopFooter = document.querySelector('.shop-footer');

    let coordsShopFooter = shopFooter.getBoundingClientRect();

    let heightHeader = mainHeader.offsetHeight;

    let topPaddingRegisterForm =  window.getComputedStyle(registerForm, null).getPropertyValue("padding-top");


    let heightPaddingRegisterForm = parseFloat(topPaddingRegisterForm) + 30;

    let heightRegisterForm = window.innerHeight - heightHeader - heightPaddingRegisterForm;

    registerForm.style.height = heightRegisterForm + 'px'; // Применение позиционирования слайдера по вертикали
        if(registerForm.offsetHeight < 350){
            registerForm.style.height = 350 + 'px';
        }
    let coordsMainForm = mainForm.getBoundingClientRect();
    let coordsRegisterForm = registerForm.getBoundingClientRect();

        if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
            mainForm.style.scale = 0.9;
            mainForm.style.marginTop = -10 + '%';
            coordsRegisterForm = registerForm.getBoundingClientRect();
            coordsMainForm = mainForm.getBoundingClientRect();
            if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                mainForm.style.scale = 0.9;
                mainForm.style.marginTop = -15 + '%';
                coordsRegisterForm = registerForm.getBoundingClientRect();
                coordsMainForm = mainForm.getBoundingClientRect();
                if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                    mainForm.style.scale = 0.8;
                    mainForm.style.marginTop = -20 + '%';
                    coordsRegisterForm = registerForm.getBoundingClientRect();
                    coordsMainForm = mainForm.getBoundingClientRect();
                    if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                        mainForm.style.scale = 0.7;
                        mainForm.style.marginTop = -30 + '%';
                        coordsRegisterForm = registerForm.getBoundingClientRect();
                        coordsMainForm = mainForm.getBoundingClientRect();
                        if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                            mainForm.style.marginTop = -25 + '%';
                            coordsRegisterForm = registerForm.getBoundingClientRect();
                            coordsMainForm = mainForm.getBoundingClientRect();
                            if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                                mainForm.style.scale = 0.6;
                                mainForm.style.marginTop = -30 + '%';
                                coordsRegisterForm = registerForm.getBoundingClientRect();
                                coordsMainForm = mainForm.getBoundingClientRect();
                                if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                                    mainForm.style.marginTop = -45 + '%';
                                    coordsRegisterForm = registerForm.getBoundingClientRect();
                                    coordsMainForm = mainForm.getBoundingClientRect();
                                    if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                                        mainForm.style.scale = 0.5;
                                        mainForm.style.marginTop = -40 + '%';
                                        coordsRegisterForm = registerForm.getBoundingClientRect();
                                        coordsMainForm = mainForm.getBoundingClientRect();
                                        if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                                            mainForm.style.marginTop = -45 + '%';
                                            coordsRegisterForm = registerForm.getBoundingClientRect();
                                            coordsMainForm = mainForm.getBoundingClientRect();
                                            if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                                                mainForm.style.marginTop = -65 + '%';
                                                coordsRegisterForm = registerForm.getBoundingClientRect();
                                                coordsMainForm = mainForm.getBoundingClientRect();
                                                if(coordsMainForm.bottom > coordsRegisterForm.bottom || coordsMainForm.top < coordsRegisterForm.top){
                                                    mainForm.style.scale = 0.45;
                                                    mainForm.style.marginTop = -92 + '%';
                                                    coordsRegisterForm = registerForm.getBoundingClientRect();
                                                    coordsMainForm = mainForm.getBoundingClientRect();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }