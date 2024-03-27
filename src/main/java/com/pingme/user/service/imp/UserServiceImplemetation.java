package com.pingme.user.service.imp;

import java.util.List;
import java.util.Optional;


import com.pingme.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pingme.responce.Authreponce;

import com.pingme.user.model.Role;
import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;
import com.pingme.user.service.UserService;

@Service
public class UserServiceImplemetation implements UserService {

	@Autowired
	private UserRepository userRepository;


    @Autowired
	private JwtService jwtService;
	@Autowired
	public   PasswordEncoder passwordEncoder;

     @Autowired
	private  UserDetailsService userDetailsService;



    public Authreponce createPingmeAccout(User user) throws Exception {


		 Optional<User> userpresent = userRepository.findByEmail(user.getEmail());

		if (userpresent.isPresent()) {
			throw new Exception("this user is alreay exist");
		}
		User createUser = new User();
		createUser.setFirstName(user.getFirstName());
		createUser.setLastName(user.getLastName());
		createUser.setEmail(user.getEmail());
		createUser.setPassword(user.getPassword());
		createUser.setGender(user.getGender());
		createUser.setContact(user.getContact());
		// createUser.setRole(Role.admin);

		User saveUser = userRepository.save(createUser);

		Authreponce auth = new Authreponce(null, "Accout Create");

		return auth;
	}

	@Override
	public User updateUser(User user, Integer userid) throws Exception {

		Optional<User> userupdat = userRepository.findById(userid);

		System.out.println(userupdat);

		if (userupdat.isPresent()) {
			User userUpdate = userupdat.get();

			if (user.getFirstName() != null) {
				userUpdate.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null) {
				userUpdate.setLastName(user.getLastName());
			}

			if (user.getEmail() != null) {
				userUpdate.setEmail(user.getEmail());
			}
			if (user.getContact() != null) {
				userUpdate.setContact(user.getContact());
			}

			User userupdateall = userRepository.save(userUpdate);
			return userupdateall;
		}
		throw new Exception("id not exist" + userid);
	}

	@Override
	public Authreponce loginUser(String email, String password) throws Exception {

		UserDetails userDetails = userDetailsService.loadUserByUsername(email);

		if (email == userDetails.getUsername()) {

			throw new Exception("Faild to Login" + email);
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {

			throw new Exception("password is wrong");
		}

		 String token = jwtService.generateToken(userDetails);

		Authreponce auth = new Authreponce("login Success", token);
		return auth;

	}

	@Override
	public User searchByfirstname(String firstName) {

		User userSortByName = userRepository.findByfirstName(firstName);

		return userSortByName;
	}

	@Override
	public User searchByContact(String conatact) {

		User userSortByContact = userRepository.findBycontact(conatact);

		return userSortByContact;
	}

	@Override
	public User findUserByid(Integer id) throws Exception {

		Optional<User> userid = userRepository.findById(id);

		if (userid.isEmpty()) {

			throw new Exception("userid NotFound with" + id);
		}
		return userid.get();

	}

	@Override
	public List<User> getAllUser() {

		List<User> getAllUsers = userRepository.findAll();

		return getAllUsers;
	}

	public User findUserfromJwt(String jwt) {
        String name= jwtService.extractUsername(jwt);
		Optional<User> jwtUser = userRepository.findByEmail(name);
		return jwtUser.get();
	}



}
