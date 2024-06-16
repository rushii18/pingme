package com.pingme.message.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pingme.chat.model.Chat;
import com.pingme.file.FileContent;
import com.pingme.group.model.GroupChat;
import com.pingme.message.model.Message;
import com.pingme.user.model.User;

public interface MessageService {

	public Message sendMessageOneToOne(Chat chat, Message message , User senduser );
	
	public List<Message> sendMessageOnetoOneList(Integer chatid , List<Message> message, User sendUser );
	
	public Message senMessage(Chat chat, Message message , User user , MultipartFile file );

	public Message sendMessageGroup(GroupChat groupChat, Message message , User user );
	
	public Message groupMessage(Integer groupid , Message message );
	
	public List<Message> getAllmessgae(Integer chatid);
	
	public List<Message> getAllgroupMessgae(Integer groupid);
	

	 
}
