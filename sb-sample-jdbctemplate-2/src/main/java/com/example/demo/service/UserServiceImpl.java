package com.example.demo.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;
	
	@Override
	@Transactional(readOnly=false)
	public void create(String name, Integer age) throws Exception{
		try {
			logger.debug(jdbcTemplate2.getDataSource().getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    jdbcTemplate2.update("insert into user(name, age, create_time) values(?, ?, ?)", name, age, new Date());
	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteByName(String name) throws Exception{
		try {
			logger.debug(jdbcTemplate2.getDataSource().getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    jdbcTemplate2.update("delete from user where name = ?", name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Integer getAllUsers() {
		try {
			logger.debug(jdbcTemplate1.getDataSource().getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return jdbcTemplate1.queryForObject("select count(1) from user", Integer.class);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteAllUsers() throws Exception{
		try {
			logger.debug(jdbcTemplate2.getDataSource().getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    jdbcTemplate2.update("delete from user");
	}

	@Override
	@Transactional(readOnly=false)
	public void batchCreat(List<User> userList) {
		for(User user : userList) {
			jdbcTemplate2.update("insert into user(id, name, age, create_time) values(?, ?, ?, ?)", 
					user.getId(), user.getName(), user.getAge(), new Date());
		}
	}
}
