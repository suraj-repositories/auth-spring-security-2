package com.on14june.service;

import com.on14june.entities.Role;

public interface RoleService {
	
	Role saveRole(Role role);
	
	Role getRoleByName(String role);
	
}
