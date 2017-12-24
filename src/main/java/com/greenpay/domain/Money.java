package com.greenpay.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "money")
@AllArgsConstructor
@NoArgsConstructor
public class Money {
	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "credit", nullable = false)
	private BigDecimal credit;

	@Column(name = "credit_at", nullable = false)
	private String createdAt;

	@Column(name = "updated_at", nullable = false)
	private String updatedAt;
}