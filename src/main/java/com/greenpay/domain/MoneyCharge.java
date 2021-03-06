package com.greenpay.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private int id;

	@Column(name="money_id",nullable=false)
	private String moneyId;

	@Column(name="charged_amount",nullable=false)
	private BigDecimal chargedAmount;

	@Column(name="charged_at",nullable=false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime chargedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="money_id",insertable=false,updatable=false)
	private Money money;
}