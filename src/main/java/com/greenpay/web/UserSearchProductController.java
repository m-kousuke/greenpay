package com.greenpay.web;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenpay.domain.Product;
import com.greenpay.domain.Store;
import com.greenpay.service.ProductService;
import com.greenpay.service.StoreService;

@Controller
public class UserSearchProductController {

	@Autowired
	StoreService storeService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="user/searchproductForm",method=RequestMethod.GET)
	String SearchProductForm(Model model){
		List<Store> stores = storeService.findAll();
		model.addAttribute("stores",stores);
		return "user/searchproductForm";
	}
	
	@RequestMapping(value="user/search",method=RequestMethod.POST)
	String SearchProduct(@RequestParam String word,@RequestParam String storeId,Model model){
		List<Product> products = productService.findByNameAndStoreId(word,storeId);
		if(products.size()>0){
		List<String> categoryList = productService.GetCategoryListForProductSerach(products);
		model.addAttribute("categoryList",categoryList);
		}
		model.addAttribute("products", products);
		return "user/productlist";
	}
}
