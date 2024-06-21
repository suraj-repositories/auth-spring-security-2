package com.on14june.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.on14june.entities.Role;
import com.on14june.entities.User;
import com.on14june.service.RoleService;
import com.on14june.service.UserService;

@Controller
public class CreateUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/create")
	public String addUser(@RequestParam("name") String name,
						  @RequestParam("dob") LocalDate dob,
						  @RequestParam("email") String email,
						  @RequestParam("password") String password,
						  @RequestParam("role") String role
			) {

		Role roleObj = roleService.getRoleByName(role);
		
		User user = User.builder()
			.name(name)
			.email(email)
			.password(password)
			.dob(LocalDate.now())
			.roles(new HashSet<>(Arrays.asList(roleObj)))
			.build();
		
		userService.saveUser(user);
		
		return "redirect:/";
	}
	
	
	
}
