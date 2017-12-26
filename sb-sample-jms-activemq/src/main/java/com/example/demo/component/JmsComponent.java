package com.example.demo.component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.domain.User;

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
	
	public void sendByMQ2(User user) {
		this.jmsMessagingTemplate.convertAndSend(this.mq2, user);
	}
	
	@JmsListener(destination="demo.mq1")
	public void receiveMQ1(String text) {
		logger.debug("receive from mq1 : " + text);
	}
	
	@JmsListener(destination="demo.mq2")
	public void receiveMQ2(Object msg) {
		logger.debug(msg.getClass().getSimpleName());
		
		String msgPayload = "";
		if(msg instanceof ActiveMQTextMessage) {
			ActiveMQTextMessage txtMsg = (ActiveMQTextMessage)msg;
			try {
				msgPayload = txtMsg.getText();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} 
		else if (msg instanceof ActiveMQObjectMessage)
		{
			ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage)msg;
			Object obj = null;
			try {
				obj = (Object)objMsg.getObject();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			if(obj==null) return;
			
			if(obj instanceof User) {
				msgPayload = ((User)obj).toString();
			}
		}
		logger.debug("receive from mq2 : " + msgPayload);
	}
}
