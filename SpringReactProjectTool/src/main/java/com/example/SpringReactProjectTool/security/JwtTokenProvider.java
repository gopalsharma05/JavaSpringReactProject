package com.example.SpringReactProjectTool.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.example.SpringReactProjectTool.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.SpringReactProjectTool.security.SecurityConstants.SECRET;

import com.example.SpringReactProjectTool.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	
	//for generating the token
	public String generateToken(Authentication authentication)
	{
		User user=(User) authentication.getPrincipal();   //getting the user
		Date now =new Date(System.currentTimeMillis());
		Date expiryDate=new Date(now.getTime()+EXPIRATION_TIME);
		
		String userId=Long.toString(user.getId());
		
		Map<String , Object> claims=new HashMap<>();
		claims.put("id", Long.toString(user.getId()));
		claims.put("username", user.getUsername());
		claims.put("fullName", user.getFullName());
		
		
		return Jwts.builder()
		.setSubject(userId)
		.setClaims(claims)
		.setIssuedAt(now)
		.setExpiration(expiryDate)
		.signWith(SignatureAlgorithm.HS512,SECRET)
		.compact();  
		
		
		
	}
	
	
	
	// for validate the token
	
	public boolean validateToken(String token)
	{
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
			
		}
		catch(SignatureException ex)
		{
			System.out.println("Invalid JWT Signature");
		}
		catch(MalformedJwtException ex)
		{
			System.out.println("Invalid JWT token");
		}
		catch(ExpiredJwtException ex)
		{
			System.out.println("token has been expired");
		}
		catch(UnsupportedJwtException ex)
		{
			System.out.println("Unsupported JWT token");
		}
		catch(IllegalArgumentException ex)
		{
			System.out.println("JWT claims string is empty");
		}
		
		return false;
	}
	
	
	//getting the id from token
	
	
	public Long getUserIdFromJWT(String token)
	{
			Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			String id=(String)claims.get("id");
			return Long.parseLong(id);
	}
	
	
	

}
