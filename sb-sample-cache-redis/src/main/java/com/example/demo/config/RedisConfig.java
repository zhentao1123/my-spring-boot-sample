package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String redis_host;
	@Value("${spring.redis.port}")
	private Integer redis_port;
	@Value("${spring.redis.timeout}")
	private Integer redis_timeout;

//	@Bean
//	public RedisConnectionFactory RedisConnectionFactory() {
//		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//		redisConnectionFactory.setHostName(redis_host);
//		redisConnectionFactory.setPort(redis_port);
//		//redisConnectionFactory.setPassword(password);
//		redisConnectionFactory.setTimeout(redis_timeout);
//		redisConnectionFactory.setDatabase(0);
//		redisConnectionFactory.setUsePool(false);
//		//redisConnectionFactory.setPoolConfig(poolConfig);
//		
//		return redisConnectionFactory;
//	}

}
