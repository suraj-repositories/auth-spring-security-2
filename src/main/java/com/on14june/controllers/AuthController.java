package com.on14june.controllers;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.on14june.entities.User;
import com.on14june.service.RoleService;
import com.on14june.service.UserService;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoleService roleService;
	
	@GetMapping("/")
	public String home(Model model, Authentication authentication) {
		if(authentication != null) {
			User user = userService.getUserByEmail(authentication.getName());
			model.addAttribute("user", user);
			return "welcome";
		}
		return "welcome";
	}
	
	@GetMapping("/login")
	public String loginpage(Authentication authentication) {
		if(authentication != null) {
			return "redirect:/";
		}
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage(Model model, Authentication authentication) {
		if(authentication != null) {
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")		
	public String performSignup(User user, Model model, RedirectAttributes attributes) {
	
		if(user == null) {
			return "redirect:/signup";
		}
		else if(!user.getPassword().equals(user.getConfirmPassword())) {
			return "redirect:/signup";	
		}
		user.setRoles(new HashSet<>(Arrays.asList(roleService.getRoleByName("USER"))));
		userService.saveUser(user);
		return "redirect:/login";
	}
	
	
}
