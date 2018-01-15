
package com.greenpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Money;
import com.greenpay.domain.MoneyCharge;
import com.greenpay.domain.User;
import com.greenpay.repository.MoneyChargeRepository;
import com.greenpay.repository.MoneyRepository;
import com.greenpay.repository.UserRepository;

@Service
public class ChargeMoneyService {
	@Autowired
	UserRepository userrepository;
	@Autowired
	MoneyRepository moneyrepository;
	@Autowired
	MoneyChargeRepository moneychargerepository;
	
	public User findOne(String email) {
		User user =userrepository.findOne(email);
		return  user;
	}
	public Money update(User user, String chargemoney) {
		BigDecimal charge = new BigDecimal(chargemoney );
		BigDecimal credit = user.getMoney().getCredit().add(charge);
		LocalDateTime dateTime = LocalDateTime.now();
		Money money = user.getMoney();
		money.setCredit(credit);
		money.setUpdatedAt(dateTime);
		moneyrepository.save(money);
		MoneyCharge moneycharge = new MoneyCharge();
		moneycharge.setMoneyId(money.getId());
		moneycharge.setChargedAmount(charge);
		moneycharge.setChargedAt(dateTime);
		moneychargerepository.save(moneycharge);
		return money;
	}

}
