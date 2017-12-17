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
@Table(name="stores")
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private String id;
	@Column(name="lname",nullable=false)
	private String name;
	@Column(name="password",nullable=false)
	private String password;
}
