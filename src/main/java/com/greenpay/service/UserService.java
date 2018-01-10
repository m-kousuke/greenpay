package com.greenpay.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.User;
import com.greenpay.repository.MoneyRepository;
import com.greenpay.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MoneyRepository moneyRepository;
	@Autowired
	private MailSender sender;
	@Autowired
	PasswordEncoder passwordEncoder;

	//登録されているかのチェック
	public boolean check(String email){
		boolean rs = userRepository.exists(email);
		if(rs == true){
			return true;
		}
		return false;
	}

	//ユーザー仮登録
	public void regist(User user) {
		LocalDateTime dateTime = LocalDateTime.now();
		user.setCreatedAt(dateTime);
		user.setUpdatedAt(dateTime);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	// メール送信
	public void sendMail(String email) {
		SimpleMailMessage msg = new SimpleMailMessage();
		String encrypted = encryption(email);
		msg.setFrom("greenpayroot@gmail.com");
		msg.setTo(email);
		msg.setSubject("本登録");// タイトルの設定
		String url = "http://localhost:8080/greenpay/registuserfinishForm?id=" +  encrypted;
		msg.setText("以下のURLから本登録を行ってください\n\r" + url + ""); // 本文の設定
		this.sender.send(msg);
	}

	// アドレスの暗号化
	public String encryption(String email) {
		String salt = new String(Hex.encode("123454321".getBytes()));
		TextEncryptor encryptor = Encryptors.queryableText("key", salt);
		String encrypted = encryptor.encrypt(email);
		return encrypted;
	}

	// ユーザー情報の取得
	public User findOne(String email, String password) {
		User user = userRepository.findOne(email);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	//ユーザー本登録
	public void registFinish(User user) {
		LocalDateTime dateTime = LocalDateTime.now();
		user.setActivated(1);
		user.setCreatedAt(dateTime);
		user.setUpdatedAt(dateTime);
		userRepository.save(user);
	}


	public User AuthenticatedUser(String userId){
		return userRepository.findOne(userId);
	}
}
