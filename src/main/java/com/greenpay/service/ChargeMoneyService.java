
package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Money;
import com.greenpay.repository.MoneyRepository;

@Service
public class ChargeMoneyService {
	@Autowired
	MoneyRepository moneyrepository;
	public Money findOne(String userId) {
		System.out.println(userId);
		return moneyrepository.findOne(userId);
	}

}
