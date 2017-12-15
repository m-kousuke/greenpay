package com.greenpay.domain;

import java.math.BigDecimal;
import java.security.Timestamp;

public class MoneyCharges {
	private int id = 0;
	private String money_id = null;
	private BigDecimal charged_amount = null;
	private Timestamp charged_at = null;
	
	public MoneyCharges(){
	}
	
	public MoneyCharges(int id,String money_id,BigDecimal charged_amount,Timestamp charged_at){
		this.id=id;
		this.money_id = money_id;
		this.charged_amount = charged_amount;
		this.charged_at = charged_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoney_id() {
		return money_id;
	}

	public void setMoney_id(String money_id) {
		this.money_id = money_id;
	}

	public BigDecimal getCharged_amount() {
		return charged_amount;
	}

	public void setCharged_amount(BigDecimal charged_amount) {
		this.charged_amount = charged_amount;
	}

	public Timestamp getCharged_at() {
		return charged_at;
	}

	public void setCharged_at(Timestamp charged_at) {
		this.charged_at = charged_at;
	}

}
