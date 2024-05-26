let userImgProfile = document.querySelector('.user-img-profile');
let feedbackFile = document.getElementById("imgProfileAdd");
let feedbackLabelCancel = document.querySelector('.feedback__label.cancel');
let secondMenu = document.querySelector('.second-header-menu');

secondMenu.style.display = "none";

imgProfileAdd.onchange = evt => {
  const [file] = imgProfileAdd.files
  if (file) {
    userImgProfile.src = URL.createObjectURL(file)
    feedbackLabelCancel.style.display = "block";
    document.getElementById("textLabel").innerHTML = "Изменить фото";
  }
}

feedbackLabelCancel.addEventListener('click', e => {
    userImgProfile.src = "../static/img/users_photo/unknownUser.svg"
    feedbackFile.value = "";
    feedbackLabelCancel.style.display = "none";
    document.getElementById("textLabel").innerHTML = "Добавить фото";
});


let inputNickName = document.getElementById("input_nickname");
let errorInputNickName = document.getElementById("error-nickName");

inputNickName.addEventListener('focus', e => {
    errorInputNickName.style.display = "block";
});

inputNickName.addEventListener('blur', e => {
    errorInputNickName.style.display = "none";
});