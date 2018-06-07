package com.example.demo.web.filter.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.web.filter.CustomFilter;

public class CustomFilter3 implements Filter{

	private static Logger logger = LoggerFactory.getLogger(CustomFilter.class);
	
	/**
	 * 请求时候调用
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("[filter3 do]");
		chain.doFilter(request, response);
	}
	
	/**
	 * 应用启动时候调用
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.debug("[filter3 init]");
	}
	
	/**
	 * 应用销毁时候调用
	 */
	@Override
	public void destroy() {
		logger.debug("[filter3 destory]");
	}

}