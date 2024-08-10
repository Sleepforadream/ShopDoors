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
public class ContactsController {

    private final AuthorizeUserDetailsService userService;

    @GetMapping(value = {"/contacts"})
    public String contactsPage(Model model) {
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        return "contacts";
    }
}