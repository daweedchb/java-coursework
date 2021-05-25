package com.example.demo.service;

import com.example.demo.entity.Solution;
import com.example.demo.entity.Task;
import com.example.demo.handler.FileWorker;
import com.example.demo.repository.SolutionRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private SolutionRepository solutionRepository;

    private static int SLT_ID = 0;

    @Autowired
    public TaskService(TaskRepository taskRepository, SolutionRepository solutionRepository) {
        this.taskRepository = taskRepository;
        this.solutionRepository = solutionRepository;
    }

    public void createTask(String statement, String name, String courseID, String complexity, String standard) {
        Task task = new Task();
        task.setCourseId(courseID);
        task.setStatement(statement);
        task.setComplexity(complexity);
        task.setName(name);
        task.setStandard(standard);
        taskRepository.save(task);
    }

    public void createSolution(String newSolution, int id) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Solution solution = new Solution();
        Task task = new Task();
        solution.setTask_id(String.valueOf(id));
        solution.setSolution(newSolution);
        solution.setIs_right(null);
        solution.setIs_checked(false);
        solution.setSolution_id(SLT_ID++);
        solution.setStudent_name(currentUser);
        solutionRepository.save(solution);
        FileWorker fileWorker = new FileWorker(String.valueOf(SLT_ID), newSolution);
    }

    public String getName(int id){
        Optional<Task> task = taskRepository.findById(id);
        return task.get().getName();
    }

    public String getStatement(int id){
        Optional<Task> task = taskRepository.findById(id);
        return task.get().getStatement();
    }

    public String gettingTask(int id)
    {
        Optional<Task> task = taskRepository.findById(id);

        return task.get().getStatement();
    }

    public String getStandardSolution(String name)
    {
        Iterable<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            if(task.getName().equals(name)){
                return task.getStandard();
            }
        }
        return "exception in taskService or dbupdateservice";
    }

    public Set getCoursesList()
    {
        Iterable<Task> courses = taskRepository.findAll();
        Set<String> courseIdList = new LinkedHashSet<String>();

        for (Task course: courses) {
            courseIdList.add(course.getCourseId());
        }

        courseIdList.stream().sorted();
        return courseIdList;
    }

    public Set<Task> getTaskList(String courseId)
    {
        Iterable<Task> tasks = taskRepository.findAll();
        Set<Task> tasksIdList = new LinkedHashSet<Task>();
        for (Task task: tasks) {
            if(task.getCourseId().equals(courseId)) {
                tasksIdList.add(task);
            }
        }
        return tasksIdList;
    }
    public Iterable<Task> getTaskListWithoutId()
    {
        Iterable<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public String getUserName()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }
}