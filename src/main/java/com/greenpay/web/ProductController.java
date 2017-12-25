package com.greenpay.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenpay.domain.Category;
import com.greenpay.domain.Product;
import com.greenpay.service.CategoryService;
import com.greenpay.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@ModelAttribute
	ProductForm sertUpForm() {
		return new ProductForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String index(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		return "product/index";
	}

	// 商品登録フォーム
	@RequestMapping(value = "create", method = RequestMethod.GET)
	String createForm(Model model) {
		// カテゴリーリストを生成
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "product/create";
	}
}
