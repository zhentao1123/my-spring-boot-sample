package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	@Value("${name:no one}")
	private String name;
	
	public String sayHello() {
		return "Hello " + this.name;
	}
	
	public String sayHello(String name) {
		return "Hello " + name;
	}
}
