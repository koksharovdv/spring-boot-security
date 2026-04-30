package ru.kata.springbootsecuritydemo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.kata.springbootsecuritydemo.model.Role;
import ru.kata.springbootsecuritydemo.model.User;
import ru.kata.springbootsecuritydemo.repository.RoleRepository;
import ru.kata.springbootsecuritydemo.service.UserService;

import java.util.Set;

@Component
public class InitData implements CommandLineRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    public InitData(UserService userService,
                    RoleRepository roleRepository,
                    BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }
        @Override
    public void run(String... args) throws Exception {


            // создаём роли

            Role adminRole = new Role();

            adminRole.setName("ROLE_ADMIN");

            Role userRole = new Role();

            userRole.setName("ROLE_USER");

            roleRepository.save(adminRole);

            roleRepository.save(userRole);

        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("1234");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
            admin.setRoles(Set.of(adminRole));

            userService.save(admin);
    }
}
