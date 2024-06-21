package com.on14june;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.on14june.entities.Authority;
import com.on14june.entities.Role;
import com.on14june.entities.User;
import com.on14june.repository.AuthorityRepository;
import com.on14june.repository.RoleRepository;
import com.on14june.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//
		Authority readAuthority = new Authority();
		readAuthority.setName("READ_PRIVILEGE");
		authorityRepository.save(readAuthority);

		Authority writeAuthority = new Authority();
		writeAuthority.setName("WRITE_PRIVILEGE");
		authorityRepository.save(writeAuthority);
		
		Authority exeAuthority = new Authority();
		exeAuthority.setName("EXECUTE_PRIVILEGE");
		authorityRepository.save(exeAuthority);

		Authority mngAuthority = new Authority();
		mngAuthority.setName("MANAGE_PRIVILEGE");
		authorityRepository.save(mngAuthority);

		
		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		adminRole.setAuthorities(new HashSet<>(authorityRepository.findAll()));
		roleRepository.save(adminRole);
		
		Role managerRole = new Role();
		managerRole.setName("MANAGER");
		managerRole.setAuthorities(new HashSet<>(Arrays.asList(
				authorityRepository.findByName("READ_PRIVILEGE"), 
				authorityRepository.findByName("WRITE_PRIVILEGE"), 
				authorityRepository.findByName("MANAGE_PRIVILEGE"))));
		roleRepository.save(managerRole);
		
		Role userRole = new Role();
		userRole.setName("USER");
		userRole.setAuthorities(new HashSet<>(Arrays.asList(
				authorityRepository.findByName("READ_PRIVILEGE"), 
				authorityRepository.findByName("WRITE_PRIVILEGE"))));
		roleRepository.save(userRole);

		User admin = new User();
		admin.setEmail("admin@gmail.com");
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setDob(LocalDate.now());
		admin.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(admin);

	}

}
