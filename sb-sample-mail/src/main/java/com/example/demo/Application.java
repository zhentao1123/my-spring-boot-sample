package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*
	@Bean(name="mailSender1")
	@ConfigurationProperties(prefix="mail1.username")
	public JavaMailSender mailSender1() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		return javaMailSender;
	}
	
	@Bean(name="mailSender2")
	@ConfigurationProperties(prefix="mail2.username")
	public JavaMailSender mailSender2() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		return javaMailSender;
	}
	*/
}
