package com.greenpay.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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

	public void create(User user) {
		String dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
		user.setCreatedAt(dateTime);
		user.setUpdatedAt(dateTime);
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
}
