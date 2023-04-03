package server.controllers;

import ch.qos.logback.core.hook.DelayingShutdownHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import server.model.Bank;
import server.model.Role;
import server.model.User;
import server.repositories.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){


        user.status=true;
        user.setRoles(Collections.singleton(Role.USER));

        if((userRepository.findByUsername(user.getUsername()))==null)
        {
            userRepository.save(user);
            return "redirect:/login";}
        else{
            System.out.println((userRepository.findByUsername(user.getUsername())).getClass());
            System.out.println(userRepository.findByUsername(user.getUsername()).role);

        return "redirect:/registration";}
    }







}
