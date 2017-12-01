package com.example.demo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Before
	public void setUp() {
		cityRepository.deleteAll();
	}
	
	@Test
	public void test1() {
		Cache cache1 = cacheManager.getCache("city");
		log.debug("Cache impl is {}", cache1.getNativeCache().getClass().getName());
		
		userService.save(new City("shanghai","china"));
		userService.save(new City("beijin","china"));
		List<City> allList = cityRepository.findAll(); //第一次日志有org.hibernate.SQL输出
		;//可以断点观察cache中缓存情况
		allList = userService.findAll(); //第二次走缓存，日志没有org.hibernate.SQL输出
		allList = userService.findAll();//第三次走缓存，日志没有org.hibernate.SQL输出
		
		List<City> findList = userService.findByName("shanghai");
		Cache cache3 = cacheManager.getCache("findByName");//可以断点观察cache中缓存情况
		findList = userService.findByName("shanghai");
		findList = userService.findByName("shanghai");
	}

}
