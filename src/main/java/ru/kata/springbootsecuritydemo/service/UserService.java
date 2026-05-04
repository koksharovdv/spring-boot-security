package ru.kata.springbootsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.springbootsecuritydemo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers(int count);

    void save(User user);

    User getUserById(Long id);

    void delete(Long id);

    User getUserByEmail(String email);

    void update(User user);
}
