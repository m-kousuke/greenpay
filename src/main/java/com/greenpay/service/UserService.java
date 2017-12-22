package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.User;
import com.greenpay.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void create(User user) {
		System.out.println(user);
		userRepository.save(user);
	}
}
