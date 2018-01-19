package com.greenpay.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenpay.domain.Calculate;
import com.greenpay.service.CalculateService;

@RequestMapping("store/calculate")
@Controller
public class CalculateController {
	@Autowired
	CalculateService calculateservice;

	@ModelAttribute
	CalculateForm calculateForm() {
		return new CalculateForm();
	}
    List<List<Calculate>> allList =  new ArrayList<List<Calculate>>();

	@RequestMapping(method = RequestMethod.GET)
	String index(Model model) {
		 List<Calculate> calculateList = new ArrayList<Calculate>();
		 int number = allList.size();
		 allList.add(calculateList);
		
        model.addAttribute("calculateList",calculateList);
        model.addAttribute("number",number);
		return "/store/calculate/cashregister";
	}

	@RequestMapping(value = "cashregister", method = RequestMethod.POST)
	String calcurate(@RequestParam String productId, @RequestParam String quantity, @RequestParam Integer number,
			@Validated CalculateForm calculateForm, BindingResult result, Model model, Principal principal) {
		// 入力チェック
		//if (result.hasErrors()) {
	      //  model.addAttribute("calculateList",calculateList);
			//return index(model);
		//}
		List<Calculate> calculateList = allList.get(number);
		String storeId = principal.getName();
		int check = calculateList.size();
		calculateList = calculateservice.findByIdAndStoreId(productId,Integer.parseInt(quantity),storeId,calculateList);
		model.addAttribute("calculateList",calculateList);
		model.addAttribute("number",number);
		if( check == calculateList.size()) {
			String msg="この商品は取扱っておりません"; 
			model.addAttribute("nothingError",msg);
			return "/store/calculate/cashregister";
		}
		return "/store/calculate/cashregister";
	}

}
