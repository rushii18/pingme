package com.pingme.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.responce.Authreponce;
import com.pingme.user.model.User;

import com.pingme.user.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public Authreponce signup(@RequestBody User user) throws Exception {
		Authreponce auth = userService.createPingmeAccout(user);
		return auth;
	}

	@PutMapping("/user/updat/{userid}")
	public User updateUser(@RequestBody User user, @PathVariable("userid") Integer id) throws Exception {
		User userUpdate = userService.updateUser(user, id);
		return userUpdate;
	}

	@PostMapping("/signin")
	public Authreponce loginUser(@RequestBody User user) throws Exception {
		Authreponce auth = userService.loginUser(user.getEmail(), user.getPassword());

		return auth;
	}

	@GetMapping("/getallusers")
	public List<User> getUserList() {

		List<User> userList = userService.getAllUser();

		return userList;
	}
}
