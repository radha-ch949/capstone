
package com.example.caps.configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.CachingUserDetailsService;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.example.caps.service.CustomUserDetailService;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter
//{
//@Autowired
//Google0Auth2SucessHandler google0Auth2SucessHandler;
//@Autowired
//CustomUserDetailService customUserDetailsService;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//	http
//		.authorizeRequests()
//		.antMatchers("/","/shop/**","/register","/h2-console/**").permitAll()
//		.antMatchers("/admin/**").hasRole("ADMIN")
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.failureUrl("/login?error= true")
//		.defaultSuccessUrl("/")
//		.usernameParameter("email")
//		.passwordParameter("password")
//		.and()
//		.oauth2Login()
//		.loginPage("/login");
//		//.successHandler(google0Auth2SucessHandler)
//		//.and()
//		//.logout()
//		//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		//.logoutSuccessUrl("/login")
//		//.invalidateHttpSession(true)
//		//.deleteCookies("JSESSIONID")
//		//.and()
//		//.exceptionHandling()
//		//.and()
//		//.csrf()
//		//.disable();
//	http.headers().frameOptions().disable();
//}
//	@Bean
//	public BCryptPasswordEncoder bcryptPasswordEncoder()
//	{
//	return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//	auth.userDetailsService(customUserDetailsService);
//	}
//	@Override
//	public void configure(WebSecurity web) throws Exception
//	{
//	web.ignoring().antMatchers("/resources/**","/static/**","/productimages/**");
//}
//}

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.caps.service.CustomAdminDetailsService;
import com.example.caps.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/cart/**","/admin/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.usernameParameter("email")
				.defaultSuccessUrl("/shop")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
	}
	
}

