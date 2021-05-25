package com.example.demo.controller;

import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {
    TaskService taskService;

    @Autowired
    public CoursesController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/courses")
    public String CourseList(Model model) {
        model.addAttribute("courses", taskService.getCoursesList());
        return "courses";
    }
}
