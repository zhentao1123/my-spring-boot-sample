package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//--- 1) Spring Data Redis ------------------------
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

	/**
	 * 要用RedisTemplate<K, V>模式
	 * 1.必须设置KeySerializer和实现ValueSerializer
	 * 2.需要指定name,且在调用注入处指定bean名称
	 * @param factory
	 * @return
	 */
    @Bean(name = "redisTemplate4User")
    public RedisTemplate<String, User> redisTemplate4User(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<String, User>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
    
    
    
    //--- 2) Jedis ------------------------
    
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
