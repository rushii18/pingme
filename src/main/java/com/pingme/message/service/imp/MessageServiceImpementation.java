package com.pingme.message.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pingme.chat.model.Chat;
import com.pingme.chat.repo.ChatRepository;
import com.pingme.file.FileContent;
import com.pingme.file.repo.FileRepository;
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
	private FileRepository fileRepository;

	@Autowired
	private GroupChatRepository groupChatRepository;

	@Override
	public Message senMessage(Chat chat, Message message, User user, MultipartFile filContent) {

		return null;
	}

	@Override
	public Message sendMessageOneToOne(Chat chat, Message message, User user) {

		Message message1 = new Message();
		Optional<Chat> chat1 = chatRepository.findById(chat.getId());
		message1.setChat(chat1.get());
		message1.setTextMessage(message.getTextMessage());

		message1.setUser(user);
		message1.setSenderUser(user.getFirstName());
		message1.setChatid(message.getChatid());

		message1.setVideo(message.getVideo());
		message1.setTimeStamp(LocalDateTime.now());

		chat1.get().getMessage().add(message1);

		Message saveMessage = messageRepository.save(message1);

		Chat chatsaveMessage = chatRepository.save(chat1.get());

		return saveMessage;
	}

	@Override
	public Message sendMessageGroup(GroupChat groupChat, Message message, User user) {

		Message message1 = new Message();
		Optional<GroupChat> gc = groupChatRepository.findById(message.getGroupid());
		message1.setGroupChat(gc.get());

		message1.setTextMessage(message.getTextMessage());
		message1.setGroupid(message.getGroupid());

		message1.setUser(user);
		message1.setSenderUser(user.getFirstName());

		message1.setTimeStamp(LocalDateTime.now());

		gc.get().getMessages().add(message1);

		Message saveMessage = messageRepository.save(message1);
		GroupChat groupChat3 = groupChatRepository.save(gc.get());

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

	@Override
	public List<Message> getAllmessgae(Integer chatid) {

		List<Message> lm = messageRepository.findBychatId(chatid);

		return lm;
	}

	@Override
	public List<Message> getAllgroupMessgae(Integer groupid) {
		List<Message> lg = messageRepository.findByGroupid(groupid);
		return lg;
	}

	@Override
	public List<Message> sendMessageOnetoOneList(Integer chatid, List<Message> message, User sendUser) {
		Optional<Chat> chat = chatRepository.findById(chatid);
		Optional<User> user = userRepository.findById(sendUser.getId());
		Message message1 = new Message();
		message1.setChat(chat.get());
		message1.setUser(user.get());
		chat.get().getMessage().addAll(message);

		this.chatRepository.save(chat.get());

		return this.messageRepository.saveAll(message);
	}

}
