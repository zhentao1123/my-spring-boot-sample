package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;

@Service
//@CacheConfig(cacheNames = "city") //cacheNames不设置系统会自动设置一个
//@CacheConfig
public class CityService {
	
	@Autowired
	CityRepository cityDao;
	
	@Cacheable(cacheNames="cityFindAll")//cacheNames不设置系统会自动设置一个
	//@Cacheable
	public List<City> findAll(){
		return cityDao.findAll();
	}
	
	@Cacheable(cacheNames="cityFindByName", key="#name")//cacheNames不设置系统会自动设置一个
	//@Cacheable(key="#name")
	public List<City> findByName(String name){
		return cityDao.findByName(name);
	}
	
	//@CachePut(cacheNames={"cityFindAll","cityFindByName"})
	//@CachePut
	public void save(City city) {
		cityDao.save(city);
	}
	
	public void delete(Long id) {
		cityDao.delete(id);
	}
	
	public void deleteAll() {
		cityDao.deleteAll();
	}
}

