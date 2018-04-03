package com.example.demo.config.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
 * 描述：HttpClient代理 
 */  
@Configuration  
public class MyDefaultProxyRoutePlanner {  
    // 代理的host地址  
    @Value("${httpclient.config.proxyHost}")  
    private String proxyHost;  
      
    // 代理的端口号  
    @Value("${httpclient.config.proxyPort}")  
    private int proxyPort = 8080;  
      
    @Bean  
    public DefaultProxyRoutePlanner defaultProxyRoutePlanner(){  
        HttpHost proxy = new HttpHost(this.proxyHost, this.proxyPort);  
        return new DefaultProxyRoutePlanner(proxy);  
    }  
}  