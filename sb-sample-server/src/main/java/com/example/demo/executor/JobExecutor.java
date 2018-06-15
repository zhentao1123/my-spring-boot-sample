package com.example.demo.executor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.demo.handler.IJobHandler;
import com.example.demo.handler.annotation.JobHandler;
import com.example.demo.thread.JobThread;

/**
 * 服务执行器
 * 1)管理服务器（Jetty）
 * 2)管理任务执行器池
 * 3)管理任务线程池
 */
@Component
public class JobExecutor implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(JobExecutor.class);
	
	
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
		destoryJobThreadRepository();
		
		// destory executor-server
        stopExecutorServer();
	}
	
	
	// JobHandler Repository --------------------------------------------
	private static ConcurrentHashMap<String, IJobHandler> jobHandlerRepository = new ConcurrentHashMap<>();
	// registe JobHandler
	public static IJobHandler registJobHandler(String name, IJobHandler jobHandler) {
		return jobHandlerRepository.put(name, jobHandler);
	}
	// load JobHandler
	public static IJobHandler loadJobHandler(String name) {
		return jobHandlerRepository.get(name);
	}
	// init JobHandler Repository
	private static void initJobHandlerRepository(ApplicationContext applicationContext){
		if(null == applicationContext) {
			return;
		}
		Map<String, Object> serviceBeanMap =  applicationContext.getBeansWithAnnotation(JobHandler.class);
		if(!CollectionUtils.isEmpty(serviceBeanMap)) {
			for(Object serviceBean : serviceBeanMap.values()) {
				String name = serviceBean.getClass().getAnnotation(JobHandler.class).value();
				IJobHandler handler = (IJobHandler) serviceBean;
				if (loadJobHandler(name) != null) {
                    throw new RuntimeException("jobhandler naming conflicts.");
                }
                registJobHandler(name, handler);
			}
		}
	}
	
	
	// JobThread Repository --------------------------------------------
	private static ConcurrentHashMap<String, JobThread> jobThreadRepository = new ConcurrentHashMap<>();
	// regist JobThread
	public static JobThread registJobThread(String jobId, IJobHandler jobHandler) {
		JobThread jobThread = new JobThread(jobId, jobHandler);
		jobThread.start();
		
		JobThread jobThreadOld = jobThreadRepository.put(jobId, jobThread); //如果存在，map会返回旧有值
		if(null!=jobThreadOld) {
			jobThreadOld.toStop();
			jobThreadOld.interrupt();
		}
		
		return jobThread;
	}
	// remove JobThread
	public static void removeJobThread(String jobId){
        JobThread jobThreadOld = jobThreadRepository.remove(jobId);
        if(null!=jobThreadOld) {
        		jobThreadOld.toStop();
        		jobThreadOld.interrupt();
        }
    }
	// load JobThread
	public static JobThread loadJobThread(String name) {
		return jobThreadRepository.get(name);
	}
	// destory JobThread Repository
	private static void destoryJobThreadRepository() {
		if (jobThreadRepository.size() > 0) {
            for (Map.Entry<String, JobThread> item: jobThreadRepository.entrySet()) {
            		removeJobThread(item.getKey());
            }
            jobThreadRepository.clear();
        }
	}
	
	
	// Executor Server (jetty) --------------------------------------------
	private void initExecutorServer() throws Exception {
		//TODO
	}
	private void stopExecutorServer() {
		//TODO
	}
}
