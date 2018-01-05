package com.greenpay.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenpay.domain.Money;
import com.greenpay.domain.User;
import com.greenpay.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userservice;

	// 新規ユーザー登録フォーム
	@ModelAttribute("model1")
	UserForm registUserForm() {
		return new UserForm();
	}

	@ModelAttribute("model2")
	UserFinishForm registUserFinishForm() {
		return new UserFinishForm();
	}

	// ユーザー仮登録画面
	@GetMapping("/registuserForm")
	String registuserForm() {
		return "registuserForm";
	}

	// 仮登録
	@PostMapping("/registuser")
	String temporary(@Validated UserForm form, BindingResult result, Model model) {
		if (result.hasErrors()) { // エラーがおきたら返す場所
			return "/registuserForm";
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userservice.regist(user);
		userservice.sendMail(user.getEmail());
		return "/registuserSuccess";
	}

	// ユーザー本登録画面
	@GetMapping("/registuserFinishForm")
	String registUserFinishForm(@RequestParam("id") String id, Model model) {
		model.addAttribute("id", id);
		return "registUserFinishForm";
	}

	// 本登録
	@PostMapping("/registuserFinish")
	String finish(@Validated UserFinishForm form, BindingResult result, Model model) {
		if (result.hasErrors()) { // エラーがおきたら返す場所
			return "/registuserFinishForm";
		}

		//復号化
		String salt = new String(Hex.encode("123454321".getBytes()));
		TextEncryptor decryptor = Encryptors.queryableText("key", salt);
		form.setUserId(decryptor.decrypt(form.getUserId()));

		//パスワードの照合とユーザー情報の取得
		User user = userservice.findOne(form.getUserId(), form.getPassword());
		if (user != null) {
			Money money = new Money();
			BeanUtils.copyProperties(form, money);
			userservice.registMoney(money);
			userservice.registFinish(user);
			return "/registuserSuccess";
		} else {
			return "/registuserFinishForm";
		}
	}

	@RequestMapping(value = "user/top", method = RequestMethod.GET)
	String usertop() {
		return "user/top";
	}
}
