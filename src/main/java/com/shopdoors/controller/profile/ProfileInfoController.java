package com.shopdoors.controller.profile;

import com.shopdoors.dao.entity.AuthorizeUser;
import com.shopdoors.dao.repository.AuthorizeUserRepository;
import com.shopdoors.service.AuthorizeUserDetailsService;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.ValidateService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/private_profile_info")
    public String profileInfoPage(Model model) {
        model.addAttribute("currentPage", "/private_profile_info");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String userImageName = userDetailsService.getImgPathByEmail(email);

        AuthorizeUser user = authorizeUserRepository.findByEmail(email).orElseThrow();
        model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
        model.addAttribute("user", user);
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
                                Model model) {

        var errors = validateService.validateProfileFields(
                nickName, firstName, secondName, thirdName, birthDate, address, info
        );
        if (!errors.values().stream().findFirst().orElse(true)) {
            model.addAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("Неизвестная ошибка"));
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            String userImageName = userDetailsService.getImgPathByEmail(email);
            AuthorizeUser user = authorizeUserRepository.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            return "private_profile_info";
        }

        authorizeUserRepository.findByNickName(nickName).ifPresent(user -> {
            try {
                if (img != null && !img.isEmpty()) {
                    imageService.saveImg(img.getOriginalFilename(), img.getInputStream());
                    user.setImgPath(img.getOriginalFilename());
                }
            } catch (IOException e) {
                throw new RuntimeException("Saving img is failed!", e);
            }
            user.setNickName(nickName);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setThirdName(thirdName);
            user.setGender(gender);
            user.setBirthDate(LocalDate.parse(birthDate));
            user.setInfo(info);
            user.setAddress(address);
            authorizeUserRepository.save(user);
        });

        return "redirect:/private_profile_info";
    }
}
