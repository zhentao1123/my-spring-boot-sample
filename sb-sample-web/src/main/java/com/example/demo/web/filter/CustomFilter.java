package com.example.demo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 过滤器添加方式一
 */
@Configuration //必须
@WebFilter(urlPatterns="/*", filterName="CustomFilter1")
@Order(value = 1)
public class CustomFilter implements Filter{

	private static Logger logger = LoggerFactory.getLogger(CustomFilter.class);
	
	/**
	 * 请求时候调用
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("[filter1 do]");
		
		//演示读取request的body
		//备份及使用流
		ResettableServletRequest myRequest = new ResettableServletRequest((HttpServletRequest) request);
		String requestBody = IOUtils.toString(myRequest.getInputStream(), "UTF-8");
		logger.debug(requestBody);
		
		chain.doFilter(request, response);
	}
	
	/**
	 * 应用启动时候调用
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.debug("[filter1 init]");
	}
	
	/**
	 * 应用销毁时候调用
	 */
	@Override
	public void destroy() {
		logger.debug("[filter1 destory]");
	}

}
