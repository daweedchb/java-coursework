package com.example.demo.service;

import com.example.demo.entity.Score;
import com.example.demo.entity.Solution;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.SolutionRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.max;

@Service
public class DbUpdateService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    RegistrationService registrationService;

    public void insertStudent(String student_id, int task_id, int solution_id) {
        try {
            if (!isStudentExistInScore(student_id, task_id)) {
                Score score = new Score();
                score.setStudent_id(student_id);
                score.setCourse_id("0");
                score.setTask_id(task_id);
                score.setTries(0);
                score.setSolution_id(solution_id);
                scoreRepository.save(score);
            }
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateTries(String student_id, int task_id, int tries, Boolean isRight) {
        int buf;
        Iterable<Score> scores = scoreRepository.findAll();
        for (Score score : scores) {
            if (score.getStudent_id().equals(student_id) && score.getTask_id() == task_id) {
                score.setTries(tries);
                score.setIs_right(isRight);
                if (isRight) {
                    score.setPoints(max(50, 100 - (10 * (tries - 1))));
                    buf = max(50, 100 - (10 * (tries - 1)));
                    registrationService.updateScore(student_id, buf);
                }
                scoreRepository.save(score);
            }
        }
    }

    public void updateVerified(int solution_id, Boolean isRight) {
        Solution solution = solutionRepository.findById(solution_id).get();
        solution.setIs_checked(true);
        solution.setIs_right(isRight);
        solutionRepository.save(solution);
    }

    public int findTries(String student_id, int task_id) {
        Iterable<Score> scores = scoreRepository.findAll();
        for (Score score : scores) {
            if (score.getStudent_id().equals(student_id) && score.getTask_id() == task_id) {
                return score.getTries();
            }
        }
        return -1;
    }

    public boolean isStudentExistInScore(String student_id, int task_id) {

        Iterable<Score> scores = scoreRepository.findAll();
        for (Score score : scores) {
            if (score.getStudent_id().equals(student_id) && score.getTask_id() == task_id) {
                return true;
            }
        }
        return false;
    }

    public boolean isSolutionRight(String student_id, long task_id) {
        Iterable<Solution> solutions = solutionRepository.findAll();
        for (Solution solution : solutions) {
            if (solution.getStudent_name().equals(student_id) && solution.getTask_id().equals(task_id) && solution.getIs_right()) {
                return true;
            }
        }
        return false;
    }
}
