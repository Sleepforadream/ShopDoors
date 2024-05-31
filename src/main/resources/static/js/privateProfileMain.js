    let profileBox = document.querySelector('.profile-box');
    let mainHeader = document.querySelector('.main-header');
    let profileInfoBox = document.querySelector('.profile-info-box');
    let shopFooter = document.querySelector('.shop-footer');
    let secondMenu = document.querySelector('.second-header-menu');

    document.addEventListener('DOMContentLoaded', function () {
        const imgProfileAdd = document.getElementById('imgProfileAdd');
        const profileImage = document.getElementById('profileImage');

        imgProfileAdd.addEventListener('change', function () {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    profileImage.src = e.target.result;
                }
                reader.readAsDataURL(file);
            }
        });
    });

    secondMenu.style.display = "none";

    let coordsShopFooter = shopFooter.getBoundingClientRect();

    let heightHeader = mainHeader.offsetHeight;

    let heightProfileBox = window.innerHeight - heightHeader;

    profileBox.style.height = heightProfileBox + 'px'; // Применение позиционирования слайдера по вертикал

    let coordsProfileInfoBox = profileInfoBox.getBoundingClientRect();
    let coordsProfileBox = profileBox.getBoundingClientRect();

    if(coordsProfileInfoBox.bottom > coordsProfileBox.bottom || coordsProfileInfoBox.top < coordsProfileBox.top){

    profileBox.style.height = 450 + 'px';

    //let coordsRegisterForm = profileBox.getBoundingClientRect();

    }
