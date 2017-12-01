package com.example.demo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Before
	public void setUp() {
		cityRepository.deleteAll();
	}
	
	@Test
	public void test1() {
		Cache cache = cacheManager.getCache("city");
		cityRepository.save(new City("shanghai","china"));
		cityRepository.save(new City("beijin","china"));
		List<City> allList = cityRepository.findAll(); //第一次日志有org.hibernate.SQL输出
		 //可以断点观察cache中缓存情况
		allList = cityRepository.findAll(); //第二次走缓存，日志没有org.hibernate.SQL输出
		allList = cityRepository.findAll();//第三次走缓存，日志没有org.hibernate.SQL输出
		
		List<City> findList = cityRepository.findByName("shanghai");
		//可以断点观察cache中缓存情况
		findList = cityRepository.findByName("shanghai");
		findList = cityRepository.findByName("shanghai");
	}

}
