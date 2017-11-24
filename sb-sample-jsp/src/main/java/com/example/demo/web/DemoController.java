package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DemoController {
	
	@RequestMapping
	public String index(ModelMap map) {
		map.put("message", "index page");
		return "index";
	}
	
	/**
	 * ModelAndView方式
	 * @return
	 */
	@RequestMapping("user/info")
	public ModelAndView userInfo() {
		ModelAndView m = new ModelAndView();
		m.addObject("message", "user info page");
		m.setViewName("user/info");
		return m;
	}
	
	/**
	 * ModelMap方式
	 * @param mm
	 * @return
	 */
	@RequestMapping("user/list")
	public String userList(ModelMap mm) {
		mm.addAttribute("message", "user list page");
		return "user/list";
	}
}
