package com.greenpay.service;


import org.springframework.security.core.authority.AuthorityUtils;

import com.greenpay.domain.User;

import lombok.Data;

@Data
public class LoginUserDetails extends  org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	private final User user;
	
	public LoginUserDetails(User user){
		super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user =  user;
	}
}
