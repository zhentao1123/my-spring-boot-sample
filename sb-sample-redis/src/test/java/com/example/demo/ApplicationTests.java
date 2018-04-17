package com.example.demo;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//需要通过name获取唯一指定的的RedisTemplate
	@Resource(name = "redisTemplate4User")
	private RedisTemplate<String, User> redisTemplate;
	
	@Autowired
    private JedisPool jedisPool;
	
	@Test
	public void testSpringDataRedis() {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		User user = new User("超人", 20);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蝙蝠侠", 30);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蜘蛛侠", 40);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
		
	}

	@Test
	public void testJedis() {
		Jedis jedis = jedisPool.getResource();
        //存入键值对
        jedis.set("key2","value2");
        String result = jedis.get("key2");
        Assert.assertEquals("value2", result);
        //回收ShardedJedis实例
        jedis.close();
	}
}
