package com.example.demo.service;

import com.example.demo.domain.City;
import com.example.demo.domain.HotelSummary;
import com.example.demo.service.dao.CitySearchCriteria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

	Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);

	City getCity(String name, String country);

	Page<HotelSummary> getHotels(City city, Pageable pageable);

}
