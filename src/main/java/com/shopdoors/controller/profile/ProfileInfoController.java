package com.shopdoors.controller.profile;

import com.shopdoors.dao.entity.user.User;
import com.shopdoors.dao.repository.UserRepository;
import com.shopdoors.dto.ProfileDto;
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

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProfileInfoController {
    private final UserRepository userRepository;
    private final ValidateService validateService;
    private final ImageService imageService;
    private final AuthorizeUserDetailsService userDetailsService;
    private final TransactionRunner transactionRunner;

    @GetMapping("/private_profile_info")
    public String profileInfoPage(Model model) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentEmail.equals("anonymousUser")) return "redirect:/home";

        model.addAttribute("currentPage", "/private_profile_info");
        addAttributes(model, currentEmail);

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

        ProfileDto profileDto = ProfileDto.builder()
                .img(img)
                .nickName(nickName)
                .firstName(firstName)
                .secondName(secondName)
                .thirdName(thirdName)
                .gender(gender)
                .birthDate(birthDate)
                .info(info)
                .address(address)
                .imgProfileName(imgProfileName)
                .build();

        var errors = validateService.validateProfileFields(profileDto);

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = currentAuth.getName();

        if (!errors.values().stream().findFirst().orElse(true)) {
            addAttributesWithError(model, errors, currentEmail);
            return "private_profile_info";
        }

        userDetailsService.updateSoftInfoUser(profileDto, currentEmail, currentAuth);

        return "redirect:/private_profile_info";
    }

    private void addAttributesWithError(Model model, Map<String, Boolean> errors, String currentEmail) {
        model.addAttribute("error", errors.keySet()
                .stream()
                .findFirst()
                .orElse("Неизвестная ошибка"));

        addAttributes(model, currentEmail);
    }

    private void addAttributes(Model model, String currentEmail) {
        transactionRunner.doInTransaction(() -> {
            String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
            User user = userRepository.findByEmail(currentEmail).orElseThrow();
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            model.addAttribute("user", user);
        });
    }
}
