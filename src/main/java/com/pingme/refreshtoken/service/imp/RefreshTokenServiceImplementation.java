package com.pingme.refreshtoken.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.refreshtoken.model.RefreshToken;
import com.pingme.refreshtoken.repo.RefreshTokenRepository;
import com.pingme.user.model.User;
import com.pingme.user.repository.UserRepository;

@Service
public class RefreshTokenServiceImplementation {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public RefreshToken createRefreshtoken(String userName) {
		Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(userName);
		
		

		return null;
	}

}
