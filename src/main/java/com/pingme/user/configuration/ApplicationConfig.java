package com.pingme.user.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pingme.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found From this Email " + username));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authDao = new DaoAuthenticationProvider();

		authDao.setUserDetailsService(userDetailsService());
		authDao.setPasswordEncoder(passwordEncoder());
		return authDao;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
