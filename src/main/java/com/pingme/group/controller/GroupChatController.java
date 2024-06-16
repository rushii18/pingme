package com.pingme.group.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.group.grouprequest.GroupRequest;
import com.pingme.group.model.GroupChat;
import com.pingme.group.service.GroupChatService;
import com.pingme.user.model.User;
import com.pingme.user.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GroupChatController {

	@Autowired
	private GroupChatService groupChatService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/newgroup")
	public GroupChat createGroup(@RequestBody GroupChat groupChat, @RequestHeader("Authorization") String jwt)
			throws Exception {

		User user = userService.findUserfromJwt(jwt);

		GroupChat groupChat2 = groupChatService.createGroupUser(groupChat, user);

		return groupChat2;
	}

	@PostMapping("/api/useraddgroup")
	public GroupChat userAddinGroup(@RequestBody GroupRequest groupRequest) throws Exception {
		User useradd = userService.findUserByid(groupRequest.getUserid());

		GroupChat gc = groupChatService.findByGroupId(groupRequest.getGroupid());

		GroupChat gc1 = groupChatService.addUserinGroup(gc, useradd);
		return gc1;
	}

	@GetMapping("/api/getgroupbyuser")
	public List<GroupChat> getGroups(@RequestHeader("Authorization") String token) {

		User user = userService.findUserfromJwt(token);

		List<GroupChat> lg = groupChatService.getAllGroupByUser(user);

		return lg;
	}

	@GetMapping("/api/getAllgroups")
	public List<GroupChat> getAllgroups() {
		List<GroupChat> lg = groupChatService.getAllgroups();
		return lg;
	}

	@GetMapping("/api/getgroups")
	public List<GroupChat> getGroupList(@RequestParam String query) {

		List<GroupChat> gpList = groupChatService.findByGroupChatName(query);

		return gpList;
	}
	
	@GetMapping("/api/group/{groupName}")
	public GroupChat getGroup(@PathVariable String groupName) throws Exception {

		GroupChat gpList = groupChatService.searchByGroupName(groupName);

		return gpList;
	}
	
	
	

}
