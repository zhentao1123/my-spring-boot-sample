package com.example.demo.config;

import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.ehcache.core.EhcacheManager;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
	

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		//Create a cache by implementing the JCacheManagerCustomizer.customize(CacheManager cacheManager) method, which will be invoked before the CacheManager is used.
		return new JCacheManagerCustomizer() {
			@Override
			public void customize(CacheManager cacheManager) {
				cacheManager.createCache("city", //Creates a cache with an alias of "city".
						new MutableConfiguration<>()
								//This line sets the expiration policy. In this case we set it to 10 seconds. Thus, if an entry hasn’t been touched (created, updated, or accessed) for the last 10 seconds it will be evicted.
								.setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 10)))
								.setStoreByValue(false).setStatisticsEnabled(true));
			}
		};
	}
	
}
