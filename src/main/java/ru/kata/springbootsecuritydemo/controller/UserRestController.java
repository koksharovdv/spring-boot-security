package ru.kata.springbootsecuritydemo.controller;


import org.springframework.web.bind.annotation.*;
import ru.kata.springbootsecuritydemo.model.Role;
import ru.kata.springbootsecuritydemo.model.User;
import ru.kata.springbootsecuritydemo.repository.RoleRepository;
import ru.kata.springbootsecuritydemo.service.UserService;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        return user;
    }
}
