package com.pingme.user.controller;

import java.util.List;
import java.util.Optional;

import com.pingme.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.responce.Authreponce;

import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;
import com.pingme.user.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;
	

	@PostMapping("/signup")
	public Authreponce signup(@RequestBody User user) throws Exception {
		Authreponce auth = userService.createPingmeAccout(user);
		return auth;
	}

	@PutMapping("/api/user/updat")
	public User updateUser(@RequestBody User user,@RequestHeader("Authorization") String jwt ) throws Exception {
		
          String name = jwtService.extractUsername(jwt);
		Optional<User> userUpdate = userRepository.findByEmail(name);
		if(userUpdate.isPresent()){
			System.out.println("kfgeyfg");
		}

		//User userUpdate.get = userService.updateUser(user, userid);
		return userUpdate.get();
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
	
	@GetMapping("/api/getuserjwt")
	public User getUserByjwt(@RequestHeader("Authorization") String jwt) {

		User userList = userService.findUserfromJwt(jwt);

		return userList;
	}
}
