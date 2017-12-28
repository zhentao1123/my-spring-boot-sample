package com.example.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	@RabbitListener(queues = "hello.queue1")
    public String processMessage1(String msg) {
		logger.debug(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
        return msg.toUpperCase();
    }
    
    @RabbitListener(queues = "hello.queue2")
    public void processMessage2(String msg) {
    		logger.debug(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
    }

}
