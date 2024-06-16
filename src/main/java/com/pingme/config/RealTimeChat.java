package com.pingme.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.message.model.Message;
import com.pingme.message.service.MessageService;

@Controller
public class RealTimeChat {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private MessageService messageService;

	@MessageMapping("/chat/{chatId}")
	public Message sendToUser(@Payload Message message , @DestinationVariable String chatId) {
		simpMessagingTemplate.convertAndSendToUser(chatId, "/private", message);
		//System.out.println(message);
		
		List<Message> listOfMessage = new ArrayList<>();
		listOfMessage.add(message);
		if(listOfMessage.size()==10) {
			this.messageService.sendMessageOnetoOneList(message.getChatid(), listOfMessage, message.getUser());
		}
		System.out.println(listOfMessage.toString());
	
		return message;
	}

	@MessageMapping("/group/{groupId}")
	public Message sendToGroup(@Payload Message message , @DestinationVariable String groupId) {
		simpMessagingTemplate.convertAndSendToUser(groupId, "/private", message);
		//System.out.println(message);		
		return message;
	}
	
}
