package com.on14june.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.on14june.entities.Authority;
import com.on14june.entities.Role;
import com.on14june.entities.User;
import com.on14june.repository.AuthorityRepository;
import com.on14june.repository.RoleRepository;
import com.on14june.repository.UserRepository;
import com.on14june.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		 return userRepository.findByEmailWithRoles(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(userRepository.findById(id).get());
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

	@Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

	@Override
    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
    
	@Override
    public List<String> getAuthoritiesOf(String role){
    	Role roleObj = roleRepository.findByName(role);
    	if(roleObj == null) {
    		throw new RuntimeException("Role not found!!!!");
    	}
    	return roleObj.getAuthorities().stream().map(authority -> authority.getName()).collect(Collectors.toList()); 
    }
    
    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.getRoles().remove(role);
            userRepository.save(user);
        }
        roleRepository.deleteById(roleId);
    }

}
