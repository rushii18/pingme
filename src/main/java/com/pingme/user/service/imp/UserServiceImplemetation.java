package com.pingme.user.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pingme.responce.Authreponce;
import com.pingme.user.configuration.jwtservice.JwtService;
import com.pingme.user.model.Role;
import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;
import com.pingme.user.service.UserService;

@Service
public class UserServiceImplemetation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	public Authreponce createPingmeAccout(User user) throws Exception {

		Optional<User> userpresent = userRepository.findByEmail(user.getEmail());

		if (userpresent.get() != null) {
			throw new Exception("this user is alreay exist");
		}
		User createUser = new User();
		createUser.setFirstName(user.getFirstName());
		createUser.setLastName(user.getLastName());
		createUser.setEmail(user.getEmail());
		createUser.setPassword(passwordEncoder.encode(user.getPassword()));
		createUser.setGender(user.getGender());
		createUser.setContact(user.getContact());
		createUser.setRole(Role.ADMIN);

		User saveUser = userRepository.save(createUser);

		String token = jwtService.generateJwt(saveUser);

		Authreponce auth = new Authreponce(token, "Accout Create");

		return auth;
	}

	@Override
	public User updateUser(User user, Integer userid) throws Exception {

		Optional<User> userupdat = userRepository.findById(userid);

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
			if (user.getPassword() != null) {
				userUpdate.setPassword(user.getPassword());
			}

			User userupdateall = userRepository.save(userUpdate);
			return userupdateall;
		}
		throw new Exception("id not exist" + userid);
	}

	@Override
	public Authreponce loginUser(String email, String password) throws Exception {
		System.out.println(email);
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);

		if (email == userDetails.getUsername()) {

			throw new Exception("Faild to Login" + email);
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {

			throw new Exception("password is wrong");
		}

		String token = jwtService.generateJwt(userDetails);

		Authreponce auth = new Authreponce(token, "Login Sucssefull");
		return auth;

	}

	@Override
	public List<User> searchByfirstname(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchByContact(String conatact) {
		// TODO Auto-generated method stub
		return null;
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

}
