package com.pingme.user.controller;

import java.util.List;
import java.util.Optional;

import com.pingme.chat.model.Chat;
import com.pingme.chat.repo.ChatRepository;
import com.pingme.chat.service.ChatService;
import com.pingme.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.responce.Authreponce;

import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;
import com.pingme.user.service.UserService;

import lombok.experimental.PackagePrivate;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signup")
	public Authreponce signup(@RequestBody User user) throws Exception {
		Authreponce auth = userService.createPingmeAccout(user);
		return auth;
	}

	@PostMapping("/")
	public String welcome() throws Exception {

		return "welcome to RushiSocialApp";
	}

	@PutMapping("/api/user/update")
	public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws Exception {

		User updateUser = userService.findUserfromJwt(jwt);

		User usernew = userService.updateUser(user, updateUser.getId());

		return usernew;
	}

	@PostMapping("/signin")
	public Authreponce loginUser(@RequestBody User user) throws Exception {
		Authreponce auth = userService.loginUser(user.getEmail(), user.getPassword());

		return auth;
	}

	@GetMapping("/api/getalluser")
	public List<User> getUserList(@RequestParam String query) {

		List<User> userList = userService.getUserByName(query);

		return userList;
	}

	@GetMapping("/api/getuserjwt")
	public User getUserByjwt(@RequestHeader("Authorization") String jwt) {

		User userList = userService.findUserfromJwt(jwt);

		return userList;
	}

	@GetMapping("/api/getusername")
	public List<User> getUserByName(@RequestBody User user) {

		return this.userService.findByUserName(user.getFirstName());
	}

}
