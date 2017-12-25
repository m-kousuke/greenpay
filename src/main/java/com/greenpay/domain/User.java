package com.greenpay.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String createdAt;
	@Column(name = "updated_at", nullable = false)
	private String updatedAt;
}
