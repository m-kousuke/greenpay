package com.greenpay.web;

import java.security.Principal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenpay.domain.User;
import com.greenpay.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userservice;

		
	// 新規ユーザー登録フォーム
	@ModelAttribute
	UserForm registUserForm() {
		return new UserForm();
	}

	//ユーザー仮登録画面
	@GetMapping("/registuserForm")
	String registuserForm(Model model) {
		
		return "registuserForm";
	}

	@PostMapping("/registuser")
	String create(@Validated UserForm form, BindingResult result, Model model) {
		if (result.hasErrors()) { // エラーがおきたら返す場所
			return "/registuserForm";
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userservice.create(user);
		userservice.sendMail(user);
		return "/registuserSuccess";
	}

	//ユーザー本登録画面
	@GetMapping("/registuserFinishForm")
	String registUserFinishForm() {
		return "registUserFinishForm";
	}
	
	@RequestMapping(value="user/top" , method=RequestMethod.GET)
	String usertop(Principal principal,Model model) {
		User user = userservice.AuthenticatedUser(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("money",user.getMoney());
		return "user/top";
	}
}
