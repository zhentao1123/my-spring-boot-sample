package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.component.JmsComponent;
import com.example.demo.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	JmsComponent jmsComponent;
	
	@Test
	public void jmsTest() {
		jmsComponent.sendByMQ1("Hello");
		
		jmsComponent.sendByMQ2("Hello");
		
		User user = new User("Bob", 36);
		jmsComponent.sendByMQ2(user);
	}

}
