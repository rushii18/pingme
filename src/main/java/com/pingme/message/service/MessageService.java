package com.pingme.message.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pingme.chat.model.Chat;
import com.pingme.file.FileContent;
import com.pingme.group.model.GroupChat;
import com.pingme.message.model.Message;
import com.pingme.user.model.User;

public interface MessageService {

	public Message sendMessageOneToOne(User revicedUser, Message message);

	public Message senMessage(Chat chat, Message message  );

	public Message sendMessageGroup(GroupChat groupChat, Message message);
	
	public Message groupMessage(Integer groupid , Message message );

}
