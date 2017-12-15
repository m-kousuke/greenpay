package com.greenpay.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="last_name_kana",nullable=false)
	private String lastnameKana;
	@Column(name="first_name",nullable=false)
	private String firstname;
	@Column(name="first_name_kana",nullable=false)
	private String firstnameKana;
	@Column(name="password",nullable=false)
	private String password;
}
