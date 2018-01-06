package com.greenpay.web;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenpay.domain.Money;
import com.greenpay.service.ChargeMoneyService;


@Controller
public class ChargeMoneyController {
	@Autowired
	ChargeMoneyService chargemoneyservice;

	@RequestMapping(value = "/money", method = RequestMethod.GET)
	String index() {
		return "/money";
	}

	@RequestMapping(value = "/checkBalance", method = RequestMethod.POST)
	String search(@RequestParam String userId, Model model, Principal principal) {
		Money money = chargemoneyservice.findOne(userId);
		System.out.println(userId);
		//if (money == null) {
		//	return "redirect:/money?error";
		//}
		BigDecimal credit = money.getCredit();
		model.addAttribute("credit", credit);
		return "/checkBalance";
	}
	
	@RequestMapping(value = "/selectAmountOfMoney", method = RequestMethod.GET)
	String selectAmountOfMoney(){
		return "/selectAmountOfMoney";
	}
	
	
	
	

	
}