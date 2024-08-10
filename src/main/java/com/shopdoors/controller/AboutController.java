package com.shopdoors.controller;

import com.shopdoors.service.ImageService;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController {

    private final AuthorizeUserDetailsService userDetailsService;

    @GetMapping(value = {"/about"})
    public String aboutPage(Model model) {
        model.addAttribute("imgProfileUrl", userDetailsService.getCurrentUserImgPath());
        return "about";
    }
}