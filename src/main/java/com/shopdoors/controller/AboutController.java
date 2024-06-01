package com.shopdoors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController {

    @GetMapping(value = {"/about"})
    public String aboutPage() {
        return "about";
    }
}