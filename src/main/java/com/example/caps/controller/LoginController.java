package com.example.caps.controller;

import com.example.caps.repository.RoleRepository;

import com.example.caps.model.User;
import com.example.caps.repository.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
   @Autowired
   private BCryptPasswordEncoder bcryptPasswordEncoder;
   @Autowired
   UserRepository userRepository;
   @Autowired
   RoleRepository roleRepository;

   @GetMapping("/login1")
   public String login(){
      return "login1";
   }
   @GetMapping("/register")
   public String registerGet(Model model) {
		model.addAttribute("user", new User());
      return "register";
   }
   @PostMapping("/process_register")
   public String registerpost(@ModelAttribute("user")User user, HttpServletRequest request) throws ServletException {
	   String password = user.getPassword();
	   user.setPassword(bcryptPasswordEncoder.encode(password));
	   List<com.example.caps.model.Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userRepository.save(user);
		request.login(user.getEmail(),password);
		return "redirect:/";
   }
}

