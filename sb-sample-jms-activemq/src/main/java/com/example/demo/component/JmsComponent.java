package com.example.demo.component;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsComponent.class);
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Resource(name="mq1")
	private Queue mq1;
	
	@Resource(name="mq2")
	private Queue mq2;
	
	public void sendByMQ1(String msg) {
		this.jmsMessagingTemplate.convertAndSend(this.mq1, msg);
	}
	
	public void sendByMQ2(String msg) {
		this.jmsMessagingTemplate.convertAndSend(this.mq2, msg);
	}
	
	@JmsListener(destination="demo.mq1")
	public void receiveMQ1(String text) {
		logger.debug("receive from mq1 : " + text);
	}
	
	@JmsListener(destination="demo.mq2")
	public void receiveMQ2(String text) {
		logger.debug("receive from mq2 : " + text);
	}
}
