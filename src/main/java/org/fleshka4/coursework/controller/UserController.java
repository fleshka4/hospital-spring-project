package org.fleshka4.coursework.controller;

import org.fleshka4.coursework.model.User;
import org.fleshka4.coursework.model.UserRole;
import org.fleshka4.coursework.repository.UserRepository;
import org.fleshka4.coursework.repository.UserRoleRepository;
import org.fleshka4.coursework.model.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(31);

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody AuthRequest authRequest) {
        if (userRepository.findByUsernameIgnoreCase(authRequest.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        if (userRoleRepository.findByRoleIgnoreCase(authRequest.getRole()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "To create role use /api/users/roles/add url instead");
        }

        return userRepository.save(new User(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()),
                new UserRole(authRequest.getRole())));
    }
}
