package com.pingme.group.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.GapContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public GroupChat createGroupUser(GroupChat groupChat, User usersAdd) {

		GroupChat groupChatCreate = new GroupChat();

		groupChatCreate.setGroupName(groupChat.getGroupName());
		groupChatCreate.setGroupImage(groupChat.getGroupImage());
		groupChatCreate.getUsers().add(usersAdd);

		GroupChat savegrGroupChat = groupChatRepository.save(groupChatCreate);

		return savegrGroupChat;
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
	public GroupChat addUserinGroup(User user, GroupChat groupChat) throws Exception {

		GroupChat adduser = findByGroupId(groupChat.getId());

		if (adduser.getUsers().contains(user)) {
			throw new Exception("this user is already add in group  " + groupChat.getGroupName());
		}

		adduser.getUsers().add(user);

		GroupChat savegroup = groupChatRepository.save(adduser);

		return savegroup;

	}

	@Override
	public List<GroupChat> findByGroupChatName(GroupChat groupChat) {

		List<GroupChat> gc = groupChatRepository.findByGroupName(groupChat.getGroupName());

		return gc;
	}

}
