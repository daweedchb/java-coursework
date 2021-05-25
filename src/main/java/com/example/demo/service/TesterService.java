package com.example.demo.service;

import com.example.demo.handler.java.Broker;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TesterService {
    private TaskService taskService;
    private DbUpdateService dbUpdateService;
    HomeService homeService;
    TaskRepository taskRepository;

    private int id;
    static private int solution_id = 0;

    @Autowired
    public TesterService(TaskService taskService, DbUpdateService dbUpdateService, HomeService homeService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.dbUpdateService = dbUpdateService;
        this.homeService = homeService;
        this.taskRepository = taskRepository;
    }

    public void setTaskID(int taskID) {
        this.id = taskID;
    }

    public void startTest(String newSolution) {
        taskService.createSolution(newSolution, id);
        String student_id = taskService.getUserName();
        dbUpdateService.insertStudent(student_id, id, solution_id);
        try {
            Broker broker = new Broker(student_id);
            int temp = dbUpdateService.findTries(student_id, id);
            temp++;
            if (!broker.runTest(String.valueOf(id), solution_id + 1)) {
                try {
                    dbUpdateService.updateTries(student_id, id, temp, false);
                    dbUpdateService.updateVerified(solution_id, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dbUpdateService.updateVerified(solution_id, true);
                    dbUpdateService.updateTries(student_id, id, temp, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        solution_id++;
    }
}
