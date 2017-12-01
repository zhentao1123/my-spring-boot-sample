package com.example.demo.service;

import java.util.List;

import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;

@Service
@CacheConfig(cacheNames = "city") //cacheNames不设置系统会自动设置一个
public class UserService {
	
	@Autowired
	CityRepository cityDao;
	
	//@Cacheable//value不设置系统会自动设置一个
	@CacheResult
	public List<City> findAll(){
		return cityDao.findAll();
	}
	
	//@Cacheable(value="findByName")//value不设置系统会自动设置一个
	@CacheResult
	public List<City> findByName(String name){
		return cityDao.findByName(name);
	}
	
	@CachePut
	public void save(City city) {
		cityDao.save(city);
	}
	
	@CacheRemove
	public void delete(Long id) {
		cityDao.delete(id);
	}
	
	@CacheRemoveAll
	public void deleteAll() {
		cityDao.deleteAll();
	}
}
