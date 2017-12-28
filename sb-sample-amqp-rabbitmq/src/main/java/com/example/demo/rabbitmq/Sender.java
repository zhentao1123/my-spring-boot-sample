package com.example.demo.rabbitmq;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback, ReturnCallback {
	
	private static final Logger logger = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
	
	public void sendText(String msg) {
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		logger.debug("---------------------------------------------");
		logger.debug("开始发送消息 : " + msg.toLowerCase());
        String response = rabbitTemplate.convertSendAndReceive("topicExchange", "key.1", msg, correlationId).toString();
        //rabbitTemplate.convertAndSend(msg);
        logger.debug("结束发送消息 : " + msg.toLowerCase());
        logger.debug("消费者响应 : " + response + " 消息处理完成");
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		logger.debug(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {  
			logger.debug("消息发送成功:" + correlationData);  
        } else {  
        		logger.debug("消息发送失败:" + cause);  
        } 
	}
}
