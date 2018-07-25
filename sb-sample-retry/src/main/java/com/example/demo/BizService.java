package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bizService")
public class BizService {
	
	public static final Logger logger = LoggerFactory.getLogger(BizService.class);
	
	@Autowired
	RetryService retryService;
	
	public Map<String, String> testRetry() {
        Map<String, String> map= new HashMap<String, String>();
        String s = retryService.retry();
        if (s.equals("6")){
        		logger.info("成功了");
        }else {
        		logger.info("失败了");
        }
        map.put("result",s);
        return map;
    }
	
}
