package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Store;
import com.greenpay.repository.StoreRepository;

@Service(value="store")
public class LoginStoreUserDetailsService implements UserDetailsService {
	@Autowired
	StoreRepository storeRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Store store = storeRepository.findOne(id);
		if (store == null) {
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new LoginStoreUserDetails(store);
	}

}
