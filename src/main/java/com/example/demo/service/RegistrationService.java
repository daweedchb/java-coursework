package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    private static int COUNT_ID = 0;

    public void createUser(String password, String userName, String role) {
        Users users = new Users();
        users.setUser_id(COUNT_ID++);
        users.setPassword(password);
        users.setUsername(userName);
        users.setEnabled(true);
        users.setPoints(0);
        users.setRole(role);
        registrationRepository.save(users);
    }

    public void updateScore(String name, int buf){
        long id=-1;
        Iterable<Users> users1 = registrationRepository.findAll();
        for (Users user:users1) {
            if(user.getUsername().equals(name)) {
                id = user.getUser_id();
            }
        }
        Optional<Users> users = registrationRepository.findById(id);
        Users user = users.get();
        int bufScore = user.getPoints() + buf;
        user.setPoints(bufScore);
        registrationRepository.save(user);
    }

    public void ratingList(Model model){
        String attribute;
        Iterable<Users> users = registrationRepository.findAll();
        List<Users> sortedUsers = new ArrayList<Users>();
        List<String> finalUsers = new ArrayList<>();
        for (Users user : users) {
            sortedUsers.add(user);
        }
        for (Users user : sortedUsers) {
            attribute = String.valueOf(user.getPoints());
            attribute+= " очков   -   ";
            attribute += user.getUser_id();
            attribute+=" ";
            attribute+= user.getUsername();
            finalUsers.add(attribute);
        }
        Collections.sort(finalUsers);
        Collections.reverse(finalUsers);

        model.addAttribute("ratings", finalUsers);
    }

    public String gettingUserName(long id)
    {
        Optional<Users> users = registrationRepository.findById(id);

        return users.get().getUsername();
    }

    public String gettingUserRole(long id)
    {
        Optional<Users> users = registrationRepository.findById(id);

        return users.get().getRole();
    }

    public void changeRole(long id,String role){
        Optional<Users> users = registrationRepository.findById(id);
        Users user = users.get();
        user.setRole(role);
        registrationRepository.save(user);
    }


    public Iterable<Users> getUsersList()
    {
        Iterable<Users> users = registrationRepository.findAll();

        return users;
    }
}
