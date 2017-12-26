package com.example.demo.web;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@CrossOrigin(origins="http://localhost:8082")//通过添加cors设置，开放来自指定域名的ajax跨域访问
	@RequestMapping(path="test/get", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> get(@RequestParam String name){
		logger.debug("[/test/get]");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "hello");
		map.put("name", name);
		return map;
	}
	
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "index";
	}
}
