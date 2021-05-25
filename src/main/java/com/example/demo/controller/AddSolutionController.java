package com.example.demo.controller;

import com.example.demo.repository.TaskRepository;
import com.example.demo.service.DbUpdateService;
import com.example.demo.service.HomeService;
import com.example.demo.service.TaskService;
import com.example.demo.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;


@Controller
public class AddSolutionController {
    private TaskService taskService;
    private DbUpdateService dbUpdateService;
    HomeService homeService;
    TesterService testerService;
    TaskRepository taskRepository;

    private int id;

    static private int solution_id = 0;

    @Autowired
    public AddSolutionController(TaskService taskService, DbUpdateService dbUpdateService, HomeService homeService, TesterService testerService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.dbUpdateService = dbUpdateService;
        this.homeService = homeService;
        this.testerService = testerService;
        this.taskRepository = taskRepository;
    }

    public void setTaskID(int task_id) {
        this.id = task_id;
    }

    @GetMapping("/addSolution")
    public String addSolution(@RequestParam String newSolution, Model model) {
        testerService.setTaskID(id);
        String student_id = taskService.getUserName();
        dbUpdateService.insertStudent(student_id, id, solution_id);
        if (dbUpdateService.isSolutionRight(student_id, id)) {
            model.addAttribute("standard", taskService.getStandardSolution(String.valueOf(id)));
        }
        testerService.startTest(newSolution);
        homeService.personalPage(model);
        return "home";
    }

    @GetMapping("/addFileSolution")
    public String addFileSolution(@RequestParam("file") File file, Model model) {
        testerService.setTaskID(id);
        String student_id = taskService.getUserName();
        dbUpdateService.insertStudent(student_id, id, solution_id);
        String path = "C:\\Users\\David\\IdeaProjects\\homework_check_system\\demo\\src\\main\\java\\com\\example\\demo\\handler\\tasks";
        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        homeService.personalPage(model);
        return "home";
    }
}
