package com.example.auth.serviceImpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

//import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtLoadItems {
	
	private final String secretkey="3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
	private final long expirationTime=3600000;//15 mins
	
	public String generateToken(User user) {
		Map<String,Object> claims=new HashMap();
		return createToken(claims,user);
	}
	
	public String createToken(Map<String,Object> claims,User user) {
		
		return Jwts.builder().setClaims(claims)
				.setSubject(user.getUserEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expirationTime))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key getSignKey() {
		byte[] byteKey=Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(byteKey);
	}
	
	public long getExpirationTime() {
		
		return expirationTime;
	}
	
	public String exactUserEmail(String token) {
		
		return exactClaims(token,Claims::getSubject);
	}
	
	public Date exactExpirationTime(String token) {
		
		return exactClaims(token,Claims::getExpiration);
	}
	
	private <T> T exactClaims(String token,Function<Claims,T> claimsResolver) {
		
		Claims claims = exactAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims exactAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey()).build()
				.parseClaimsJws(token).getBody();
	}
	
	public boolean isValidedToken(String token,User user) {
		
		String exactlyUserEmail = exactUserEmail(token);
		return (exactlyUserEmail.equals(user.getUserEmail()) && !isExpiredToken(token) );
	}
	
	public boolean isExpiredToken(String token) {
		
		return exactExpirationTime(token).before(new Date());
	}

}
