package com.example.SpringReactProjectTool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringReactProjectTool.domain.User;
import com.example.SpringReactProjectTool.exception.UsernameException;
import com.example.SpringReactProjectTool.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser)
	{
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));	
		 
			return userRepository.save(newUser);
		}
		catch(Exception e) {
			
			throw new UsernameException("Username " +newUser.getUsername()+" already exist in the system");
			
		}
	}
		
	
	
	
	
}
