package com.pingme.chat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pingme.chat.model.Chat;
import com.pingme.user.model.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	@Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

	public List<Chat> findChatBychatName(String chatName);
	
	public List<Chat> findChatBycontact(String contact);
	
	public List<Chat> findByUsersId(Integer chaid);

	public Chat findByChatName(String chatName);
}
