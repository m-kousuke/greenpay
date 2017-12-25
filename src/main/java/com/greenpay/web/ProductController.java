package com.greenpay.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.greenpay.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@ModelAttribute
	ProductForm sertUpForm() {
		return new ProductForm();
	}
}
