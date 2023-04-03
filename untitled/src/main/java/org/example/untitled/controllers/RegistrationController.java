package org.example.untitled.controllers;

import org.example.untitled.entity.Role;
import org.example.untitled.entity.User;
import org.example.untitled.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Map<String, Object> model){
        User userFromBD = userRepo.findByUsername(user.getUsername());
        if (userFromBD != null){
            model.put("message", "User exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setClosed_Sheets(0);
        user.setSick_Period(0);
        user.setSick_Date("0");

        userRepo.save(user);

        return "redirect:/main";
    }
}
