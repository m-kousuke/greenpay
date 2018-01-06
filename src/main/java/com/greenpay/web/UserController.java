package com.greenpay.web;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

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
import com.greenpay.domain.PurchaseHistory;
import com.greenpay.domain.PurchaseHistoryDetail;
import com.greenpay.domain.User;
import com.greenpay.service.PurchaseHistoryDetailService;
import com.greenpay.service.PurchaseHistoryService;
import com.greenpay.service.UserService;

@Controller
public class UserController {

	    @Autowired
    UserService userService;

    @Autowired
    PurchaseHistoryService purchaseHistoryService;

    @Autowired
    PurchaseHistoryDetailService purchaseHistoryDetailService;


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
		userService.regist(user);
		userService.sendMail(user.getEmail());
		return "/registuserSuccess";
	}

	// ユーザー本登録画面
	@GetMapping("/registuserfinishForm")
	String registUserFinishForm(@RequestParam("id") String id, Model model) {
		model.addAttribute("id", id);
		return "registuserfinishForm";
	}

	// 本登録
	@PostMapping("/registuserfinish")
	String finish(@Validated UserFinishForm form, BindingResult result, Model model) {
		if (result.hasErrors()) { // エラーがおきたら返す場所
			return "/registuserfinishForm";
		}

		//復号化
		String salt = new String(Hex.encode("123454321".getBytes()));
		TextEncryptor decryptor = Encryptors.queryableText("key", salt);
		form.setUserId(decryptor.decrypt(form.getUserId()));

		//パスワードの照合とユーザー情報の取得
		User user = userService.findOne(form.getUserId(), form.getPassword());
		if (user != null) {
			Money money = new Money();
			BeanUtils.copyProperties(form, money);
			userService.registMoney(money);
			userService.registFinish(user);
			return "/registuserfinishSuccess";
		} else {
			return "/registuserfinishForm";
		}
	}

    // 利用履歴閲覧画面
    @RequestMapping(value = "user/history", method = RequestMethod.GET)
    String purchaseHistory(Model model, Principal principal) {
        User user = userService.AuthenticatedUser(principal.getName());
        List<PurchaseHistory> history = purchaseHistoryService.findByMoneyId(user);
        model.addAttribute("history", history);
        return "user/history/index";
    }

    // 利用履歴閲覧画面
    @RequestMapping(value = "user/history", method = RequestMethod.POST)
    String purchaseHistory(@RequestParam Integer id, @RequestParam BigDecimal amount, Model model, Principal principal) {
        List<PurchaseHistoryDetail> details = purchaseHistoryDetailService.findByPurchaseId(id);
        model.addAttribute("details", details);
        model.addAttribute("amount", amount);
        return "user/history/detail";
    }

  @RequestMapping(value="user/top" , method=RequestMethod.GET)
	String usertop(Principal principal,Model model) {
		User user = userService.AuthenticatedUser(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("money",user.getMoney());
		return "user/top";
	}
}
