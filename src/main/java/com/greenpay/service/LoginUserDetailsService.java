package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenpay.domain.User;
import com.greenpay.repository.UserRepository;

@Service(value="user")
public class LoginUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("ユーザー情報を参照中...");
		User user = userRepository.findOne(email);
		if(user == null){
			throw new UsernameNotFoundException("The requested user is not found.");
		} else if(user.getActivated() != 1){
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		System.out.println("ユーザー情報発見");
		return new LoginUserDetails(user);
	}
}
