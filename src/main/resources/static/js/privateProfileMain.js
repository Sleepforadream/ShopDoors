let profileBox = document.querySelector('.profile-box');
let mainHeader = document.querySelector('.main-header');
let profileInfoBox = document.querySelector('.profile-info-box');
let shopFooter = document.querySelector('.shop-footer');
let secondMenu = document.querySelector('.second-header-menu');

document.addEventListener('DOMContentLoaded', function () {
    const imgProfileAdd = document.getElementById('imgProfileAdd');
    const profileImage = document.getElementById('profileImage');
    const imgProfileRemove = document.getElementById('imgProfileRemove');
    const imgProfileName = document.getElementById('imgProfileName');
    const defaultImage = '/img/users_photo/unknownUser.svg';

    imgProfileAdd.addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                profileImage.src = e.target.result;
                imgProfileName.value = file.name;
            }
            reader.readAsDataURL(file);
        }
    });

    imgProfileRemove.addEventListener('click', function () {
        profileImage.src = defaultImage;
        imgProfileAdd.value = '';
        imgProfileName.value = 'unknownUser.svg';
    });
});

secondMenu.style.display = "none";

let coordsShopFooter = shopFooter.getBoundingClientRect();

let heightHeader = mainHeader.offsetHeight;

let heightProfileBox = window.innerHeight - heightHeader;

profileBox.style.height = heightProfileBox + 'px';

let coordsProfileInfoBox = profileInfoBox.getBoundingClientRect();
let coordsProfileBox = profileBox.getBoundingClientRect();

if (coordsProfileInfoBox.bottom > coordsProfileBox.bottom || coordsProfileInfoBox.top < coordsProfileBox.top) {

    profileBox.style.height = 450 + 'px';

    //let coordsRegisterForm = profileBox.getBoundingClientRect();

}
