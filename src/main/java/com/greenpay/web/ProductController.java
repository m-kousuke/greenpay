package com.greenpay.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

	// 商品登録
	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated ProductForm productForm, BindingResult result/*, @AuthenticationPrincipal LoginStoreDetails storeDetails*/) {
		// 入力チェック
		if (result.hasErrors()) {
			return "product/create";
		}

		// 商品登録
		Product product = new Product();
		BeanUtils.copyProperties(productForm,  product);
		// productService.create(product, storeDetails.getName());
		productService.create(product);

		return "redirect:/product";
	}
}
