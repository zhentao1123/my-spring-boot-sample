package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {""})
@ActiveProfiles("dev")
public class ApplicationTests {

	@Value("${env}")
	String test;
	
	@Test
	public void contextLoads() {
		System.out.println(test);
		Assert.assertEquals("dev", test);
	}

}