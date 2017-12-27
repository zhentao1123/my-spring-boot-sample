package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/anyone")
	public String anyone() {
		return "anyone";
	}
	
	@RequestMapping("/user/")
	public String user() {
		return "user";
	}
	
	@RequestMapping("/user/1")
	public String user1() {
		return "user1";
	}
	
	@RequestMapping("/admin/")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping("/admin/1")
	public String admin1() {
		return "admin1";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
}
