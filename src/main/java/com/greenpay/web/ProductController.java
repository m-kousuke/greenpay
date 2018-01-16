package com.greenpay.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.greenpay.service.CategoryService;
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

	// 商品一覧(topページ)
	@RequestMapping(method = RequestMethod.GET)
	String index(Model model, Principal principal) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("storeId", principal.getName());
		return "store/product/index";
	}

	// 多店舗の商品一覧
	@RequestMapping(value = "otherStore", method = RequestMethod.GET)
	String otherStore(Model model, Principal principal) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("storeId", principal.getName());

		return "store/product/otherStore";
	}

	// 商品登録フォーム
	@RequestMapping(value = "create", method = RequestMethod.GET)
	String createForm(Model model) {
		// カテゴリーリストを生成
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "store/product/createForm";
	}

	// 商品登録
	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated ProductForm productForm, BindingResult result, Model model, Principal principal) {
		// 入力チェック
		if (result.hasErrors()) {
			return createForm(model);
		}

		// 商品登録
		Product product = new Product();
		BeanUtils.copyProperties(productForm, product);
		product.setStoreId(principal.getName());

		// DBに同一商品がないかのチェック
		Product rs = productService.isEmpty(product);
		if (rs != null) {
		    // 無効の場合、状態を有効に変更する
		    if(rs.getActivated() == 0) {
		        rs.setActivated(2);
		        rs.setPrice(product.getPrice());
		        rs.setCategoryId(product.getCategoryId());
		        productService.update(rs);
		        return "redirect:/store/product";
		    }

			result.rejectValue("name", null , "その商品名はすでに登録されています");
			return createForm(model);
		}

		productService.create(product);

		return "redirect:/store/product";
	}

	// 商品編集フォーム
	@RequestMapping(value = "update", method = RequestMethod.GET)
	String updateForm(@RequestParam Integer id, Model model,
			Principal principal) {
		Product product = productService.findOne(id);

		// storeIdのチェック
		if (!(product.getStoreId()).equals(principal.getName())) {
			return "redirect:/store/product";
		}

		model.addAttribute("product", product);

		// カテゴリーリストを生成
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "store/product/updateForm";
	}

	// 商品編集
	@RequestMapping(value = "update", method = RequestMethod.POST)
	String update(@RequestParam Integer id, @RequestParam String storeId,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam LocalDateTime createdAt,
			@Validated ProductForm productForm, BindingResult result, Model model, Principal principal) {
		// 入力チェック
		if (result.hasErrors()) {
			return updateForm(id, model, principal);
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
}
