package com.greenpay.web;

import java.security.Principal;
import java.util.List;

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

import com.greenpay.domain.PurchaseHistory;
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
    @ModelAttribute
    UserForm registUserForm() {
        return new UserForm();
    }

    //ユーザー仮登録画面
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
        userService.create(user);
        userService.sendMail(user);
        return "/registuserSuccess";
    }

    //ユーザー本登録画面
    @GetMapping("/registuserFinishForm")
    String registUserFinishForm() {
        return "registUserFinishForm";
    }

    @RequestMapping(value = "user/top", method = RequestMethod.GET)
    String usertop() {
        return "user/top";
    }

    // 利用履歴閲覧画面
    @RequestMapping(value = "user/history", method = RequestMethod.GET)
    String purchaseHistory(Model model, Principal principal) {
        User user = userService.findOne(principal.getName());
        List<PurchaseHistory> history = purchaseHistoryService.findByMoneyId(user);
        model.addAttribute("history", history);
        return "user/history/index";
    }
}
