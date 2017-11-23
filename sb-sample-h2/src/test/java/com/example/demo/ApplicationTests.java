package com.example.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.City;
import com.example.demo.domain.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	CityRepository cityRepository;
	
	@Before
	public void setUp() {
		cityRepository.deleteAll();
	}
	
	@Test
	public void test1() {
		cityRepository.save(new City("shanghai","china"));
		cityRepository.save(new City("beijin","china"));
		City city = cityRepository.findByNameAndCountryAllIgnoringCase("shanghai","china");
		Assert.assertEquals("shanghai", city.getName());
	}
	
	@Test
	public void test2() {
		cityRepository.save(new City("shanghai","china"));
		cityRepository.save(new City("beijin","china"));
		List<City> list = cityRepository.findAll();
		System.out.println("========================"+list.size());
		Assert.assertEquals(2, list.size());
	}

}
