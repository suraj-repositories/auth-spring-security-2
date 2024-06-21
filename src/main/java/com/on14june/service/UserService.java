package com.on14june.service;

import java.util.List;

import com.on14june.entities.Authority;
import com.on14june.entities.Role;
import com.on14june.entities.User;

public interface UserService {

	User saveUser(User user);
	
	User getUserByEmail(String email);
	
	void deleteUser(Long id);
	
	List<User> getAllUsers();
	
	User createUser(User user);
	
	Role createRole(Role role);
	
	List<String> getAuthoritiesOf(String role);
	
	Authority createAuthority(Authority authority);
	
	void deleteRole(Long roleId);
	
}
