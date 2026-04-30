package ru.kata.springbootsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.springbootsecuritydemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
