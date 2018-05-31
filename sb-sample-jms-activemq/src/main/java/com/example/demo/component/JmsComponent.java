package com.example.demo.component;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
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
	
	public void sendByMQ2DelaySend(String msg) {
		//获取连接工厂
        ConnectionFactory connectionFactory = this.jmsMessagingTemplate.getConnectionFactory();
        try {
            //获取连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue("mq2");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(msg);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 5000);
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.getMessage();
        }
		
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
		else {
			logger.debug("other format");
		}
		logger.debug("receive from mq2 : " + msgPayload);
	}
}
