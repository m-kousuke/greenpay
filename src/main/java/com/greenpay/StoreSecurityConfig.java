package com.greenpay;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Order(2)
public class StoreSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**","/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/store/**")
			.authorizeRequests()
			.antMatchers("/store/loginForm").permitAll()
			.antMatchers("/store/**").hasRole("STORE")
			.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.formLogin()
		.loginProcessingUrl("/store/login")
			.loginPage("/store/loginForm")
			.failureUrl("/store/loginForm?error")
			.defaultSuccessUrl("/store/top",true)
			.usernameParameter("id").passwordParameter("password")
			.and()
			.logout()
				.logoutSuccessUrl("/index");
	}
}
