package com.pingme.group.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.group.model.GroupChat;
import com.pingme.group.service.GroupChatService;
import com.pingme.user.model.User;
import com.pingme.user.service.UserService;

@RestController
public class GroupChatController {

	@Autowired
	private GroupChatService groupChatService;

	@Autowired
	private UserService userService;

	@PostMapping("/group/{userid}")
	public GroupChat createGroup(@RequestBody GroupChat groupChat, @PathVariable Integer userid) throws Exception {

		User user = userService.findUserByid(userid);

		GroupChat groupChat2 = groupChatService.createGroupUser(groupChat, user);

		return groupChat2;
	}

	@PostMapping("/useraddgroup/{userid}/{groupid}")
	public GroupChat userGropList(@PathVariable Integer userid, @PathVariable Integer groupid) throws Exception {
		User user = userService.findUserByid(userid);

		GroupChat gc = groupChatService.findByGroupId(groupid);

		GroupChat gc1 = groupChatService.addUserinGroup(user, gc);
		return gc1;
	}
	
	@GetMapping("/getgroup")
	public List<GroupChat> getGroup(@RequestBody GroupChat groupChat) {
		
		List<GroupChat> gc = groupChatService.findByGroupChatName(groupChat);
		
		return gc;
	}

}
