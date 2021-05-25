package com.example.demo.controller;

import com.example.demo.service.HomeService;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddTaskController {
    private TaskService taskService;
    HomeService homeService;

    @Autowired
    public AddTaskController(TaskService taskService, HomeService homeService) {
        this.taskService = taskService;
        this.homeService = homeService;
    }

    @GetMapping("/addTaskAction")
    public String addTask(@RequestParam String taskName, @RequestParam String newTask, @RequestParam String courseId, @RequestParam String complexity, @RequestParam String standard, Model model) {
        taskService.createTask(newTask, taskName, courseId, complexity, standard);
        homeService.personalPage(model);
        return "home";
    }
}
