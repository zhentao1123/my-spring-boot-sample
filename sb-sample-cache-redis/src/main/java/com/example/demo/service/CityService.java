package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityDao;
	
	@Cacheable(cacheNames="cityFindAll")//cacheNames不设置系统会自动设置一个
	public List<City> findAll(){
		return cityDao.findAll();
	}
	
	@Cacheable(cacheNames="cityFindByName", key="#name")//cacheNames不设置系统会自动设置一个
	public List<City> findByName(String name){
		return cityDao.findByName(name);
	}
	
	public void save(City city) {
		cityDao.save(city);
	}
	
	@Cacheable(cacheNames="city", key="#cityId")
	public City getCity(Long cityId) {
		return cityDao.findOne(cityId);
	}
	
	public void delete(Long id) {
		cityDao.delete(id);
	}
	
	public void deleteAll() {
		cityDao.deleteAll();
	}
}

