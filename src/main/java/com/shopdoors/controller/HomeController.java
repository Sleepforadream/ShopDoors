package com.shopdoors.controller;

import com.shopdoors.dao.entity.Article;
import com.shopdoors.dao.repository.ArticleRepository;
import com.shopdoors.service.AuthorizeUserDetailsService;
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
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ArticleRepository articleRepository;
    private final LoginController loginController;
    private final AuthorizeUserDetailsService userDetailsService;
    private final ImageService imageService;

    @GetMapping(value = {"/", "/home"})
    public String homePage(Model model) {
        List<Article> articles = articleRepository.findAllByOrderByDateDesc();

        model.addAttribute("currentView", "home");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.equals("anonymousUser")) {
            String userImageName = userDetailsService.getImgPathByEmail(username);
            model.addAttribute("imgProfileUrl", imageService.getImgUrl(userImageName));
        }

        if (articles.size() > 1) {
            Article article1 = articles.get(0);
            Article article2 = articles.get(1);

            List<String> monthsYears = articles.stream()
                    .map(article -> article.getDate().format(DateTimeFormatter.ofPattern("yyyy MMMM")))
                    .distinct()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            model.addAttribute("articles", articles);
            model.addAttribute("article1", article1);
            model.addAttribute("article2", article2);
            model.addAttribute("monthsyears", monthsYears);
        } else {
            model.addAttribute("articles", articles);
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