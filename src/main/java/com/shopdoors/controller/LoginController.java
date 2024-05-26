package com.shopdoors.controller;

import com.shopdoors.service.ValidateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ValidateService validateService;

    @GetMapping({"/login"})
    public String loginPage() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("floatingInput") String email,
                            @RequestParam("floatingPassword") String password,
                            @RequestParam(value = "rememberme", required = false) String remember,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        var errors = validateService.validateLoginFields(email, password);
        if (!errors.values().stream().findFirst().orElse(true)) {
            redirectAttributes.addFlashAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("Неизвестная ошибка"));
            return "redirect:/login";
        }
        request.getSession().setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return "redirect:/home";
    }

    @GetMapping({"/login-success"})
    public String loginSuccessPage() {
        return "login_success";
    }
}