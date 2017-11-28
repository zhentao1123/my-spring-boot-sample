package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	
	List<User> findAll();
	
}
