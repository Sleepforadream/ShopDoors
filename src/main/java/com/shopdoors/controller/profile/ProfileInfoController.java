package com.shopdoors.controller.profile;

import com.shopdoors.dao.entity.AuthorizeUser;
import com.shopdoors.dao.repository.AuthorizeUserRepository;
import com.shopdoors.security.dto.AuthorizeUserDetails;
import com.shopdoors.service.AuthorizeUserDetailsService;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.ValidateService;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class ProfileInfoController {
    private final AuthorizeUserRepository authorizeUserRepository;
    private final ValidateService validateService;
    private final ImageService imageService;
    private final AuthorizeUserDetailsService userDetailsService;
    private final TransactionRunner transactionRunner;

    @GetMapping("/private_profile_info")
    public String profileInfoPage(Model model) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentEmail.equals("anonymousUser")) return "redirect:/home";

        model.addAttribute("currentPage", "/private_profile_info");
        transactionRunner.doInTransaction(() -> {
            String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
            AuthorizeUser user = authorizeUserRepository.findByEmail(currentEmail).orElseThrow();
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            model.addAttribute("user", user);
        });

        return "private_profile_info";
    }

    @PostMapping("/private_profile_info")
    public String profileChange(@RequestPart("imgProfileAdd") MultipartFile img,
                                @RequestParam("nickName") String nickName,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("secondName") String secondName,
                                @RequestParam("thirdName") String thirdName,
                                @RequestParam("gender") String gender,
                                @RequestParam("birthDate") String birthDate,
                                @RequestParam("info") String info,
                                @RequestParam("address") String address,
                                @RequestParam("imgProfileName") String imgProfileName,
                                Model model) {

        var errors = validateService.validateProfileFields(
                nickName, firstName, secondName, thirdName, birthDate, address, info
        );

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = currentAuth.getName();

        if (!errors.values().stream().findFirst().orElse(true)) {
            model.addAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("Неизвестная ошибка"));
            transactionRunner.doInTransaction(() -> {
                String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
                AuthorizeUser user = authorizeUserRepository.findByEmail(currentEmail).orElseThrow();
                model.addAttribute("user", user);
                model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            });
            return "private_profile_info";
        }

        transactionRunner.doInTransaction(() -> authorizeUserRepository.findByEmail(currentEmail).ifPresent(user -> {
            user.setNickName(nickName);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setThirdName(thirdName);
            user.setGender(gender);
            if (!birthDate.isEmpty()) {
                user.setBirthDate(LocalDate.parse(birthDate));
            } else {
                user.setBirthDate(null);
            }
            user.setInfo(info);
            user.setAddress(address);
            user.setImgPath(img.getOriginalFilename());
            if (!user.equals(((AuthorizeUserDetails) currentAuth.getPrincipal()).authorizeUser())) {
                try {
                    if (!img.isEmpty()) {
                        imageService.saveImg(img.getOriginalFilename(), img.getInputStream());
                    } else {
                        user.setImgPath(imgProfileName);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Saving img is failed!", e);
                }
                authorizeUserRepository.save(user);
            }
        }));

        return "redirect:/private_profile_info";
    }
}
