package com.example.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.handler.JobHandler;

public class JobThread extends Thread{

	private static Logger logger = LoggerFactory.getLogger(JobThread.class);
	
	private String jobId;
	private JobHandler jobHandler;
	
	private volatile boolean toStop = false;
	
	public JobThread(String jobId, JobHandler jobHandler) {
		this.jobId = jobId;
		this.jobHandler = jobHandler;
	}
	
	public JobHandler getJobHandler() {
		return jobHandler;
	}

	public void toStop() {
		this.toStop = true;
	}
	
	@Override
	public void run() {
		
	}
}
