package com.greenpay.web;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenpay.domain.PurchaseHistory;
import com.greenpay.domain.PurchaseHistoryDetail;
import com.greenpay.domain.User;
import com.greenpay.service.PurchaseHistoryDetailService;
import com.greenpay.service.PurchaseHistoryService;
import com.greenpay.service.UserService;

@Controller
@RequestMapping("/user/history")
public class PurchaseHistoryController {
    @Autowired
    UserService userService;

    @Autowired
    PurchaseHistoryService purchaseHistoryService;

    @Autowired
    PurchaseHistoryDetailService purchaseHistoryDetailService;

    // 利用履歴
    @RequestMapping(method = RequestMethod.GET)
    String purchaseHistory(Model model, Principal principal) {
        User user = userService.AuthenticatedUser(principal.getName());
        List<PurchaseHistory> history = purchaseHistoryService.findByMoneyId(user);
        model.addAttribute("history", history);
        return "user/history/index";
    }

    // 利用履歴詳細
    @RequestMapping(method = RequestMethod.POST)
    String purchaseHistory(@RequestParam Integer id, @RequestParam BigDecimal amount, Model model,
            Principal principal) {
        List<PurchaseHistoryDetail> details = purchaseHistoryDetailService.findByPurchaseId(id);
        model.addAttribute("details", details);
        model.addAttribute("amount", amount);
        return "user/history/detail";
    }
}
