package com.greenpay.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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

	@Column(name = "user_email", nullable = false)
	private String userId;

	@Column(name = "credit", nullable = false)
	private BigDecimal credit;

	@Column(name = "created_at", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime updatedAt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_email",insertable=false, updatable=false,nullable=true)
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="money")
	private List<MoneyCharge> moneyCharges;
}
