package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service("retryService")
public class RetryService {
	
	public static final Logger logger = LoggerFactory.getLogger(RetryService.class);
	
    int i=1;
    
    /**
     * @Retryable：标注此注解的方法在发生异常时会进行重试
     * 参数说明：value：抛出指定异常才会重试
     *         include：和value一样，默认为空，当exclude也为空时，默认所有异常
     *         exclude：指定不处理的异常
     *         maxAttempts:最大重试次数，默认3次
     *         backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，multiplier（指定延迟倍数）
     *                 默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为2，则第一次重试为1秒，第二次为
     *                 2秒，第三次为4秒
     * @return
     */
    @Retryable(value = {RuntimeException.class}, maxAttempts = 4, backoff = @Backoff(delay = 1000l, multiplier = 1))
    public String retry() {
    		logger.info("测试retry");
        i++;//生产环境此处应该为调用第三方接口，判断接口返回code
        if(i==6){
            return i+"";
        }
        RetryException retryException= new RetryException("9999","连接超时");
        throw retryException;
    }
 
    /**
     * @Recover：用于@Retryable重试失败后处理方法
	 *			此方法里的异常一定要是@Retryable方法里抛出的异常，否则不会调用这个方法
     * @param e
     * @return
     */
    @Recover
    public String recover(RetryException e){
    		logger.info("recover");
    		logger.info(e.getMessage());
        return "recover";
    }
    
}
