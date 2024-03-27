package com.pingme.user.service;

import java.util.List;

import com.pingme.responce.Authreponce;
import com.pingme.user.model.User;

public interface UserService {

	public Authreponce createPingmeAccout(User user) throws Exception;

	public User updateUser(User user , Integer userid) throws Exception;

	public Authreponce loginUser(String email, String password) throws Exception;

	public User searchByfirstname(String firstName);

	public User searchByContact(String conatact);

	public User findUserByid(Integer id) throws Exception;
	
	public List<User> getAllUser();
	
	public User findUserfromJwt(String jwt);
	
}
