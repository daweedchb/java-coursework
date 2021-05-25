package com.example.demo.controller;

import com.example.demo.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        homeService.personalPage(model);
        return "home";
    }

    @GetMapping("/addtask")
    public String addTask() {
        return "addtask";
    }

    @GetMapping("/registration")
    public String addUser() {
        return "registration";
    }

    @GetMapping("/auth")
    public String login() {
        return "login";
    }
}
