package com.pingme.user.configuration.jwtservice;

import java.security.Key;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "BYUERVF38Y238947BCRY132488473Y2489109320FDVDVEC";

	public <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaim(jwt);

		return claimResolver.apply(claims);

	}

	public String generateJwt(UserDetails userDetails) {

		return Jwts.builder().setIssuer(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isJwtTokenValid(String jwt, UserDetails userDetails) {
		final String username = extractUserName(jwt);
		return (username.equals(userDetails.getUsername()) && !isJwtTokenExpired(jwt));
	}

	public String extractUserName(String jwt) {

		return extractClaim(jwt, Claims::getSubject);
	}

	// to check the token expired
	private boolean isJwtTokenExpired(String jwt) {

		return extractExpiration(jwt).before(new Date());
	}

	private Date extractExpiration(String jwt) {

		return extractClaim(jwt, Claims::getExpiration);
	}

	// extractAllClaim from JWT token
	private Claims extractAllClaim(String jwt) {

		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJwt(jwt).getBody();
	}

	private Key getSignKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}

}
