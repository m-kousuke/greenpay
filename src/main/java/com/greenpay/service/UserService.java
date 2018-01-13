package com.greenpay.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.User;
import com.greenpay.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private MailSender sender;
	@Autowired
	PasswordEncoder passwordEncoder;

	public void create(User user) {
		LocalDateTime dateTime = LocalDateTime.now();
		user.setCreatedAt(dateTime);
		user.setUpdatedAt(dateTime);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void sendMail(User user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("greenpayroot@gmail.com");
		msg.setTo(user.getEmail());
		msg.setSubject("本登録");// タイトルの設定
		msg.setText("以下のURLから本登録を行ってください\n\r" + "http://localhost:8080/greenpay/registUserFinishForm"); // 本文の設定

		this.sender.send(msg);
	}

	public boolean check(String rawPassword, String hash) {
		return passwordEncoder.matches(rawPassword, hash);
	}

	public void edit(User user,String newPassword) {
		LocalDateTime dateTime = LocalDateTime.now();
		user.setUpdatedAt(dateTime);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}


	public User AuthenticatedUser(String userId) {
		return userRepository.findOne(userId);
	}
}
