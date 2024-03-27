package com.pingme.message.service.imp;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.chat.model.Chat;
import com.pingme.chat.repo.ChatRepository;

import com.pingme.group.model.GroupChat;
import com.pingme.group.repository.GroupChatRepository;
import com.pingme.message.model.Message;
import com.pingme.message.repo.MessageRepository;
import com.pingme.message.service.MessageService;
import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;

@Service
public class MessageServiceImpementation implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupChatRepository groupChatRepository;

	@Override
	public Message senMessage(Chat chat, Message message) {

		Message message1 = new Message();
		Chat chat1 = chatRepository.findByChatName(chat.getChatName());
		message1.setTextMessage(message.getTextMessage());

		message1.setVideo(message.getVideo());

		message1.setChat(chat1);
		chat1.getMessage().add(message1);

		Message saveMessage = messageRepository.save(message1);

		Chat chatsaveMessage = chatRepository.save(chat1);

		return saveMessage;
	}

	@Override
	public Message sendMessageOneToOne(User revicedUser, Message message) {

		User user = userRepository.findByfirstName(revicedUser.getFirstName());
		Message message1 = new Message();

		message1.setTextMessage(message.getTextMessage());

		// message1.setFile(message.getFile());

		message1.setUser(user);
		message1.setRecivedUserName(revicedUser.getFirstName());
		message1.setTimeStamp(LocalDateTime.now());

		Message saveMessage = messageRepository.save(message1);

		return saveMessage;
	}

	@Override
	public Message sendMessageGroup(GroupChat groupChat, Message message) {

		Message message1 = new Message();
		GroupChat groupChat2 = new GroupChat();

		message1.setTextMessage(message.getTextMessage());

		// message1.setFile(message.getFile());
		message1.setGroupChat(groupChat2);

		message1.setTimeStamp(LocalDateTime.now());

		groupChat2.getMessages().add(message1);

		GroupChat groupChat3 = groupChatRepository.save(groupChat2);
		Message saveMessage = messageRepository.save(message1);

		return saveMessage;
	}

	@Override
	public Message groupMessage(Integer groupid, Message message) {
		Message message1 = new Message();
		Optional<GroupChat> groupChat2 = groupChatRepository.findById(groupid);

		message1.setTextMessage(message.getTextMessage());

		// message1.setFile(message.getFile());
		message1.setGroupChat(groupChat2.get());

		message1.setTimeStamp(LocalDateTime.now());

		groupChat2.get().getMessages().add(message1);

		GroupChat groupChat3 = groupChatRepository.save(groupChat2.get());
		Message saveMessage = messageRepository.save(message1);

		return saveMessage;
	}

}
