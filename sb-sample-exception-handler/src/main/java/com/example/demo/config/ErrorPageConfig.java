package com.example.demo.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * spring boot 使用内嵌web服务器的异常统一处理
 */
@Configuration
public class ErrorPageConfig {
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	        		
	        		/**
	        		 * 1）直接指向static目录下的页面
	        		 * 2）写请求地址，对应controller处理
	        		 */
	            //ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html"); //直接指向页面方式
	        		//ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.html");
	            //ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
	            //ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"); //请求地址方式
	            //container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
	        }
	    };
	}
	
}
