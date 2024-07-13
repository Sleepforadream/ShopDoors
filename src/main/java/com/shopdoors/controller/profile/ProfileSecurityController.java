package com.shopdoors.controller.profile;

import com.shopdoors.dao.entity.User;
import com.shopdoors.dao.repository.UserRepository;
import com.shopdoors.dto.AuthorizeUserDetails;
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
    private final UserRepository userRepository;
    private final ValidateService validateService;
    private final ImageService imageService;
    private final AuthorizeUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TransactionRunner transactionRunner;

    @GetMapping("/private_profile_security")
    public String profileInfoPage(Model model) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentEmail.equals("anonymousUser")) return "redirect:/home";

        model.addAttribute("currentPage", "/private_profile_security");
        transactionRunner.doInTransaction(() -> {
            String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
            User user = userRepository.findByEmail(currentEmail).orElseThrow();
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
            @RequestParam("isDelete") String isDelete,
            Model model
    ) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = currentAuth.getName();

        if (Boolean.parseBoolean(isDelete)) {
            transactionRunner.doInTransaction(() -> {
                var user = userRepository.findByEmail(currentEmail).orElseThrow();
                userRepository.delete(user);
            });
            currentAuth.setAuthenticated(false);
            return "home";
        }

        Map<String, Boolean> errors;

        if (!email.isEmpty() && oldPassword.isEmpty() && newPassword.isEmpty() && againPassword.isEmpty()) {
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
                    .orElse("Неизвестная ошибка"));

            transactionRunner.doInTransaction(() -> {
                String userImageName = userDetailsService.getImgPathByEmail(currentEmail);
                User user = userRepository.findByEmail(currentEmail).orElseThrow();
                model.addAttribute("user", user);
                model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
            });
            return "private_profile_security";
        }

        transactionRunner.doInTransaction(() -> userRepository.findByEmail(currentEmail).ifPresent(user -> {
            user.setEmail(email);
            user.setPhoneNumber(phone);
            if (!newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }
            if (!user.equals(((AuthorizeUserDetails) currentAuth.getPrincipal()).user())) {
                userRepository.save(user);
            }
        }));

        User user = userRepository.findByEmail(email).orElseThrow();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                new AuthorizeUserDetails(user),
                currentAuth.getCredentials(),
                currentAuth.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/private_profile_security";
    }
}
