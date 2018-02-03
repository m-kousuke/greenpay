package com.greenpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.Money;
import com.greenpay.repository.MoneyRepository;

@Service
@Transactional
public class MoneyService {

	@Autowired
	MoneyRepository moneyRepository;

	public void registMoney(Money money) {
		BigDecimal decimal = new BigDecimal("0");
		money.setCredit(decimal);
		money.setCreatedAt(LocalDateTime.now());
		money.setUpdatedAt(LocalDateTime.now());
		moneyRepository.save(money);
	}

}
