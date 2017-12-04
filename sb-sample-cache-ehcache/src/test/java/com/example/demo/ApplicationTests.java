package com.example.demo;

import java.util.Collection;
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
import com.example.demo.service.CityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Before
	public void setUp() {
		cityRepository.deleteAll();
	}
	
	@Test
	public void test1() {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for(String cacheName : cacheNames) {
			log.debug("Cache impl is {}", cacheManager.getCache(cacheName).getNativeCache().getClass().getName());
			log.debug("Cache Name {}", cacheName);
		}
		
		cityService.save(new City("shanghai","china"));
		cityService.save(new City("beijin","china"));
		
		List<City> allList = cityService.findAll(); //第一次日志有org.hibernate.SQL输出
		Cache cache2 = cacheManager.getCache("cityFindAll");//可以断点观察cache中缓存情况
		allList = cityService.findAll(); //第二次走缓存，日志没有org.hibernate.SQL输出
		cityService.save(new City("nanjin","china"));
		allList = cityService.findAll();//第三次走缓存，日志没有org.hibernate.SQL输出
		
		List<City> findList = cityService.findByName("shanghai");
		Cache cache3 = cacheManager.getCache("cityFindByName");//可以断点观察cache中缓存情况
		findList = cityService.findByName("shanghai");
		findList = cityService.findByName("shanghai");
	}

}
