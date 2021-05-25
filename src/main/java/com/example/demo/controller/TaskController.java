package com.example.demo.controller;

import com.example.demo.service.DbUpdateService;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class TaskController {
    private TaskService taskService;
    private AddSolutionController addSolutionController;
    private DbUpdateService dbUpdateService;

    @Autowired
    public TaskController(TaskService taskService, AddSolutionController addSolutionController, DbUpdateService dbUpdateService) {
        this.taskService = taskService;
        this.addSolutionController = addSolutionController;
        this.dbUpdateService = dbUpdateService;
    }

    @GetMapping("/task/{id}")
    public String task(@PathVariable("id") int id, Model model) {
        String student_id = taskService.getUserName();
        String name = taskService.getName(id);
        if (dbUpdateService.isSolutionRight(student_id, id)) {
            model.addAttribute("standard", taskService.getStandardSolution(name));
        }
        addSolutionController.setTaskID(id);
        model.addAttribute("greeting", taskService.gettingTask((int)id));
        model.addAttribute("statement", taskService.getStatement(id));
        return "task";
    }

    @GetMapping("/tasks/{id}")
    public String TaskList(@PathVariable("id") String courseId, Model model) {
        model.addAttribute("tasks", taskService.getTaskList(courseId));
        return "tasks";
    }
}

