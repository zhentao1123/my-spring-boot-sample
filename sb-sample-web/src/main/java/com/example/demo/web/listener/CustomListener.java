package com.example.demo.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class CustomListener implements ServletContextListener{

	private static Logger logger = LoggerFactory.getLogger(CustomListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("[Listener contextInitialized]");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("[Listener contextDestroyed]");
	}

}
