package com.example.demo.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	
	//List<User> findAll();
	
}
