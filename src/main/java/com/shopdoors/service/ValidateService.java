package com.shopdoors.service;

import com.shopdoors.dto.MeasurementDto;
import com.shopdoors.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidateService {
    private final AuthenticationManager authenticationManager;

    public Map<String, Boolean> validateRegistrationFields(
            String nickName,
            String email,
            String password,
            String passwordDouble
    ) {
        log.info("Validating registration fields");
        Map<String, Boolean> validFields = new HashMap<>();

        Map<String, Boolean> nickNameValidation = validateCorrectLogin(nickName);
        if (nickNameValidation.containsValue(false)) return nickNameValidation;

        Map<String, Boolean> emailValidation = validateCorrectEmail(email);
        if (emailValidation.containsValue(false)) return emailValidation;

        Map<String, Boolean> passwordValidation = validateCorrectPassword(password);
        if (passwordValidation.containsValue(false)) return passwordValidation;

        Map<String, Boolean> passwordDoubleValidation = validateDoublePassword(password, passwordDouble);
        if (passwordDoubleValidation.containsValue(false)) return validFields;

        validFields.put("Все поля валидны", true);
        log.info("Validating registration fields completed");
        return validFields;
    }

    public Map<String, Boolean> validateLoginFields(String email, String password) {
        log.info("Validating login fields");
        Map<String, Boolean> validFields = new HashMap<>();

        Map<String, Boolean> emailValidation = validateCorrectEmail(email);
        if (emailValidation.containsValue(false)) return emailValidation;

        Map<String, Boolean> passwordValidation = validateSuccessPassword(email, password);
        if (passwordValidation.containsValue(false)) return passwordValidation;

        validFields.put("Все поля валидны", true);
        log.info("Validating login fields completed");
        return validFields;
    }

    public Map<String, Boolean> validateProfileFields(ProfileDto profileDto) {
        log.info("Validating profile fields");
        Map<String, Boolean> validFields = new HashMap<>();

        if (!profileDto.getInfo().isEmpty()) {
            Map<String, Boolean> infoSizeValidation = validateMaxSizeFields(profileDto.getInfo(), "info", 512);
            if (infoSizeValidation.containsValue(false)) return infoSizeValidation;
        }

        Map<String, Boolean> nickNameCorrectValidation = validateCorrectName(profileDto.getFirstName(), "nickName");
        if (nickNameCorrectValidation.containsValue(false)) return nickNameCorrectValidation;

        if (!profileDto.getFirstName().isEmpty()) {
            Map<String, Boolean> firstNameCorrectValidation = validateCorrectName(profileDto.getFirstName(), "firstName");
            if (firstNameCorrectValidation.containsValue(false)) return firstNameCorrectValidation;
        }

        if (!profileDto.getSecondName().isEmpty()) {
            Map<String, Boolean> secondNameCorrectValidation = validateCorrectName(profileDto.getSecondName(), "secondName");
            if (secondNameCorrectValidation.containsValue(false)) return secondNameCorrectValidation;
        }

        if (!profileDto.getThirdName().isEmpty()) {
            Map<String, Boolean> thirdNameCorrectValidation = validateCorrectName(profileDto.getThirdName(), "thirdName");
            if (thirdNameCorrectValidation.containsValue(false)) return thirdNameCorrectValidation;
        }

        if (!profileDto.getBirthDate().isEmpty()) {
            Map<String, Boolean> birthDateValidation = validateBirthDate(profileDto.getBirthDate());
            if (birthDateValidation.containsValue(false)) return birthDateValidation;
        }

        if (!profileDto.getAddress().isEmpty()) {
            Map<String, Boolean> addressCorrectValidation = validateCorrectAddress(profileDto.getAddress());
            if (addressCorrectValidation.containsValue(false)) return addressCorrectValidation;
        }

        validFields.put("Все поля валидны", true);
        log.info("Validating profile fields completed");
        return validFields;
    }

    public Map<String, Boolean> validateSecurityFields(
            String phone,
            String email,
            String currentEmail,
            String oldPassword,
            String newPassword,
            String againPassword
    ) {
        log.info("Validating security fields");
        Map<String, Boolean> validFields = new HashMap<>();

        if (!phone.isEmpty()) {
            Map<String, Boolean> phoneValidation = validateCorrectPhoneNumber(phone);
            if (phoneValidation.containsValue(false)) return phoneValidation;
        }

        Map<String, Boolean> emailValidation = validateCorrectEmail(email);
        if (emailValidation.containsValue(false)) return emailValidation;

        Map<String, Boolean> passwordValidation = validateSuccessPassword(currentEmail, oldPassword);
        if (passwordValidation.containsValue(false)) return passwordValidation;

        Map<String, Boolean> newPasswordValidation = validateCorrectPassword(newPassword);
        if (newPasswordValidation.containsValue(false)) return newPasswordValidation;

        Map<String, Boolean> passwordDoubleValidation = validateDoublePassword(newPassword, againPassword);
        if (passwordDoubleValidation.containsValue(false)) return passwordDoubleValidation;

        validFields.put("Все поля валидны", true);
        log.info("Validating security fields completed");
        return validFields;
    }

    public Map<String, Boolean> validatePhoneAndEmailFields(
            String phone,
            String email
    ) {
        log.info("Validating phone and email fields");
        Map<String, Boolean> validFields = new HashMap<>();

        if (!phone.isEmpty()) {
            Map<String, Boolean> phoneValidation = validateCorrectPhoneNumber(phone);
            if (phoneValidation.containsValue(false)) return phoneValidation;
        }

        Map<String, Boolean> emailValidation = validateCorrectEmail(email);
        if (emailValidation.containsValue(false)) return emailValidation;

        validFields.put("Все поля валидны", true);
        log.info("Validating phone and email fields completed");
        return validFields;
    }

    public Map<String, Boolean> validateMeasurements(MeasurementDto measurementDto) {
        log.info("Validating measurements");
        Map<String, Boolean> validFields = new HashMap<>();

        Map<String, Boolean> nameCorrectValidation = validateCorrectName(measurementDto.getName(), "name");
        if (nameCorrectValidation.containsValue(false)) return nameCorrectValidation;

        if (!measurementDto.getSecondName().isEmpty()) {
            Map<String, Boolean> secondNameCorrectValidation = validateCorrectName(measurementDto.getSecondName(), "secondName");
            if (secondNameCorrectValidation.containsValue(false)) return secondNameCorrectValidation;
        }

        if (!measurementDto.getThirdName().isEmpty()) {
            Map<String, Boolean> thirdNameCorrectValidation = validateCorrectName(measurementDto.getThirdName(), "thirdName");
            if (thirdNameCorrectValidation.containsValue(false)) return thirdNameCorrectValidation;
        }

        Map<String, Boolean> emailValidation = validateCorrectEmail(measurementDto.getEmail());
        if (emailValidation.containsValue(false)) return emailValidation;

        Map<String, Boolean> phoneValidation = validateCorrectPhoneNumber(measurementDto.getPhoneNumber());
        if (phoneValidation.containsValue(false)) return phoneValidation;

        Map<String, Boolean> addressCorrectValidation = validateCorrectAddress(measurementDto.getAddress());
        if (addressCorrectValidation.containsValue(false)) return addressCorrectValidation;

        Map<String, Boolean> doorsCountValidation = validateDoorsCount(measurementDto.getRoomDoorsCount(), measurementDto.getEnterDoorsCount());
        if (doorsCountValidation.containsValue(false)) return doorsCountValidation;

        Map<String, Boolean> measurementDateValidation = validateMeasurementDate(measurementDto.getMeasurementDate());
        if (measurementDateValidation.containsValue(false)) return measurementDateValidation;

        Map<String, Boolean> measurementTimeValidation = validateMeasurementTime(measurementDto.getMeasurementTime());
        if (measurementTimeValidation.containsValue(false)) return measurementTimeValidation;

        Map<String, Boolean> infoValidation = validateMaxSizeFields(measurementDto.getInfo(), "info", 1000);
        if (infoValidation.containsValue(false)) return infoValidation;

        validFields.put("Все поля валидны", true);
        log.info("Validating measurements completed");
        return validFields;
    }

    private Map<String, Boolean> validateMaxSizeFields(String field, String fieldName, int size) {
        Map<String, Boolean> erroredFields = new HashMap<>();

        if (field.length() > size) {
            erroredFields.put("Поле " + fieldName + " содержит больше 64 символов", false);
            return erroredFields;
        }

        erroredFields.put("Поле заполнено", true);
        return erroredFields;
    }

    private Map<String, Boolean> validateCorrectEmail(String email) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        String EMAIL_PATTERN = "^[_A-Za-z\\d-+]+(\\.[_A-Za-z\\d-]+)*@"
                + "[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            erroredFields.put("Email валиден", true);
        } else {
            erroredFields.put("Email не валиден", false);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateCorrectPhoneNumber(String phoneNumber) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        String PHONE_PATTERN = "^(\\+7|8)\\d{10}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            erroredFields.put("Номер телефона валиден", true);
        } else {
            erroredFields.put("Номер телефона не валиден", false);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateCorrectLogin(String login) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        String LOGIN_PATTERN = "^[a-zA-Z\\d_-]{3,16}$";
        Pattern pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);
        if (matcher.matches()) {
            erroredFields.put("Введённый логин валиден", true);
        } else {
            erroredFields.put("Логин должен содержать только латинские буквы, цифры, дефис и знак подчёркивания. " +
                    "Длина - не менее 3 символов, не более 16 символов", false);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateCorrectName(String name, String fieldName) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        String NAME_PATTERN = "^[a-zA-Z\\d_-]{3,16}$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            erroredFields.put("Введённое имя валидно", true);
        } else {
            erroredFields.put("Поле " + fieldName + " должно содержать только латинские буквы, цифры, дефис и знак подчёркивания. " +
                    "Длина - не менее 3 символов, не более 16 символов", false);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateCorrectAddress(String address) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        String ADDRESS_PATTERN = "^[a-zA-Zа-яА-Я\\d\\s-,.№/]{3,256}$";
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) {
            erroredFields.put("Введённый адрес валиден", true);
        } else {
            erroredFields.put("Адрес должен содержать буквы, цифры, пробелы, дефисы, слэши точки и запятые. " +
                    "Длина - не менее 3 символов, не более 256 символов", false);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateCorrectPassword(String password) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        String PASSWORD_PATTERN =
                "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+_=-])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            erroredFields.put("Введённый пароль валиден", true);
        } else {
            erroredFields.put(
                    "Пароль должен содержать хотя бы одну цифру, строчную букву, заглавную букву, " +
                            "специальный символ из набора [@#$%^&+_=-]. Не должен содержать пробелов. " +
                            "Длина - не менее 8 символов",
                    false
            );
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateDoublePassword(String password, String passwordDouble) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        if (!password.equals(passwordDouble)) {
            erroredFields.put("Пароли не совпадают", false);
            return erroredFields;
        }
        erroredFields.put("Пароли совпали", true);
        return erroredFields;
    }

    private Map<String, Boolean> validateSuccessPassword(String email, String password) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        try {
            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email, password);
            Authentication auth = authenticationManager.authenticate(authReq);

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (AuthenticationException e) {
            erroredFields.put("Не верный email или пароль", false);
            return erroredFields;
        }
        erroredFields.put("Пароль верный", true);
        return erroredFields;
    }

    private Map<String, Boolean> validateBirthDate(String birthdate) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        if (LocalDate.parse(birthdate).isAfter(LocalDate.now().minusYears(16))) {
            erroredFields.put("Ваш возраст должен быть больше 16 лет", false);
        } else if (LocalDate.parse(birthdate).isBefore(LocalDate.now().minusYears(150))) {
            erroredFields.put("Ваш возраст не должен быть больше 150 лет", false);
        } else {
            erroredFields.put("Возраст валиден", true);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateDoorsCount(int roomDoorsCount, int enterDoorsCount) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        if (roomDoorsCount <= 0 && enterDoorsCount <= 0) {
            erroredFields.put("Количество дверей должно быть больше 0", false);
        } else {
            erroredFields.put("Количество дверей валидно", true);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateMeasurementDate(String measurementDate) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        if (measurementDate.isEmpty()) {
            erroredFields.put("Необходимо ввести дату для замера", false);
        } else if (LocalDate.parse(measurementDate).isBefore(LocalDate.now().plusDays(1))) {
            erroredFields.put("Запись должна осуществляться заранее как минимум за один день", false);
        } else if (LocalDate.parse(measurementDate).isAfter(LocalDate.now().plusDays(30))) {
            erroredFields.put("Записаться на замер заранее можно не позднее чем за 30 дней до даты замера", false);
        } else {
            erroredFields.put("Запись валидна", true);
        }
        return erroredFields;
    }

    private Map<String, Boolean> validateMeasurementTime(String measurementTime) {
        Map<String, Boolean> erroredFields = new HashMap<>();
        if (measurementTime.equals("Нет доступного времени") || measurementTime.isEmpty()) {
            erroredFields.put("Необходимо ввести время для замера", false);
        } else {
            erroredFields.put("Запись валидна", true);
        }
        return erroredFields;
    }
}
