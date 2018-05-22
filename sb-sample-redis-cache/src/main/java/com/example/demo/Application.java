package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Value("${spring.redis.host}")
    private String host;
    
    @Value("${spring.redis.port}")
    private Integer port;
    
    @Value("${spring.redis.password}")
    private String password;
    
    @Value("${spring.redis.database}")
    private Integer database;
    
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    
    @Value("${spring.redis.pool.max-wait}")
    private Long maxWait;
    
    @Value("${spring.redis.pool.max-active}")
    private Integer maxActive;
    
    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;
    
    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;
    
    @Bean
	JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxActive);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWait);
		
		jedisPoolConfig.setTestOnCreate(true);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		return jedisPoolConfig;
	}
	
	@Bean
	JedisPool jedisPool() {
		return new JedisPool(jedisPoolConfig(), host, port, timeout, password, database);
	}
}
