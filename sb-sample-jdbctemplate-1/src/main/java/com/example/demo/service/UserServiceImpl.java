package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public void create(String name, Integer age) {
	    jdbcTemplate.update("insert into user(name, age, create_time) values(?, ?, ?)", name, age, new Date());
	}
	
	@Override
	@Transactional
	public void deleteByName(String name) {
	    jdbcTemplate.update("delete from user where name = ?", name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Integer getAllUsers() {
	    return jdbcTemplate.queryForObject("select count(1) from user", Integer.class);
	}
	
	@Override
	@Transactional
	public void deleteAllUsers() {
	    jdbcTemplate.update("delete from user");
	}
}
