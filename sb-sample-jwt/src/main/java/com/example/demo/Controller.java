package com.example.demo;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
	
	/**
	 * curl http://127.0.0.1:8000/api/protected
	 * @return
	 */
	@GetMapping("/api/protected")
    public @ResponseBody Object hellWorld() {
        return "Hello World! This is a protected api";
    }
	
	/**
	 * 根据
	 * curl -H "Content-Type:application/json" -X POST -d '{"username": "tom", "password":"123456"}' http://127.0.0.1:8000/login
	 * @param response
	 * @param account
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/login") 
	public Object login(HttpServletResponse response, @RequestBody final Account account) throws IOException { 
		if(isValidPassword(account)) {
			String jwt = JwtUtil.createJWT(account.getUsername(), "secret", 0); 
			return new HashMap<String, String>(){{put("token", jwt);}}; 
		}else { 
			return new ResponseEntity(HttpStatus.UNAUTHORIZED); 
		} 
	}
	
	private boolean isValidPassword(Account account) {
		return (account.getUsername().equals("tom") && account.getPassword().equals("123456"));
	}
}
