package com.example.caps.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.caps.dta.ProductDTO;
import com.example.caps.model.Admin;
import com.example.caps.model.Category;
import com.example.caps.model.Product;
import com.example.caps.model.User;
import com.example.caps.repository.UserRepository;
import com.example.caps.service.CategoryService;
import com.example.caps.service.CouponService;
import com.example.caps.service.CustomAdminDetailsService;
import com.example.caps.service.ProductService;

@Controller

public class AdminController {
	public static String uploadDir=System.getProperty("user.dir") +"\\caps\\src\\main\\resources\\static\\productsimage";
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomAdminDetailsService adminDetailsService;
////admin section//
//	@GetMapping("")
//	public String adminHome() {
//		return "index";
//	}
//	@GetMapping("/adminreg/adminlogin")
//	public String adminHome2(Model model) {
//		return "adminreg/adminlogin";
//	}
//	@GetMapping("/adminreg/adminregi")
//	public String adminHome3(Model model) {
//		return "adminreg/adminregi";
//	}
//	@PostMapping("/register_process")
//	public String processRegister(Admin user) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		
//		UserRepository.save(user);
//		
//		return "adminHome";
//	}
	@RequestMapping("/admin")
	public String adminHome1() {
		return "adminHome";
	}
	@RequestMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	@RequestMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
	categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<Category> category=categoryService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}else
			return "404";
	}
	
///product section///
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file, 
			@RequestParam("imgName")String imgName) throws IOException{
		Product product = new Product();
	    product.setId(productDTO.getId());
	    product.setName(productDTO.getName());
	    product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
	    product.setPrice(productDTO.getPrice());
	    product.setCar_model(productDTO.getCar_model());
	    product.setDescription(productDTO.getDescription());
	    String imageUUID;
	    if(file.isEmpty()) {
	    	imageUUID=file.getOriginalFilename();
	    	Path fileNameAndPath=Paths.get(uploadDir, imageUUID);
	    	Files.write(fileNameAndPath,file.getBytes());
	    }
	    else {
	    	imageUUID=imgName;
	    }
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
		
}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
	productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable Long id, Model model) {
		Product product=productService.getProductById(id).get();
		ProductDTO productDTO=new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		 productDTO.setCategoryId((product.getCategory().getId()));
		    productDTO.setPrice(product.getPrice());
		    productDTO.setCar_model(product.getCar_model());
		    productDTO.setDescription(product.getDescription());
		    
		    productDTO.setImageName(product.getImageName());
		    model.addAttribute("categories",categoryService.getAllCategory());
		    model.addAttribute("productDTO",productDTO);
		    return "productsAdd";
		    
		
	}
	
	///coupon section///
	
	@GetMapping("/admin/Addcoupon")
	public String Addcoupon(Model model) {
		model.addAttribute("Addcoupon", CouponService.getAllCoupon());
	  return "Addcoupon";
	}
	@GetMapping("/admin/CouponList")
	public String GetCoupon(Model model) {
		model.addAttribute("Addcoupon", CouponService.getAllCoupon());
		return "redirect:/CouponList";
	}
	
}
