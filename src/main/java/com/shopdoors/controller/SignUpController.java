package com.shopdoors.controller;

import com.shopdoors.dao.entity.user.User;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
import com.shopdoors.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final AuthorizeUserDetailsService userService;
    private final ValidateService validateService;

    @GetMapping({"/signup"})
    public String signUpPage() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            return "redirect:/home";
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(
            @RequestParam("floatingName") String name,
            @RequestParam("floatingEmail") String email,
            @RequestParam("floatingPassword") String password,
            @RequestParam("floatingPassword2") String passwordDouble,
            RedirectAttributes redirectAttr
    ) {
        var errors = validateService.validateRegistrationFields(name, email, password, passwordDouble);

        if (!errors.values().stream().findFirst().orElse(true)) {
            redirectAttr.addFlashAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("Неизвестная ошибка"));
            return "redirect:/signup";
        } else {
            User registeredUser = userService.saveUser(email, password, name);
            if (registeredUser.getEmail() == null) {
                redirectAttr.addFlashAttribute("error", "Пользователь с таким email уже существует");
                return "redirect:/signup";
            }
            return "redirect:/login-success";
        }
    }
}