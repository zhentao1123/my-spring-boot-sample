package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Cors 全局配置方式1
 * 也可以单独或结合@CrossOrigin注释使用
 * 也可以如下xml配置
 * <mvc:cors>
 * 	<mvc:mapping path="/**" />
 * </mvc:cors>
 */
@Configuration
public class CorsConfig1 {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry
//				.addMapping("/test/**")
//				.allowedOrigins("http://localhost:8080")
//				.allowedMethods("PUT", "DELETE")
//	            .allowedHeaders("header1", "header2", "header3")
//	            .exposedHeaders("header1", "header2")
//	            .allowCredentials(false).maxAge(3600);
			}
		};
	}
}
