package com.shopdoors.controller.profile;

import com.shopdoors.dao.entity.AuthorizeUser;
import com.shopdoors.dao.repository.AuthorizeUserRepository;
import com.shopdoors.security.dto.AuthorizeUserDetails;
import com.shopdoors.service.AuthorizeUserDetailsService;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.ValidateService;
import com.shopdoors.util.TransactionRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProfileSecurityController {
    private final AuthorizeUserRepository authorizeUserRepository;
    private final ValidateService validateService;
    private final ImageService imageService;
    private final AuthorizeUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TransactionRunner transactionRunner;

    @GetMapping("/private_profile_security")
    public String profileInfoPage(Model model) {
        model.addAttribute("currentPage", "/private_profile_security");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        transactionRunner.doInTransaction(() -> {
            String userImageName = userDetailsService.getImgPathByEmail(email);
            AuthorizeUser user = authorizeUserRepository.findByEmail(email).orElseThrow();
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            model.addAttribute("user", user);
        });
        return "private_profile_security";
    }

    @PostMapping("/private_profile_security")
    public String profileChange(
            @RequestParam("phoneNumber") String phone,
            @RequestParam("email") String email,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("repeatNewPassword") String againPassword,
            Model model
    ) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Boolean> errors;

        if (!phone.isEmpty() && !email.isEmpty() && oldPassword.isEmpty() && newPassword.isEmpty() && againPassword.isEmpty()) {
            errors = validateService.validatePhoneAndEmailFields(phone, email);

        } else {
            errors = validateService.validateSecurityFields(
                    phone, email, currentEmail, oldPassword, newPassword, againPassword
            );
        }

        if (!errors.values().stream().findFirst().orElse(true)) {
            model.addAttribute("error", errors.keySet()
                    .stream()
                    .findFirst()
                    .orElse("vНеизвестная ошибка"));

            transactionRunner.doInTransaction(() -> {
                String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
                AuthorizeUser user = authorizeUserRepository.findByEmail(currentEmail).orElseThrow();
                model.addAttribute("user", user);
                model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            });
            return "private_profile_security";
        }

        transactionRunner.doInTransaction(() -> authorizeUserRepository.findByEmail(currentEmail).ifPresent(user -> {
            user.setEmail(email);
            user.setPhoneNumber(phone);
            if (!newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }
            authorizeUserRepository.save(user);
        }));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AuthorizeUser user = authorizeUserRepository.findByEmail(email).orElseThrow();

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                new AuthorizeUserDetails(user),
                authentication.getCredentials(),
                authentication.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/private_profile_security";
    }
}
