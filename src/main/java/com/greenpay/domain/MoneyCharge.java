package com.greenpay.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="money_charges")
@AllArgsConstructor
@NoArgsConstructor
public class MoneyCharge {
	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private int id;

	@Column(name="money_id",nullable=false)
	private String moneyId;

	@Column(name="charged_amount",nullable=false)
	private BigDecimal chargedAmount;

	@Column(name="charged_at",nullable=false)
	private String chargedAt;
}