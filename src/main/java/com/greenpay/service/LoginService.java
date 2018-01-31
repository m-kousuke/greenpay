package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;
	
	public boolean Authentication(String id){
		return userRepository.exists(id);
	}
	
}
