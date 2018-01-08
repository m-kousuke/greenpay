package com.greenpay.web;

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
	String search(@RequestParam String user_email, Model model, Principal principal) {
		Money money = chargemoneyservice.findOne(user_email);
		//if (money == null) {
		//	return "redirect:/money";
		//}
		model.addAttribute("money", money);
		System.out.println(user_email);

		return "/checkBalance";
	}
	
	@RequestMapping(value = "/selectAmountOfMoney", method = RequestMethod.GET)
	String selectAmountOfMoney(){
		
		return "/selectAmountOfMoney";
	}
	
	@RequestMapping(value = "/chargeconfirm", method = RequestMethod.POST)
	String confirm(@RequestParam String chargemoney, Model model, Principal principal) {
		model.addAttribute("money",chargemoney);
		return "/chargeconfirm";

	}
	
	
	
	

	
}