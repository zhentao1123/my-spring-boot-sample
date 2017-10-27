package com.example.demo.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.City;
import com.example.demo.domain.Hotel;

@RepositoryRestResource(collectionResourceRel = "hotels", path = "hotels")
interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

	Hotel findByCityAndName(City city, String name);

}
