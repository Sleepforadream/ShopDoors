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
        model.addAttribute("currentPage", "/private_profile_home");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String userImageName = userDetailsService.getImgPathByEmail(username);
        model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
        return "private_profile_home";
    }
}
