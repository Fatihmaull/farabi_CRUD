package com.example.farabi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/buku"; // atau return "index"; kalau kamu punya templates/index.html
    }
}
