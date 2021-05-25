package com.example.demo.controller;

import com.example.demo.service.HomeService;
import com.example.demo.service.TaskService;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddTestController {
    private TestService testService;
    private TaskService taskService;
    HomeService homeService;

    @Autowired
    public AddTestController(TestService testService, TaskService taskService, HomeService homeService) {
        this.testService = testService;
        this.taskService = taskService;
        this.homeService = homeService;
    }

    @GetMapping("/addtest")
    public String getTestList(Model model) {
        model.addAttribute("tasks", taskService.getTasks());
        return "addtest";
    }

    @GetMapping("/addTestAction")
    public String addTest(@RequestParam String taskName, @RequestParam String inputData,
                          @RequestParam String outputData, Model model) {
        testService.addTest(taskName, inputData, outputData);
        homeService.personalPage(model);
        return "home";
    }
}
