package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Application {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/test")
	@ResponseBody
	public String testlog() {
		String preStr = "My level is ";
		logger.debug("{} debug", preStr);
		logger.info("{} info", preStr);
		logger.warn("{} warn", preStr);
		logger.error("{} error", preStr);
		return "see log in console";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
