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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "money_id", nullable = false)
	private String moneyId;

	@Column(name = "store_id", nullable = false)
	private String storeId;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "created_at", nullable = false)
	private String createdAt;
}
