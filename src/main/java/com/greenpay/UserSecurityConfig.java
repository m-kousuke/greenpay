package com.greenpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.greenpay.service.LoginService;



@EnableWebSecurity
@Order(1)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	LoginService loginService;
	
	@Autowired
	@Qualifier("userHandler")
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
		http.antMatcher("/user/**")
			.authorizeRequests()
			.antMatchers("/user/loginForm").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.formLogin()
			.loginProcessingUrl("/user/login").permitAll()
			.loginPage("/user/loginForm")
			.failureUrl("/user/loginForm?error")
			.successHandler(successHandler)
			//.defaultSuccessUrl("/user/top",true)
			.usernameParameter("email").passwordParameter("password")
			.and()
			.logout()
				.logoutUrl("/**/logout")
				.logoutSuccessUrl("/index");
	}
	
	//パスワードのハッシュ化のアルゴリズムを指定
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	@Qualifier("user")
	UserDetailsService userDetailsService;
	@Autowired
	public void init(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Configuration(value="userHandler")
	public class SuccessHandler implements AuthenticationSuccessHandler{
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication auth) throws IOException, ServletException {
			if(loginService.Authentication(auth.getName())){
				response.sendRedirect("/greenpay/user/top");
			} else {
				auth.setAuthenticated(false);
				response.sendRedirect("/greenpay/user/loginForm");
			}
		}
	}
	
}
