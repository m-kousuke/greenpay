package com.greenpay.domain;

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
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="last_name_kana",nullable=false)
	private String last_name_kana;
	@Column(name="first_name",nullable=false)
	private String first_name;
	@Column(name="first_name_kana",nullable=false)
	private String first_name_kana;
	@Column(name="password",nullable=false)
	private String password;
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
}
