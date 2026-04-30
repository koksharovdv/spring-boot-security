package ru.kata.springbootsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.springbootsecuritydemo.model.Role;
import ru.kata.springbootsecuritydemo.model.User;
import ru.kata.springbootsecuritydemo.repository.RoleRepository;
import ru.kata.springbootsecuritydemo.service.UserService;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }



    @GetMapping
    public String getUsers(Model model, @RequestParam(defaultValue = "10") int count) {
        List<User> users = userService.getUsers(count);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "admin";
    }

    @PostMapping
    public String addUser(@Validated @ModelAttribute User user,
                          BindingResult bindingResult,
                          Model model,@RequestParam(required = false) Integer count) {
        if (bindingResult.hasErrors()) {
            int safeCount = (count == null) ? 10 : count;

            List<User> users = userService.getUsers(safeCount);
            model.addAttribute("users", users);
            model.addAttribute("user", user);

            return "admin";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
