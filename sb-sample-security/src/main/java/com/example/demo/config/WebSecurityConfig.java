package com.example.demo.config;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationService authenticationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				//.failureUrl(authenticationFailureUrl)
				//.failureForwardUrl("/login")
				//.successForwardUrl(forwardUrl)
				.permitAll()
				.and()
			.logout()
				//.logoutUrl("") //默认 "/logout"
				.logoutSuccessUrl("/")
				.clearAuthentication(true)
				.invalidateHttpSession(false)
				.permitAll()
				;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//数据库方式
		auth.userDetailsService(authenticationService).passwordEncoder(new ShaPasswordEncoder(1));
		//内存方式
//		auth.inMemoryAuthentication()
//	 		.withUser("user").password("password").roles("USER")
//	 		.and()
//	 		.withUser("u").password("p").roles("USER");
	}
	
//	public static void main(String[] args) {
//		//密码编码
//		System.out.println(DigestUtils.sha1Hex("123456"));
//		//7c4a8d09ca3762af61e59520943dc26494f8941b
//	}
}
