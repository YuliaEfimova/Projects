package org.example.untitled.controllers;

import org.example.untitled.entity.User;
import org.example.untitled.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();
        System.out.println(users);
        model.put("users", users);

        return "main";
    }
}

