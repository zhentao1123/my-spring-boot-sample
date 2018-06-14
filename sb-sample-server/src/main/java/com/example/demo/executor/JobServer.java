package com.example.demo.executor;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.example.demo.handler.JobHandler;
import com.example.demo.thread.JobThread;

/**
 * 服务执行器
 * 1)管理服务器（Jetty）
 * 2)管理任务执行器池
 * 3)管理任务线程池
 */
@Component
public class JobServer implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(JobServer.class);
	
	
	// ApplicationContext --------------------------------------------
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		logger.debug("[MyServer.setApplicationContext]");
	}
	public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
	
	
	// Start/Stop --------------------------------------------
	public void start() throws Exception {
		// init jobHandlerRepository
		initJobHandlerRepository(applicationContext);
		
		// init executor-server
        initExecutorServer();
	}
	public void destroy(){
		// destory JobThreadRepository
		destoryJobHandlerRepository();
		
		// destory executor-server
        stopExecutorServer();
	}
	
	
	// JobHandler Repository --------------------------------------------
	private static ConcurrentHashMap<String, JobHandler> jobHandlerRepository = new ConcurrentHashMap<>();
	public static JobHandler registJobHandler(String name, JobHandler jobHandler) {
		return jobHandlerRepository.put(name, jobHandler);
	}
	public static JobHandler loadJobHandler(String name) {
		return jobHandlerRepository.get(name);
	}
	private static void initJobHandlerRepository(ApplicationContext applicationContext){
		//TODO
	}
	private static void destoryJobHandlerRepository() {
		//TODO
	}
	
	
	// JobThread Repository --------------------------------------------
	private static ConcurrentHashMap<String, JobThread> jobThreadRepository = new ConcurrentHashMap<>();
	public static JobThread registJobThread(String jobId, JobHandler jobHandler) {
		JobThread jobThread = new JobThread(jobId, jobHandler);
		jobThread.start();
		
		JobThread jobThreadOld = jobThreadRepository.put(jobId, jobThread); //如果存在，map会返回旧有值
		if(null!=jobThreadOld) {
			jobThreadOld.toStop();
			jobThreadOld.interrupt();
		}
		
		return jobThread;
	}
	public static void removeJobThread(String jobId){
        JobThread jobThreadOld = jobThreadRepository.remove(jobId);
        if(null!=jobThreadOld) {
        		jobThreadOld.toStop();
        		jobThreadOld.interrupt();
        }
    }
	public static JobThread loadJobThread(String name) {
		return jobThreadRepository.get(name);
	}
	
	
	// Executor Server (jetty) --------------------------------------------
	private void initExecutorServer() throws Exception {
		//TODO
	}
	private void stopExecutorServer() {
		//TODO
	}
}
