package com.pingme.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pingme.group.model.GroupChat;
import com.pingme.user.model.User;

public interface GroupChatRepository extends JpaRepository<GroupChat, Integer> {

//	@Query("select G from GroupChat G where :user Member of G.users")
//	public GroupChat findByUsersId(@Param("user") User user);
//	
	public List<User> findByUsers(User users);

	public List<GroupChat> findByGroupName(String groupName);

}
