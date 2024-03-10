package com.pingme.refreshtoken.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.refreshtoken.model.RefreshToken;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Integer>{

	Optional<RefreshToken> findByToken(String token_1);
	
}
