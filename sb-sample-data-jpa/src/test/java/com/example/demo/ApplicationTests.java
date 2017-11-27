package com.example.demo;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Before
	public void setUp() {
		userRepository.deleteAll();
	}

	@Test
	public void test() throws Exception {

		userRepository.save(new User("aaa", 10, new Date()));
		userRepository.save(new User("bbb", 20, new Date()));
		userRepository.save(new User("ccc", 30, new Date()));
		userRepository.save(new User("ddd", 40, new Date()));
		userRepository.save(new User("eee", 50, new Date()));

		//Assert.assertEquals(5, 5);
		Assert.assertEquals(5, userRepository.findAll().size());
	}

}

