package com.greenpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.Money;
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

	public void regist(User user) {
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
		// String salt = KeyGenerators.string().generateKey(); //
		// SecureRandomの値をhex化
		// TextEncryptor encryptor = Encryptors.queryableText("password", salt);
		// String encrypted = encryptor.encrypt(user.getEmail());
		// String encodedResult = passwordEncoder.encode(user.getPassword());
		String url = "http://localhost:8080/greenpay/registuserFinishForm?id=" + user.getEmail();
		msg.setText("以下のURLから本登録を行ってください\n\r" + url + ""); // 本文の設定
		this.sender.send(msg);
	}

	public User findOne(String email, String password) {
		User user = userRepository.findOne(email);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	public void registFinish(User user) {
		user.setActivated(1);
		user.setCreatedAt(LocalDateTime.now());
		userRepository.save(user);
	}

	public void registMoney(Money money) {
		BigDecimal decimal = new BigDecimal("0");
		money.setCredit(decimal);
		money.setCreatedAt(LocalDateTime.now());
		money.setUpdatedAt(LocalDateTime.now());
		moneyRepository.save(money);
	}

	private BigDecimal BigDecimal() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
