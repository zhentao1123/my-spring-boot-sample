package com.example.demo.component;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.MessageCreator;

public class SchedulerMessageCreator<M extends Serializable> implements MessageCreator {

	private M jobMsg;
	private Long delay;
	private Long period;
	private Integer repeat;
	private String cron;
	
	public SchedulerMessageCreator() {
		
	}

	public SchedulerMessageCreator(M jobMsg) {
		this.jobMsg = jobMsg;
	}
	
	public void setScheduledCron(String cron) {
		this.cron = cron;
	}

	public void setScheduledPeriod(Long period) {
		this.period = period;
	}

	public void setScheduledRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	public void setScheduledDelay(Long delay) {
		this.delay = delay;
	}
	
	@Override
	public Message createMessage(Session session) throws JMSException {
		ObjectMessage msg = session.createObjectMessage();
		msg.setJMSPriority(5);
		msg.setObject(jobMsg);

		if (delay != null) {
			msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
		}
		if (period != null) {
			msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
		}
		if (repeat != null) {
			msg.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
		}
		if (cron != null) {
			msg.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, cron);
		}
		return msg;
	}

	public M getJobMsg() {
		return jobMsg;
	}

	public void setJobMsg(M jobMsg) {
		this.jobMsg = jobMsg;
	}

	
}
