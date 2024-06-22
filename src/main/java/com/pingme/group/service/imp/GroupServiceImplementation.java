package com.pingme.group.service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.GapContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.group.grouprequest.GroupRequest;
import com.pingme.group.model.GroupChat;

import com.pingme.group.repository.GroupChatRepository;
import com.pingme.group.service.GroupChatService;
import com.pingme.user.model.User;
import com.pingme.user.service.UserService;

@Service
public class GroupServiceImplementation implements GroupChatService {

	@Autowired
	private GroupChatRepository groupChatRepository;

	@Autowired
	private UserService userService;

	
	@Override
	public GroupChat createGroupUser(GroupChat groupChat, User usersAdd) throws Exception {

		Optional<GroupChat> gp = groupChatRepository.findByGroupName(groupChat.getGroupName());

		if (gp.isPresent()) {
			throw new Exception("group alredy exist");
			// return gp.get();
		} else {
			GroupChat groupChatCreate = new GroupChat();

			groupChatCreate.setGroupName(groupChat.getGroupName());
			groupChatCreate.setGroupImage(groupChat.getGroupImage());
			groupChatCreate.getUsers().add(usersAdd);
			groupChatCreate.setTimeStamp(LocalDate.now());

			return groupChatCreate = groupChatRepository.save(groupChatCreate);
		}

	}

	@Override
	public String deleteGroup(Integer groupid) throws Exception {

		Optional<GroupChat> id = groupChatRepository.findById(groupid);
		if (id.isEmpty()) {
			throw new Exception("Group Id not founf" + groupid);
		}

		return "delete Successfull " + groupid;
	}

	@Override
	public GroupChat findByGroupId(Integer groupId) throws Exception {

		Optional<GroupChat> id = groupChatRepository.findById(groupId);
		if (id.isEmpty()) {
			throw new Exception("Group Id not founf" + groupId);
		}

		return id.get();
	}

	@Override
	public GroupChat addUserinGroup(GroupChat groupChat, User user) throws Exception {

		GroupChat adduser = findByGroupName(groupChat.getGroupName());

		User userName = userService.searchByfirstname(user.getFirstName());

		if (adduser.getUsers().contains(userName)) {
			throw new Exception("this user is already add in group  " + groupChat.getGroupName());
		}

		adduser.getUsers().add(userName);

		GroupChat savegroup = groupChatRepository.save(adduser);

		return savegroup;

	}

	@Override
	public List<GroupChat> findByGroupChatName(String groupChat) {

		List<GroupChat> gc = groupChatRepository.searchByGroupName(groupChat);

		return gc;
	}

	@Override
	public GroupChat findByGroupName(String groupName) {

		Optional<GroupChat> gc = groupChatRepository.findByGroupName(groupName);

		return gc.get();
	}

	@Override
	public List<GroupChat> getAllgroups() {
		List<GroupChat> lg = groupChatRepository.findAll();

		return lg;
	}

	@Override
	public List<GroupChat> getAllGroupByUser(User user) {

		List<GroupChat> lg = groupChatRepository.findByUsers(user);
		return lg;
	}

	@Override
	public GroupChat searchByGroupName(String name) throws Exception {
		Optional<GroupChat> gc = groupChatRepository.findByGroupName(name);
		if(gc.isEmpty()) {
			throw new Exception("this Group in not Present " + name);
		}

		return gc.get();
	}

}
