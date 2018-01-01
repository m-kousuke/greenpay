package com.greenpay.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
	
	@RequestMapping(value="store/top",method=RequestMethod.GET)
	String StoreTop(){
		return "store/top";
	}
}
