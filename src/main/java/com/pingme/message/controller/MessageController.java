package com.pingme.message.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.chat.model.Chat;
import com.pingme.chat.service.ChatService;
import com.pingme.group.model.GroupChat;
import com.pingme.group.service.GroupChatService;
import com.pingme.message.model.Message;
import com.pingme.message.service.MessageService;
import com.pingme.user.model.User;
import com.pingme.user.service.UserService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;

	@PostMapping("/message/{chatid}")
	public Message sendMessage(@RequestBody Message message, @PathVariable Integer chatid) throws Exception {

		Chat chat = chatService.findByid(chatid);

		Message createMessage = messageService.senMessage(chat, message);

		return createMessage;
	}

	@PostMapping("/groupmesage/{groupid}")
	public Message sendMessageToGroup(@RequestBody Message message, @PathVariable Integer groupid) throws Exception {

		Message createMessage = messageService.groupMessage(groupid, message);

		return createMessage;
	}
}
