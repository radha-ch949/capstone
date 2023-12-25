package com.example.caps.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.caps.model.Role;
import com.example.caps.model.User;
import com.example.caps.repository.RoleRepository;
import com.example.caps.repository.UserRepository;

@Component
public class Google0Auth2SucessHandler implements AuthenticationSuccessHandler {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
	//	OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//		String email = token.getPrincipal().getAttributes().get("email").toString();
	//	if(userRepository.findUserByEmail(email).isPresent()) {
	//} else {
		User user = new User();
		//user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
		//user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
	//	user.setEmail(email);
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userRepository.save(user);
	}
//		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
	//}
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		
	}
	
	
}
