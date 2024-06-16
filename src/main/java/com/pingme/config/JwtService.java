package com.pingme.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service
public class JwtService {

	final static String SECRET_KEY = "9C4183A2D29A1A1BA4645159A5DB2jnfusegfsehjffsfsvgyef";
	final static String TOKEN_HEADER = "Authorization";

	public String extractUsername(String jwt) {
		return extractClaim(jwt, Claims::getSubject);

	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public boolean isTokenValid(String jwt, UserDetails userDetails) {
		final String userName = extractUsername(jwt);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpird(jwt));
	}

	private boolean isTokenExpird(String jwt) {

		return extractExpiration(jwt).before(new Date());
	}

	private Date extractExpiration(String jwt) {
		return extractClaim(jwt, Claims::getExpiration);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis())).setIssuer(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 240 )).signWith(getSignInkey())
				.compact();
	}

	public Claims extractAllClaims(String jwt) {
		return Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(jwt).getBody();
	}

	public <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(jwt);
		return claimResolver.apply(claims);
	}

	private Key getSignInkey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
