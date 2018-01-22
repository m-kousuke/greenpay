package com.greenpay.web;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenpay.domain.Calculate;
import com.greenpay.domain.Money;
import com.greenpay.service.CalculateService;
import com.greenpay.service.ChargeMoneyService;

@RequestMapping("store/calculate")
@Controller
public class CalculateController {
	@Autowired
	CalculateService calculateservice;
	@Autowired
	ChargeMoneyService chargemoneyservice;
	@Autowired
	HttpSession session;

	

	List<List<Calculate>> allList = new ArrayList<List<Calculate>>();//全売却商品リストを生成

	//売却商品を追加するフォームへ移る処理
	@RequestMapping(method = RequestMethod.GET)
	String index(Model model) {

		List<Calculate> calculateList = new ArrayList<Calculate>();//お客さんごとの売却商品リストを生成

		int number = allList.size();//お客さんごとの売却商品リストに個別番号を与える

		allList.add(calculateList);//全売却商品リストに生成した売却商品リストを追加

		model.addAttribute("calculateList", calculateList);
		model.addAttribute("number", number);
		return "/store/calculate/cashregister";
	}

	//売却商品を追加する処理
	@RequestMapping(value = "cashregister", method = RequestMethod.POST)
	String calcurate(@RequestParam String productId, @RequestParam String quantity, @RequestParam Integer number,
			Model model, Principal principal) {

		List<Calculate> calculateList = allList.get(number);//numberに該当する売却商品リストを全売却商品リストから取り出す

		String storeId = principal.getName();
		int check = calculateList.size();
		calculateList = calculateservice.findByIdAndStoreId(productId, Integer.parseInt(quantity), storeId,
				calculateList);
		BigDecimal total = BigDecimal.ZERO;
		if (calculateList.size() >= 1) {
			for (Calculate calculate : calculateList) {
				BigDecimal subtotal = calculate.getPrice().multiply(BigDecimal.valueOf(calculate.getQuantity()));
				total = total.add(subtotal);
			}
		}
		model.addAttribute("calculateList", calculateList);
		model.addAttribute("number", number);
		model.addAttribute("total", total);
		//取扱商品かどうかの判定
		if (check == calculateList.size()) {
			String msg = "この商品は取扱っておりません";
			model.addAttribute("nothingError", msg);
			return "/store/calculate/cashregister";
		}
		return "/store/calculate/cashregister";
	}

	//売却商品を確定し,電子マネー残高確認フォームへ移る処理
	@RequestMapping(value = "checkBalanceForm", method = RequestMethod.POST)
	String checkBalance(@RequestParam Integer number, @RequestParam BigDecimal total, Model model) {
		List<Calculate> calculateList = allList.get(number);
		session.setAttribute("calculateList", calculateList);
		session.setAttribute("number", number);
		session.setAttribute("total", total);
		return "/store/calculate/checkBalanceForm";
	}

	//一度売却商品を確定後,再度売却商品を追加するフォームへ移る処理
	@RequestMapping(value = "cashregister", method = RequestMethod.GET)
	String reCalcurate(Model model) {
		List<Calculate> calculateList = (List<Calculate>) session.getAttribute("calculateList");
		BigDecimal total = (BigDecimal) session.getAttribute("total");
		int number = (Integer) session.getAttribute("number");
		model.addAttribute("calculateList", calculateList);
		model.addAttribute("number", number);
		model.addAttribute("total", total);
		return "/store/calculate/cashregister";
	}

	@RequestMapping(value = "checkBalanceForm", method = RequestMethod.GET)
	String Error() {
		return "/store/calculate/checkBalanceForm";
	}

	//電子マネー残高検索
	@RequestMapping(value = "calculateComplete", method = RequestMethod.POST)
	String search(@RequestParam String id, Model model,Principal principal, RedirectAttributes attributes) {

		Money money = chargemoneyservice.findOne(id);
		if (money == null) {
			attributes.addFlashAttribute("errorMessage", "登録電子マネーIDを確認後もう一度入力して下さい");
			return "redirect:/store/calculate/checkBalanceForm";
		}
		BigDecimal total = (BigDecimal) session.getAttribute("total");

		session.setAttribute("money", money);
		//残高が足りてるとき
		if (total.compareTo(money.getCredit()) <= 0) {
			BigDecimal balance = calculateservice.updateOfCredit(money, total);//会計の処理
			String storeId = principal.getName();
			List<Calculate> calculateList = (List<Calculate>) session.getAttribute("calculateList");
			calculateservice.registPurchaseHistory(money,storeId,total,calculateList);//購入履歴登録の処理
			model.addAttribute("balance", balance);
			return "/store/calculate/calculateComplete";
		}
		//残高不足のとき
		else if (total.compareTo(money.getCredit()) == 1) {
			model.addAttribute("total", total);
			model.addAttribute("shortage", money.getCredit().subtract(total));//"shortage"：不足金額
			return "/store/calculate/compareBalance";
		}
		return "/store/calculate/checkBalanceForm";
	}

	//残高不足でチャージした後の清算の処理
	@RequestMapping(value = "chargeAndCalculate", method = RequestMethod.POST)
	String charge(@RequestParam String chargemoney, Model model,Principal principal) {
		Money money = (Money) session.getAttribute("money");
		BigDecimal total = (BigDecimal) session.getAttribute("total");
		BigDecimal charge = new BigDecimal(chargemoney);
		if (total.compareTo(money.getCredit().add(charge)) == 1) {//チャージ金額が足りるのかチェック
			model.addAttribute("errorMessage", "チャージ金額が不足しています");
			model.addAttribute("total", total);
			model.addAttribute("shortage", money.getCredit().subtract(total));//"shortage"：不足金額
			return "/store/calculate/compareBalance";
		}
		//チャージ金額が足りていた場合,以下の処理に移る
		money = chargemoneyservice.update(money, chargemoney);//チャージの処理
		BigDecimal balance = calculateservice.updateOfCredit(money, total);//会計の処理
		String storeId = principal.getName();
		List<Calculate> calculateList = (List<Calculate>) session.getAttribute("calculateList");
		calculateservice.registPurchaseHistory(money,storeId,total,calculateList);//購入履歴登録の処理
		model.addAttribute("balance", balance);
		return "/store/calculate/calculateComplete";

	}

	@RequestMapping(value = "compareBalance", method = RequestMethod.GET)
	String chargeError(Model model) {
		return "/store/calculate/calculateComplete";
	}

}
