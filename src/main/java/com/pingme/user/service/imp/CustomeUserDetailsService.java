//package com.pingme.user.service.imp;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.pingme.user.model.User;
//import com.pingme.user.repository.UserRepository;
//
//@Service
//public class CustomeUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User loginUser = userRepository.findByEmail(username);
//
//		if (loginUser == null) {
//			throw new UsernameNotFoundException("with this Email userNoFound" + username);
//
//		}
//
//		List<GrantedAuthority> authorities = new ArrayList<>();
//
//		return new org.springframework.security.core.userdetails.User(loginUser.getEmail(), loginUser.getPassword(),
//				false, false, false, false, authorities);
//	}
//
//}
