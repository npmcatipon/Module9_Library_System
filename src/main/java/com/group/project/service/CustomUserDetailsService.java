	package com.group.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group.project.entity.User;
import com.group.project.repository.RoleRepository;
import com.group.project.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public CustomUserDetailsService(UserRepository userRepository,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));

		List<String> roles = roleRepository.findRolesByUserId(user.getId());

		List<SimpleGrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.getEnabled(),
				true,
				true,
				true,
				authorities);
	}
}
