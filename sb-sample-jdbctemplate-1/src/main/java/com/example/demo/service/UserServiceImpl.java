package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional(readOnly=false)
	public void create(String name, Integer age) {
	    jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteByName(String name) {
	    jdbcTemplate.update("delete from USER where NAME = ?", name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Integer getAllUsers() {
	    return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteAllUsers() {
	    jdbcTemplate.update("delete from USER");
	}
}
