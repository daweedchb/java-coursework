package com.example.demo.service;

import com.example.demo.entity.Score;
import com.example.demo.entity.Users;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    public List<Score> showRightTasks(String student_id) {
        Iterable<Score> scores = scoreRepository.findAll();
        List<Score> rightTaskList = new ArrayList();
        for (Score score : scores) {
            if (score.getStudent_id().equals(student_id) && score.getIs_right()) {
                rightTaskList.add(score);
            }
        }
        return rightTaskList;
    }

    public List<String> getUsersName() {
        Iterable<Users> users = registrationRepository.findAll();
        List<String> usersName = new ArrayList<>();
        for (Users user : users) {
            if (user.getRole().equals("ROLE_USER")) {
                usersName.add(user.getUsername());
            }
        }
        return usersName;
    }

    public long getUserID(String name) {
        Iterable<Users> users = registrationRepository.findAll();
        for (Users user : users) {
            if (user.getUsername().equals(name)) {
                return user.getUser_id();
            }
        }
        return -1;
    }
}
