
package com.greenpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Money;
import com.greenpay.domain.MoneyCharge;
import com.greenpay.repository.MoneyChargeRepository;
import com.greenpay.repository.MoneyRepository;

@Service
public class ChargeMoneyService {
	@Autowired
	MoneyRepository moneyrepository;
	@Autowired
	MoneyChargeRepository moneychargerepository;
	
	public Money findOne(String id) {
		 Money money =moneyrepository.findOne(id);
		return  money;
	}
	public Money update(Money money, String chargemoney) {
		BigDecimal charge = new BigDecimal(chargemoney );
		BigDecimal credit = money.getCredit().add(charge);
		LocalDateTime dateTime = LocalDateTime.now();
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
