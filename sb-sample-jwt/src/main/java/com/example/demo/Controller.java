package com.example.demo;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class Controller {
	
	@GetMapping("/protected")
    public @ResponseBody Object hellWorld() {
        return "Hello World! This is a protected api";
    }
	
	@PostMapping("/login") 
	public Object login(HttpServletResponse response, @RequestBody final Account account) throws IOException { 
		if(isValidPassword(account)) { 
			String jwt = JwtUtil.generateToken(account.getUsername()); 
			return new HashMap<String,String>(){{ put("token", jwt); }}; 
		}else { 
			return new ResponseEntity(HttpStatus.UNAUTHORIZED); 
		} 
	}
	
	private boolean isValidPassword(Account account) {
		return (account.getUsername().equals("tom") && account.getPassword().equals("123456"));
	}
}
