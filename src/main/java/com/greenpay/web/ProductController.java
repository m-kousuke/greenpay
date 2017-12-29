package com.greenpay.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenpay.domain.Category;
import com.greenpay.domain.Product;
import com.greenpay.domain.Store;
import com.greenpay.service.CategoryService;
import com.greenpay.service.LoginStoreUserDetails;
import com.greenpay.service.ProductService;

@Controller
@RequestMapping("store/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@ModelAttribute
	ProductForm productForm() {
		return new ProductForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String index(Model model, @AuthenticationPrincipal LoginStoreUserDetails storeDetails) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("store", storeDetails.getStore());

		return "store/product/index";
	}

	// 商品登録フォーム
	@RequestMapping(value = "create", method = RequestMethod.GET)
	String createForm(Model model) {
		// カテゴリーリストを生成
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "store/product/create";
	}

	// 商品登録
	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated ProductForm productForm, BindingResult result,
			@AuthenticationPrincipal LoginStoreUserDetails storeDetails) {
		// 入力チェック
		if (result.hasErrors()) {
			return "store/product/create";
		}

		// 商品登録
		Product product = new Product();
		BeanUtils.copyProperties(productForm, product);
		productService.create(product, storeDetails.getStore());

		return "redirect:/store/product";
	}

	// 商品編集フォーム
	@RequestMapping(value = "update", method = RequestMethod.GET)
	String updateForm(@RequestParam Integer id, Model model,
			@AuthenticationPrincipal LoginStoreUserDetails storeDetails) {
		// storeIdのチェック
		Product product = productService.findOne(id);
		Store store = storeDetails.getStore();
		if (!(product.getStoreId()).equals(store.getId())) {
			return "redirect:/store/product";
		}
		model.addAttribute("product", product);

		// カテゴリーリストを生成
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "store/product/update";
	}

	// 商品編集
	@RequestMapping(value = "update", method = RequestMethod.POST)
	String update(@RequestParam Integer id, @RequestParam String storeId, @RequestParam String createdAt,
			@Validated ProductForm productForm, BindingResult result) {
		// 入力チェック
		if (result.hasErrors()) {
			return updateForm(id, null, null);
		}

		// 商品編集
		Product product = new Product();
		BeanUtils.copyProperties(productForm, product);
		product.setId(id);
		product.setStoreId(storeId);
		product.setCreatedAt(createdAt);
		productService.update(product);

		return "redirect:/store/product";
	}

	// 商品削除
	@RequestMapping(path = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		productService.delete(id);
		;

		return "redirect:/store/product";
	}
}
