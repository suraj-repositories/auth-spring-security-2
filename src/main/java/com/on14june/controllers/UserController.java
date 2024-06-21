package com.on14june.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.on14june.entities.Authority;
import com.on14june.entities.Role;
import com.on14june.entities.User;
import com.on14june.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role) {
        return userService.createRole(role);
    }

    @PostMapping("/authorities")
    public Authority createAuthority(@RequestBody Authority authority) {
        return userService.createAuthority(authority);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
