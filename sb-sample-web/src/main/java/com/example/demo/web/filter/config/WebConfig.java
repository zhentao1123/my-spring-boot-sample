package com.example.demo.web.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器添加方式二
 */
@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean filterRegistration2() {
		// 配置无需过滤的路径或者静态资源，如：css，imgage等
		StringBuffer excludedUriStr = new StringBuffer();
		excludedUriStr.append("/login/*");
		excludedUriStr.append(",");
		excludedUriStr.append("/favicon.ico");
		excludedUriStr.append(",");
		excludedUriStr.append("/js/*");

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CustomFilter2());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("excludedUri", excludedUriStr.toString());
		registration.setName("CustomFilter2");
		registration.setOrder(2);
		
		return registration;
	}

	@Bean
	public FilterRegistrationBean filterRegistration3() {
		// 配置无需过滤的路径或者静态资源，如：css，imgage等
		StringBuffer excludedUriStr = new StringBuffer();
		excludedUriStr.append("/login/*");
		excludedUriStr.append(",");
		excludedUriStr.append("/favicon.ico");
		excludedUriStr.append(",");
		excludedUriStr.append("/js/*");

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CustomFilter3());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("excludedUri", excludedUriStr.toString());
		registration.setName("CustomFilter3");
		registration.setOrder(3);
		
		return registration;
	}
}
