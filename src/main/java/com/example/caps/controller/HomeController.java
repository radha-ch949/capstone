package com.example.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.caps.global.GlobalData;
import com.example.caps.service.CategoryService;
import com.example.caps.service.ProductService;

@Controller


public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@GetMapping({"/","/home"})
	public String home(Model model)
	{
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
		
	}
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
		
	}
	@GetMapping("/shop/Category/{id}")
	public String shopByCategory(Model model,@PathVariable int id)
	{
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProductByCategoryId(id));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewproduct(Model model,@PathVariable Long id)
	{
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		
		return "viewproduct";
	}
	@RequestMapping(value="/do-stuff")
	public String doStuffMethod() {
		return "/doStuff";
	}
	

}
