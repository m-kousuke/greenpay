package com.greenpay.domain;

import java.math.BigDecimal;
import java.security.Timestamp;

public class Money {
	private String id = null;
	private String user_id = null;
	private BigDecimal credit = null;
	private Timestamp credit_at = null;
	private Timestamp updated_at = null;
	
	public Money(){
	}
	
	public Money(String id,String user_id,BigDecimal credit,Timestamp credit_at,Timestamp updated_at){
		this.id=id;
		this.user_id = user_id;
		this.credit = credit;
		this.credit_at = credit_at;
		this.updated_at = updated_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public Timestamp getCredit_at() {
		return credit_at;
	}

	public void setCredit_at(Timestamp credit_at) {
		this.credit_at = credit_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}	
	
}
