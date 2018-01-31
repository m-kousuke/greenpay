package com.greenpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.greenpay.service.LoginService;



@EnableWebSecurity
@Order(2)
public class StoreSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	LoginService loginService;
	
	@Autowired
	@Qualifier("storeHandler")
	SuccessHandler successHandler;
	@Bean
	SuccessHandler successHander() {
		return new SuccessHandler();
	}
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
			.loginPage("/store/loginForm").permitAll()
			.successHandler(successHandler)
			.failureUrl("/store/loginForm?error")
			//.defaultSuccessUrl("/authentication",true)
			.usernameParameter("id").passwordParameter("password")
			.and()
			.logout()
				.logoutUrl("/**/logout")
				.logoutSuccessUrl("/index");
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("store")
	UserDetailsService userDetailsService;
	@Autowired
	HttpSession session;
	
	
	@Autowired
	public void init(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Configuration(value="storeHandler")
	public class SuccessHandler implements AuthenticationSuccessHandler{
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication auth) throws IOException, ServletException {
			if(!loginService.Authentication(auth.getName())){
				response.sendRedirect("/greenpay/store/top");
			} else {
				auth.setAuthenticated(false);
				response.sendRedirect("/greenpay/store/loginForm");
			}
		}
	}
}
