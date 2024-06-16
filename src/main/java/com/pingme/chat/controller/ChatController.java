package com.pingme.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.chat.model.Chat;
import com.pingme.chat.request.ChatRequest;
import com.pingme.chat.service.ChatService;
import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;
import com.pingme.user.service.UserService;

@RestController
public class ChatController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ChatService chatService;

	@PostMapping("/api/chat/create")
	public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody ChatRequest chatRequest)
			throws Exception {
		User user = userService.findUserfromJwt(jwt);

		User requser1 = userService.searchByfirstname(chatRequest.getFirstName());

		Chat createchat = chatService.createChatByContactandfirstname(requser1, user);

		return createchat;
	}

	@GetMapping("/api/getallchat")
	public List<Chat> allChat(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserfromJwt(jwt);

		List<Chat> chatall = chatService.getAllchatid(user.getId());

		return chatall;

	}

//	@GetMapping("/getchatname")
//	public List<Chat> chatByChatName( @RequestHeader("Authorization") String jwt, @RequestBody ChatRequest chat  ) {
//
//		User userList = userService.findUserfromJwt(jwt);
//		
//
//		
//		List<Chat> chatName =chatService.findBychatname(chat.getFirstName());
//
//		return chatName;
//
//	}
	@GetMapping("/api/getchatname")
	public Chat chatByChatName(@RequestHeader("Authorization") String jwt, @RequestBody ChatRequest chat) {

		User userList = userService.findUserfromJwt(jwt);

		Chat chatName = chatService.findBychatname(chat.getFirstName());

		return chatName;

	}

	@GetMapping("/api/getcontact")
	public List<Chat> chatByContact(@RequestBody Chat chat) {

		List<Chat> chatcontact = chatService.findBychatcontact(chat.getContact());

		return chatcontact;

	}

}
