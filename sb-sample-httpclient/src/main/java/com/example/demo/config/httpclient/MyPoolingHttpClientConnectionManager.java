package com.example.demo.config.httpclient;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PoolingHttpClientConnectionManager用来管理客户端的连接池，并且可以为多个线程的请求提供服务
 * 注意：当HttpClient实例不再需要并且即将超出范围时，重要的是关闭其连接管理器，
 * 以确保管理器保持活动的所有连接都被关闭，并释放由这些连接分配的系统资源
 * 
 * 在PoolingHttpClientConnectionManager的配置中有两个最大连接数量，分别控制着总的最大连接数量和每个route的最大连接数量。
 * 如果没有显式设置，默认每个route只允许最多2个connection，总的connection数量不超过20。
 * 这个值对于很多并发度高的应用来说是不够的，必须根据实际的情况设置合适的值，思路和线程池的大小设置方式是类似的，
 * 如果所有的连接请求都是到同一个url，那可以把MaxPerRoute的值设置成和MaxTotal一致，这样就能更高效地复用连接

 * 特别注意：想要复用一个connection就必须要让它占有的系统资源得到正确释放，释放方法如下：
 * 如果是使用outputStream就要保证整个entity都被write out，如果是inputStream，则再最后要记得调用inputStream.close()。
 * 或者使用EntityUtils.consume(entity)或EntityUtils.consumeQuietly(entity)来让entity被完全耗尽（后者不抛异常）来做这一工作。
 * EntityUtils中有个toString方法也很方便的（调用这个方法最后也会自动把inputStream close掉的，但是在实际的测试过程中，会导致连接没有释放的现象），
 * 不过只有在可以确定收到的entity不是特别大的情况下才能使用。如果没有让整个entity被fully consumed，则该连接是不能被复用的，
 * 很快就会因为在连接池中取不到可用的连接超时或者阻塞在这里（因为该连接的状态将会一直是leased的，即正在被使用的状态）。
 * 所以如果想要复用connection，一定一定要记得把entity fully consume掉，只要检测到stream的eof，
 * 是会自动调用ConnectionHolder的releaseConnection方法进行处理的
 */
@Configuration
public class MyPoolingHttpClientConnectionManager {
	/**
	 * 连接池最大连接数
	 */
	@Value("${httpclient.config.connMaxTotal}")
	private int connMaxTotal = 20;

	/** 
	 * 路由基数
	 */
	@Value("${httpclient.config.maxPerRoute}")
	private int maxPerRoute = 20;

	/**
	 * 连接存活时间，单位为s
	 */
	@Value("${httpclient.config.timeToLive}")
	private int timeToLive = 60;

	@Bean
	public PoolingHttpClientConnectionManager poolingClientConnectionManager() {
		PoolingHttpClientConnectionManager poolHttpcConnManager = new PoolingHttpClientConnectionManager(60,
				TimeUnit.SECONDS);
		/**
		 * 最大连接数
		 * 设置整个连接池最大连接数 根据自己的场景决定。
		 */
		poolHttpcConnManager.setMaxTotal(this.connMaxTotal);
		/** 
		 * 路由基数
		 * 是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
		 * 设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
		 */
		poolHttpcConnManager.setDefaultMaxPerRoute(this.maxPerRoute);
		/**
		 * MaxtTotal和DefaultMaxPerRoute的区别：
		 * 1、MaxtTotal是整个池子的大小；
		 * 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如：
		 * MaxtTotal=400 DefaultMaxPerRoute=200
		 * 只连接到http://aa.com时，到这个主机的并发最多只有200；而不是400；
		 * 而连接到http://aa.com 和 http://bb.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；
		 * 所以起作用的设置是DefaultMaxPerRoute。
		 */
		return poolHttpcConnManager;
	}
}
