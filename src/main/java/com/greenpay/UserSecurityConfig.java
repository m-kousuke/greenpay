package com.greenpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Order(1)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**","/css/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/user/**")
			.authorizeRequests()
			.antMatchers("/user/loginForm").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.formLogin()
			.loginProcessingUrl("/user/login")
			.loginPage("/user/loginForm")
			.failureUrl("/user/loginForm?error")
			.defaultSuccessUrl("/user/top",true)
			.usernameParameter("email").passwordParameter("password")
			.and()
			.logout()
				.logoutSuccessUrl("/index");
	}
	
	//パスワードのハッシュ化のアルゴリズムを指定
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	protected static class  AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		UserDetailsService userDetailsService;
		
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}
	}
}
