package com.shopdoors.controller;

import com.shopdoors.service.user.AuthorizeUserDetailsService;
import com.shopdoors.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final LoginController loginController;
    private final AuthorizeUserDetailsService userDetailsService;
    private final ImageService imageService;

    @GetMapping(value = {"/", "/home"})
    public String homePage(Model model) {

        model.addAttribute("currentView", "home");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.equals("anonymousUser")) {
            String userImageName = userDetailsService.getImgPathByEmail(username);
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
        }
        return "home";
    }

    @PostMapping("/*")
    public String loginUser(@RequestParam("username_popup") String email,
                            @RequestParam("password_popup") String password,
                            @RequestParam(value = "rememberme", required = false) String remember,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        return loginController.loginUser(email, password, remember, redirectAttributes, request);
    }

    @GetMapping("/favicon.ico")
    public ResponseEntity<byte[]> favicon() throws IOException {
        Resource resource = new ClassPathResource("/static/favicon.ico");
        byte[] favicon = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/x-icon")).body(favicon);
    }
}