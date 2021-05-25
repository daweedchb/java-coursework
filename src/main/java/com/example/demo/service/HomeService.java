package com.example.demo.service;

import com.example.demo.repository.SolutionRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    public void personalPage(Model model){
        String userRole = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        List<String[]> listLinks = new ArrayList<String[]>();
        if(userRole.equals("[ROLE_ADMIN]")) {
            listLinks.add(new String[]{"addtask", "Добавить задачу"});
            listLinks.add(new String[]{"addtest", "Добавить тесты"});
        }
        if (userRole.equals("[ROLE_USER]") || userRole.equals("[ROLE_ADMIN]")){
            listLinks.add(new String[] {"courses", "Список задач"});
            if (userRole.equals("[ROLE_USER]")) {
                listLinks.add(new String[]{"userAccount", "Личный кабинет"});
            }
            else{
                listLinks.add(new String[]{"teacherAccount", "Личный кабинет"});
            }
        } else{
            listLinks.add(new String[] {"auth", "Авторизация"});
            listLinks.add(new String[] {"registration", "Регистрация"});
        }
        listLinks.add(new String[] {"rating", "Рейтинг"});
        model.addAttribute("listLinks", listLinks);
    }

}
