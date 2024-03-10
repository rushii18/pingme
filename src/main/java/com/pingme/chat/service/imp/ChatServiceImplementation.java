package com.pingme.chat.service.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.chat.model.Chat;
import com.pingme.chat.repo.ChatRepository;
import com.pingme.chat.service.ChatService;
import com.pingme.user.model.User;
import com.pingme.user.service.UserService;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserService userService;

	@Override
	public String deleteChat(Integer chatid) {
		Optional<Chat> delelte = chatRepository.findById(chatid);

		if (delelte.isPresent())
			this.chatRepository.deleteById(chatid);

		return "Chat id is deleted";
	}

	@Override
	public Chat findByid(Integer chatid) throws Exception {
		Optional<Chat> chat = chatRepository.findById(chatid);

		if (chat.isPresent()) {
			return chat.get();

		}

		throw new Exception("chat id NotFound");

	}

	@Override
	public Chat createChatByContactandfirstname(User reqUser, User user) throws Exception {

		Chat isexist = chatRepository.findChatByUsersId(reqUser, user);

		if (isexist != null) {
			return isexist;
		}

		Chat chatcreate = new Chat();

		chatcreate.setChatName(reqUser.getFirstName());
		chatcreate.setContact(reqUser.getContact());
		chatcreate.setTimeStamp(LocalDate.now());

		chatcreate.getUsers().add(reqUser);
		chatcreate.getUsers().add(user);

		Chat chatSave = chatRepository.save(chatcreate);

		return chatSave;

	}

	@Override
	public List<Chat> getAllchatid() {
		List<Chat> allchat = chatRepository.findAll();
		return allchat;
	}

	@Override
	public List<Chat> findBychatname(String chatname) {
	
		List<Chat> chat = chatRepository.findChatBychatName(chatname);
		
		return chat;
	}

	@Override
	public List<Chat> findBychatcontact(String contact) {
	
		List<Chat> chatcontct = findBychatcontact(contact);
		
		return chatcontct;
	}

}
