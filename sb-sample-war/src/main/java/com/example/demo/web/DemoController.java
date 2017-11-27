package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class DemoController {
	
	@RequestMapping
	@ResponseBody
	public String index() {
		return "index";
	}
}
