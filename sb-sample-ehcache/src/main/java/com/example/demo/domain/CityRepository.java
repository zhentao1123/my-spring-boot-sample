package com.example.demo.domain;

import java.util.List;



import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@CacheConfig(cacheNames = "city")
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

	Page<City> findAll(Pageable pageable);

	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);

	City findByNameAndCountryAllIgnoringCase(String name, String country);

	@Cacheable
	List<City> findAll();
	
	@Cacheable
	List<City> findByName(String name);
}
