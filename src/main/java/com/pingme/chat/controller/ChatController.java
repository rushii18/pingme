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
import com.pingme.user.service.UserService;

@RestController
public class ChatController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@PostMapping("/chat/{user}")
	public Chat createChat(@PathVariable Integer user, @RequestBody ChatRequest chatRequestid) throws Exception {

		User requser1 = userService.findUserByid(chatRequestid.getUserid());

		User reqUser2 = userService.findUserByid(user);

		Chat createchat = chatService.createChatByContactandfirstname(requser1, reqUser2);

		return createchat;
	}

	@GetMapping("/getallchat")
	public List<Chat> allChat() {
		List<Chat> chatall = chatService.getAllchatid();

		return chatall;

	}

	@GetMapping("/getchatname")
	public List<Chat> chatByChatName(@RequestBody Chat chat) {

		List<Chat> chatName = chatService.findBychatname(chat.getChatName());

		return chatName;

	}

	@GetMapping("/getcontact")
	public List<Chat> chatByContact(@RequestBody Chat chat) {

		List<Chat> chatcontact = chatService.findBychatcontact(chat.getContact());

		return chatcontact;

	}

}
