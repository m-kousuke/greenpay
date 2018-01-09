package com.greenpay.web;

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
import com.greenpay.domain.User;
import com.greenpay.service.ChargeMoneyService;

@Controller
public class ChargeMoneyController {
	@Autowired
	ChargeMoneyService chargemoneyservice;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/money", method = RequestMethod.GET)
	String index() {
		return "/money";
	}

	@RequestMapping(value = "/checkBalance", method = RequestMethod.POST)
	String search(@RequestParam String email, Model model, Principal principal, RedirectAttributes attributes) {
		User user = chargemoneyservice.findOne(email);
		if (user == null) {
			attributes.addFlashAttribute("errorMessage","登録メールアドレスを確認後もう一度入力して下さい");
			return "redirect:/money";
		}
		model.addAttribute("user", user);
		model.addAttribute("money", user.getMoney());
		// セッションへ保存
		if(session.getAttribute("user") == null) {
		session.setAttribute("user", user);
		}
		return "/checkBalance";
	}

	@RequestMapping(value = "/selectAmountOfMoney", method = RequestMethod.GET)
	String selectAmountOfMoney(Model model) {
		
		if(session.getAttribute("user") == null) {
			return  "/money";
		}
		User user =(User) session.getAttribute("user");
		model.addAttribute("money",user.getMoney());
		return "/selectAmountOfMoney";
	}

	@RequestMapping(value = "/chargeConfirm", method = RequestMethod.POST)
	String confirm(@RequestParam String chargemoney, Model model, Principal principal) {
		if(session.getAttribute("user") == null) {
			return  "/money";
		}
		User user =(User) session.getAttribute("user");
		model.addAttribute("money",user.getMoney());
		model.addAttribute("chargemoney", chargemoney);
		return "/chargeConfirm";

	}

	@RequestMapping(value = "/chargeComplete", method = RequestMethod.POST)
	String updete(@RequestParam String chargemoney, Model model, Principal principal) {
		if(session.getAttribute("user") == null) {
			return  "/money";
		}
		
		User user =(User) session.getAttribute("user");
		Money money =chargemoneyservice.update(user,chargemoney);
		
		model.addAttribute("money", money);
		
		session.removeAttribute("user");
		return "/chargeComplete";

	}

}