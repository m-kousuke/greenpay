package com.greenpay.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.greenpay.domain.Store;

import lombok.Data;

@Data
public class LoginStoreUserDetails extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	private final Store store;
	
	public LoginStoreUserDetails(Store store){
		super(store.getId(), store.getPassword(), AuthorityUtils.createAuthorityList("ROLE_STORE"));
		this.store = store;
	}
}
