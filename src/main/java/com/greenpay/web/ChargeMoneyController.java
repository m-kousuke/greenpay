package com.greenpay.web;

import java.math.BigDecimal;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenpay.domain.Money;
import com.greenpay.service.ChargeMoneyService;

@Controller
public class ChargeMoneyController {
	@Autowired
	ChargeMoneyService chargemoneyservice;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/money", method = RequestMethod.GET)
	String index() {
		return "money";
	}

	@RequestMapping(value = "/checkBalance", method = RequestMethod.POST)
	String search(@RequestParam String id, Model model, RedirectAttributes attributes) {
		Money money = chargemoneyservice.findOne(id);
		if (money == null) {
			attributes.addFlashAttribute("errorMessage","登録電子マネーIDを確認後もう一度入力して下さい");
			return "redirect:/money";
		}
		model.addAttribute("money", money);
		// セッションへ保存
		if(session.getAttribute("money") == null) {
		session.setAttribute("money", money);
		}
		return "checkBalance";
	}
	@RequestMapping(value = "/checkBalance", method = RequestMethod.GET)
	String chackBalance(Model model) {
		Money money =(Money) session.getAttribute("money");
		model.addAttribute("money",money);
		return "checkBalance";
	}

	@RequestMapping(value = "/selectAmountOfMoney", method = RequestMethod.GET)
	String selectAmountOfMoney(Model model) {
		
		if(session.getAttribute("money") == null) {
			return  "money";
		}
		Money money =(Money) session.getAttribute("money");
		model.addAttribute("money",money);
		return "selectAmountOfMoney";
	}

	@RequestMapping(value = "/chargeConfirm", method = RequestMethod.POST)
	String confirm(@RequestParam String chargemoney, Model model,  RedirectAttributes attributes) {
		if(session.getAttribute("money") == null) {
			return  "money";
		}
		Money money =(Money) session.getAttribute("money");
		BigDecimal charge = new BigDecimal(chargemoney );
		model.addAttribute("money",money);
		if(money.getCredit().add(charge).doubleValue() > 20000) {
			attributes.addFlashAttribute("upperLimitError","チャージ上限は20,000円です。");
			return "redirect:/selectAmountOfMoney";
		}
		model.addAttribute("chargemoney", chargemoney);
		return "chargeConfirm";

	}

	@RequestMapping(value = "/chargeComplete", method = RequestMethod.POST)
	String updete(@RequestParam String chargemoney, Model model, Principal principal) {
		if(session.getAttribute("money") == null) {
			return "money";
		}
		
		Money money =(Money) session.getAttribute("money");
		money =chargemoneyservice.update(money,chargemoney);
		
		model.addAttribute("money", money);
		
		session.removeAttribute("money");
		return "chargeComplete";

	}

}