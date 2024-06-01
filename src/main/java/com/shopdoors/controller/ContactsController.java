package com.shopdoors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ContactsController {

    @GetMapping(value = {"/contacts"})
    public String contactsPage() {
        return "contacts";
    }
}