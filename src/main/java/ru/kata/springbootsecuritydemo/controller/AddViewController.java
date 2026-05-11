package ru.kata.springbootsecuritydemo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kata.springbootsecuritydemo.model.User;
import ru.kata.springbootsecuritydemo.service.UserService;

import java.security.Principal;
@Configuration
public class AddViewController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(
            ViewControllerRegistry registry) {
        registry.addViewController("/login")
                .setViewName("login");
        registry.addViewController("/admin")
                .setViewName("admin");
        registry.addViewController("/user")
                .setViewName("user");
    }
}
