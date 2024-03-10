package com.pingme.group.service;

import java.util.List;

import com.pingme.group.model.GroupChat;

import com.pingme.user.model.User;

public interface GroupChatService {
	
	public GroupChat createGroupUser( GroupChat groupChat  , User usersAdd);
	
	public GroupChat addUserinGroup(User user , GroupChat groupChat) throws Exception;
	
	public String deleteGroup(Integer groupid) throws Exception;
	
	public GroupChat findByGroupId(Integer groupId) throws Exception;
	
	public List<GroupChat> findByGroupChatName(GroupChat groupChat);
	
	

}
