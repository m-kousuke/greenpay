package com.greenpay.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value="/user/loginForm",method=RequestMethod.GET)
	String userloginform(){
		return "user/loginForm";
	}
	
	@RequestMapping(value="/store/loginForm",method=RequestMethod.GET)
	String storeloginform(){
		return "store/loginForm";
	}
}
