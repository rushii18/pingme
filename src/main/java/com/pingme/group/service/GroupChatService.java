package com.pingme.group.service;

import java.util.List;

import com.pingme.group.grouprequest.GroupRequest;
import com.pingme.group.model.GroupChat;

import com.pingme.user.model.User;

public interface GroupChatService {
	
	public GroupChat createGroupUser( GroupChat groupChat  , User usersAdd) throws Exception;
	
	public GroupChat addUserinGroup(GroupChat groupChat , User user) throws Exception;
	
	public String deleteGroup(Integer groupid) throws Exception;
	
	public GroupChat findByGroupId(Integer groupId) throws Exception;
	
	public List<GroupChat> findByGroupChatName(String groupnme);
	
	public GroupChat findByGroupName(String groupName);
	
	public List<GroupChat> getAllgroups();
	
	public List<GroupChat> getAllGroupByUser(User user);
	
	public GroupChat searchByGroupName( String name) throws Exception;
	
	

}
