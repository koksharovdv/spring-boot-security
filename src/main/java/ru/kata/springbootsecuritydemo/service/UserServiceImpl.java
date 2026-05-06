package ru.kata.springbootsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.springbootsecuritydemo.model.Role;
import ru.kata.springbootsecuritydemo.model.User;
import ru.kata.springbootsecuritydemo.repository.RoleRepository;
import ru.kata.springbootsecuritydemo.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers(int count) {
        List<User> users = userRepository.findAll();
        if (count >= users.size()) {
            return users;
        }
        return users.subList(0, count);

    }

    public User getUserByMail(String email) { return userRepository.findByEmail(email);}
    @Override
    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = user.getRoles().stream()

                .map(role -> roleRepository.findById(role.getId()).orElseThrow())

                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("пользователь не найден: " + username);
        }
        user.getRoles().size();
        return user;
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = user.getRoles().stream()

                .map(role -> roleRepository.findById(role.getId()).orElseThrow())

                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);}

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
