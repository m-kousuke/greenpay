package com.greenpay.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.User;
import com.greenpay.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void create(User user) {
		LocalDateTime dateTime = LocalDateTime.now();
		user.setCreatedAt(dateTime);
		userRepository.save(user);
	}
}
