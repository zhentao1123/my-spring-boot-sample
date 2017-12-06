package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
/**
在Spring Boot中通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），
Spring Boot根据下面的顺序去侦测缓存提供者：
	Generic
	JCache (JSR-107)
	EhCache 2.x
	Hazelcast
	Infinispan
	Redis
	Guava
	Simple
除了按顺序侦测外，我们也可以通过配置属性spring.cache.type来强制指定。
*/
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
