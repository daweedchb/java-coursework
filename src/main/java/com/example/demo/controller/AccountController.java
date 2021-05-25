package com.example.demo.controller;

import com.example.demo.service.AccountService;
import com.example.demo.service.RegistrationService;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    TaskService taskService;
    AccountService accountService;
    RegistrationService registrationService;

    @Autowired
    public AccountController(TaskService taskService, AccountService accountService, RegistrationService registrationService) {
        this.taskService = taskService;
        this.accountService = accountService;
        this.registrationService = registrationService;
    }

    @GetMapping("/userAccount")
    public String userAccount(Model model) {
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "userAccount";
    }

    @GetMapping("/teacherAccount")
    public String teacherAccount(Model model) {
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("users", registrationService.getUsersList());
        model.addAttribute("usersName", accountService.getUsersName());
        return "teacherAccount";
    }

    @GetMapping("/checkUser")
    public String checkUser(@RequestParam String userName) {
        String redirectUrl = "/user/" + accountService.getUserID(userName);
        return "redirect:" + redirectUrl;
    }
}
