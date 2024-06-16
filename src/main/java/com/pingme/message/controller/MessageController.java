package com.pingme.message.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pingme.chat.model.Chat;
import com.pingme.chat.service.ChatService;
import com.pingme.file.FileContent;
import com.pingme.file.repo.FileRepository;
import com.pingme.group.model.GroupChat;
import com.pingme.group.service.GroupChatService;
import com.pingme.message.messagerequest.MessageRequest;
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
	private GroupChatService groupChatService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/cretemessage")
	public Message sendMessageDoc(@RequestBody Message message, @RequestHeader("Authorization") String jwt)
			throws Exception {

		Chat chat = chatService.findByid(message.getChatid());

		User usersender = userService.findUserfromJwt(jwt);

		Message createMessagenew = messageService.sendMessageOneToOne(chat, message, usersender);
				

		return createMessagenew;

	}
	@GetMapping("/api/getAllmessage/{chatid}")
	public List<Message> getAllmessage (@PathVariable("chatid") Integer chatid ){
		
		List<Message> getMessage = messageService.getAllmessgae(chatid);
		
		return getMessage;
		
	}

	@PostMapping("/api/groupmesage")
	public Message sendMessageToGroup(@RequestBody Message message, @RequestHeader("Authorization") String jwt)
			throws Exception {

		User user = userService.findUserfromJwt(jwt);

		GroupChat gc = groupChatService.findByGroupId(message.getGroupid());

		Message createMessage = messageService.sendMessageGroup(gc, message, user);
		
		return createMessage;
	}

	
	@GetMapping("/api/getAllgroupmessage/{groupid}")
	public List<Message> getAllgroupMessage (@PathVariable("groupid") Integer groupid ){
		
		List<Message> getMessage = messageService.getAllgroupMessgae(groupid);
		
		return getMessage;
		
	}
	
	@PostMapping("/api/creteofMessage/{chatid}")
	public List<Message> sendlistOfMessge(List<Message> messages , @PathVariable Integer chatid, @RequestHeader("Authorization") String token ){
		User user = userService.findUserfromJwt(token);
		List<Message> messageList = messageService.sendMessageOnetoOneList(chatid, messages,user );
		return  messageList ;
	}
}
