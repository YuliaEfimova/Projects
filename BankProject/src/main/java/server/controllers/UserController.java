package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import server.model.Role;
import server.model.User;
import server.repositories.UserRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String userList(Map<String, Object> model){
        model.put("users", userRepository.findAll());
        return "userList";
    }

//
//    @PostMapping
//    public String userSave(
//            @RequestParam(required = false) String username,
//            @RequestParam Map<String, String> form,
//            @RequestParam(value = "client_id", required = false) User user){
//        user.setUsername(username);
//
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toSet());
//
//        user.getRoles().clear();
//
//        for (String key : form.keySet()){
//            if (roles.contains(key)) {
//                user.getRoles().add(Role.valueOf(key));
//            }
//        }
//
//        userRepository.save(user);
//
//        return "redirect:/user";
//    }
}
