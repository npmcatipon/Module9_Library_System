package com.group.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.project.dto.CreateUserDTO;
import com.group.project.entity.User;
import com.group.project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * To create a new user
	 *
	 * @params userDto User Details of the new user. Contains username, password and
	 *         enabled.
	 * @return String success message.
	 */
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody CreateUserDTO userDto) {

		try {
			User user = userService.createuser(userDto);
			return ResponseEntity.ok("User created with username: " + user.getUsername());
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
