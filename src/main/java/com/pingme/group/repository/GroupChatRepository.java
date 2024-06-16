package com.pingme.group.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pingme.group.model.GroupChat;
import com.pingme.user.model.User;

public interface GroupChatRepository extends JpaRepository<GroupChat, Integer> {

	public  List<GroupChat> findByUsers(User users);

	public Optional<GroupChat> findByGroupName(String groupName);
	
	@Query("select g from GroupChat g where g.groupName LIKE %:query%")
	public List<GroupChat> searchByGroupName(@Param("query") String query);

}
