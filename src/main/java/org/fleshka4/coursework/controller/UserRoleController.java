package org.fleshka4.coursework.controller;

import org.fleshka4.coursework.model.UserRole;
import org.fleshka4.coursework.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping
    public List<UserRole> getRoles() {
        return userRoleRepository.findAll();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRole createRole(@RequestBody UserRole userRole) {
        if (userRoleRepository.findByRoleIgnoreCase(userRole.getRole()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists");
        }

        return userRoleRepository.save(userRole);
    }
}
