package com.on14june.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.on14june.security.AuthorityChecker;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	private AuthorityChecker authorityChecker;
	
	@ModelAttribute
	public AuthorityChecker getAuthorityChecker() {
		return authorityChecker;
	}
	
	
}
