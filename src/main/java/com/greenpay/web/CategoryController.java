package com.greenpay.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenpay.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@ModelAttribute
	CategoryForm setUpForm() {
		return new CategoryForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String index() {
		return "category/index";
	}
}
