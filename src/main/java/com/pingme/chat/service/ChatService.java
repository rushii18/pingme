package com.pingme.chat.service;

import java.util.List;

import com.pingme.chat.model.Chat;
import com.pingme.user.model.User;

public interface ChatService {

	public String deleteChat(Integer chatid);

	public Chat findByid(Integer chatid) throws Exception;

	public Chat createChatByContactandfirstname(User reqUserid, User user) throws Exception;

	public List<Chat> getAllchatid();

	public List<Chat> findBychatname(String chatname);

	public List<Chat> findBychatcontact(String contact);

}
