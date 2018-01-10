package com.greenpay.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenpay.service.CalculateService;

@Controller
public class CalculateController {
	@Autowired
	CalculateService calculateservice;
	
	@RequestMapping(value = "/store/calculate/cashregister", method = RequestMethod.POST)
	String () {
		return "/money";
	}
	
}
