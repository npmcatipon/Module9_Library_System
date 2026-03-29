package com.group.project.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group.project.dto.CreateUserDTO;
import com.group.project.entity.Role;
import com.group.project.entity.User;
import com.group.project.repository.RoleRepository;
import com.group.project.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User createuser(CreateUserDTO userDto) {
		if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exist");
		}

		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEnabled(true);

		User savedUser = userRepository.save(user);

		// Assign Roles
		if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
			for (String roleName : userDto.getRoles()) {
				Role roles = new Role(savedUser, roleName);
				roleRepository.save(roles);
			}
		} else {
			Role role = new Role(savedUser, "ROLE_USER");
			roleRepository.save(role);
		}

		return savedUser;

	}

}
