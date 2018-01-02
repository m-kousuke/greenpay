package com.greenpay.web;

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
import com.greenpay.service.CategoryService;

@Controller
@RequestMapping("store/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@ModelAttribute
	CategoryForm setUpForm() {
		return new CategoryForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String index(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "store/category/index";
	}

	// カテゴリー登録フォーム
	@RequestMapping(value = "create", method = RequestMethod.GET)
	String createForm() {
		return "store/category/create";
	}

	// カテゴリー登録
	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated CategoryForm categoryForm, BindingResult result) {
		// 入力チェック
		if (result.hasErrors()) {
			return "store/category/create";
		}

		// カテゴリー登録
		Category category = new Category();
		BeanUtils.copyProperties(categoryForm, category);
		categoryService.create(category);

		return "redirect:/store/category";
	}

	// カテゴリー編集フォーム
	@RequestMapping(value = "update", method = RequestMethod.GET)
	String updateForm(@RequestParam Integer id, Model model) {
		Category category = categoryService.findOne(id);
		model.addAttribute("category", category);
		return "store/category/update";
	}

	// カテゴリー編集
	@RequestMapping(value = "update", method = RequestMethod.POST)
	String update(@RequestParam Integer id,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam LocalDateTime createdAt,
			@Validated CategoryForm categoryForm, BindingResult result) {
		// 入力チェック
		if (result.hasErrors()) {
			return updateForm(id, null);
		}

		// カテゴリー編集
		Category category = new Category();
		BeanUtils.copyProperties(categoryForm, category);
		category.setId(id);
		category.setCreatedAt(createdAt);
		categoryService.update(category);

		return "redirect:/store/category";
	}

	// カテゴリー削除
	@RequestMapping(path = "update", params = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		categoryService.delete(id);;

		return "redirect:/store/category";
	}
}
