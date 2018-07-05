package com.example.demo.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource(name = "transactionTemplate")
	TransactionTemplate transactionTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 声明式事务
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void testTransaction1() throws Exception{
		User user = new User();
		user.setName("Tom");
		user.setAge(11);
		user.setCreateTime(new Date());
		userRepository.save(user);
	}
	
	/**
	 * 编程式事务
	 */
	public boolean testTransaction2() throws Exception{
		try {
			return transactionTemplate.execute(new TransactionCallback<Boolean>() {
				@Override
				public Boolean doInTransaction(TransactionStatus status) {
					try {
						User user = new User();
						user.setName("Tom");
						user.setAge(11);
						user.setCreateTime(new Date());
						userRepository.save(user);
						
						return true;
					} catch (Exception e) {
						status.setRollbackOnly();
						logger.error(e.getMessage(), e);
						throw e;
					}
				}

			});
		} catch (TransactionException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
