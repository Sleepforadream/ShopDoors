package com.shopdoors.controller.profile;

import com.shopdoors.service.AuthorizeUserDetailsService;
import com.shopdoors.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final ImageService imageService;
    private final AuthorizeUserDetailsService userDetailsService;

    @GetMapping(value = {"/private_profile", "/private_profile_home"})
    public String profilePage(Model model) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentEmail.equals("anonymousUser")) return "redirect:/home";

        model.addAttribute("currentPage", "/private_profile_home");

        String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
        model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
        return "private_profile_home";
    }
}
