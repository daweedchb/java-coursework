package com.example.demo.controller;

import com.example.demo.repository.RegistrationRepository;
import com.example.demo.service.HomeService;
import com.example.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private RegistrationService registrationService;
    private RegistrationRepository registrationRepository;
    HomeService homeService;
    private long ID;

    @Autowired
    public RegistrationController(RegistrationService registrationService, RegistrationRepository registrationRepository, HomeService homeService) {
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
        this.homeService = homeService;
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable("id") String id, Model model) {
        ID = Integer.parseInt(id);
        String userName = registrationService.gettingUserName(Integer.parseInt(id));
        String userRole = registrationService.gettingUserRole(Integer.parseInt(id));
        model.addAttribute("username", userName);
        model.addAttribute("userrole", userRole);
        return "user";
    }

    @GetMapping("/userrole")
    public String change(@RequestParam String role, Model model) {
        registrationService.changeRole(ID, role);
        String userName = registrationService.gettingUserName(ID);
        String userRole = registrationService.gettingUserRole(ID);
        model.addAttribute("username", userName);
        model.addAttribute("userrole", userRole);
        return "user";
    }

    @GetMapping("/registrationAction")
    public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String role, Model model) {
        registrationService.createUser(password, userName, role);
        homeService.personalPage(model);
        return "home";
    }

    @GetMapping("/rating")
    public String rating(Model model) {
        registrationService.ratingList(model);
        return "rating";
    }
}
