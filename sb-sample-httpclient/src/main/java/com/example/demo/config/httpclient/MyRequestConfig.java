package com.example.demo.config.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RequestConfig是对request的一些配置。
 * 里面比较重要的有三个超时时间，默认的情况下这三个超时时间都为0（
 * 如果不设置request的Config，会在execute的过程中使用HttpClientParamConfig的getRequestConfig中用默认参数进行设置），
 * 这也就意味着无限等待，很容易导致所有的请求阻塞在这个地方无限期等待。
 * 
 * 这三个超时时间为：
 * 
 * a、connectionRequestTimeout—从连接池中取连接的超时时间
 * 这个时间定义的是从ConnectionManager管理的连接池中取出连接的超时时间，
 * 如果连接池中没有可用的连接，则request会被阻塞，最长等待connectionRequestTimeout的时间，
 * 如果还没有被服务，则抛出ConnectionPoolTimeoutException异常，不继续等待。
 * 
 * b、connectTimeout—连接超时时间
 * 这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间。
 * 发生超时，会抛出ConnectionTimeoutException异常。
 * 
 * c、socketTimeout—请求超时时间
 * 这个时间定义了socket读数据的超时时间，也就是连接到服务器之后到从服务器获取响应数据需要等待的时间，
 * 或者说是连接上一个url之后到获取response的返回等待时间。
 * 发生超时，会抛出SocketTimeoutException异常。
 *
 */
@Configuration
public class MyRequestConfig {
	@Value("${httpclient.config.connectTimeout}")
	private int connectTimeout = 2000;

	@Value("${httpclient.config.connectRequestTimeout}")
	private int connectRequestTimeout = 2000;

	@Value("${httpclient.config.socketTimeout}")
	private int socketTimeout = 2000;

	@Bean
	public RequestConfig config() {
		return RequestConfig.custom().setConnectionRequestTimeout(this.connectRequestTimeout)
				.setConnectTimeout(this.connectTimeout).setSocketTimeout(this.socketTimeout).build();
	}
}