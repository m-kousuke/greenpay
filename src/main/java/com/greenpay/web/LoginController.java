package com.greenpay.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value="/user/loginForm",method=RequestMethod.GET)
	String UserLoginForm(){
		return "user/loginForm";
	}
	
	@RequestMapping(value="/store/loginForm",method=RequestMethod.GET)
	String StoreLoginForm(){
		return "store/loginForm";
	}
}
