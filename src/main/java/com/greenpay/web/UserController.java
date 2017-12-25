package com.greenpay.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/registuserForm")
	String registuserForm() {
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
}
