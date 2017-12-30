package com.greenpay.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "last_name_kana", nullable = false)
	private String lastNameKana;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "first_name_kana", nullable = false)
	private String firstNameKana;

	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "activated", nullable = false)
	private int activated;

	@Column(name = "created_at", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime updatedAt;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	private Money money;
}
