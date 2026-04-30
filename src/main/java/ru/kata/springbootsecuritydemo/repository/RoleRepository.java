package ru.kata.springbootsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.springbootsecuritydemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
