package com.example.demo.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {
	
	@Bean(name="mq1")
	public Queue mq1() {
		return new ActiveMQQueue("demo.mq1");
	}
	
	@Bean(name="mq2")
	public Queue mq2() {
		return new ActiveMQQueue("demo.mq2");
	}
}
