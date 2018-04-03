package com.example.demo.config.httpclient;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
 * 描述：连接保持策略 
 * 
 * HTTP规范没有指定持久连接可能和应该保持存活多久。
 * 一些HTTP服务器使用非标准的Keep-Alive标头来向客户端通信它们打算在服务器端保持连接的时间段（以秒为单位）。
 * HttpClient可以使用这些信息。如果响应中不存在Keep-Alive头，HttpClient会假定连接可以无限期地保持活动。
 * 然而，一般使用的许多HTTP服务器都配置为在一段不活动状态之后删除持久连接，以便节省系统资源，而不会通知客户端。
 * 如果默认策略过于乐观，则可能需要提供自定义的保持活动策略。
 * 
 * 注意：长连接并不使用于所有的情况，尤其现在的系统，大都是部署在多台服务器上，且具有负载均衡的功能，
 * 如果我们在访问的时候，一直保持长连接，一旦那台服务器挂了，就会影响客户端，同时也不能充分的利用服务端的负载均衡的特性，
 * 反而短连接更有利一些，这些需要根据具体的需求来定，而不是一言概括。
 */  
@Configuration  
public class MyConnectionKeepAliveStrategy {  
      
    @Value("${httpclient.config.keepAliveTime}")  
    private int keepAliveTime = 30;  
      
    @Bean("connectionKeepAliveStrategy")  
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {  
        return new ConnectionKeepAliveStrategy() {  
  
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {  
                // Honor 'keep-alive' header  
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));  
                while (it.hasNext()) {  
                    HeaderElement he = it.nextElement();  
                    String param = he.getName();  
                    String value = he.getValue();  
                    if (value != null && param.equalsIgnoreCase("timeout")) {  
                        try {  
                            return Long.parseLong(value) * 1000;  
                        } catch (NumberFormatException ignore) {  
                        }  
                    }  
                }  
                return 30 * 1000;  
            }  
        };  
    }  
}
