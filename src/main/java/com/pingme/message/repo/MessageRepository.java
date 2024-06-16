package com.pingme.message.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.message.model.Message;
import com.pingme.chat.model.Chat;


public interface MessageRepository extends JpaRepository<Message, Integer> {

	public List<Message> findBychatId(Integer chatid);
	
	public List<Message> findByGroupid(Integer groupid);
}
