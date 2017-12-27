package com.example.demo.component;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpComponent {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(String msg) {
		this.amqpTemplate.convertAndSend("test.queue", msg);
	}

	@RabbitListener(queues = "test.queue")
	public void receiveQueue(String text) {
		System.out.println("接受到：" + text);
	}
}
