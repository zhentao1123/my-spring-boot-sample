package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.User;

@Controller
@RequestMapping("/")
public class DemoController {

	@RequestMapping
	public String index() {
		return "index";
	}
	
	@RequestMapping("user/show1")
	@ResponseBody
	public User show1(@RequestBody User user) {
		return user;
	}
	
	@RequestMapping("user/show2")
	@ResponseBody
	public User show2(@RequestParam String name) {
		return new User(name);
	}
}
