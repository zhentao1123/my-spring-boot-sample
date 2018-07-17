package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.endpoint.WebSocketServer;

@Controller
@RequestMapping("/")
public class TestController {

	@RequestMapping("")
	public String index(ModelMap mm) {
		return "index";
	} 
	
	@PostMapping(value="broadcastTest")
	public String broadcastTest(String message) {
		try {
			WebSocketServer.sendInfo(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "succeed";
	}
}
