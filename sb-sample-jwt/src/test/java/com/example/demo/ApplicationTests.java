package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private static final Log logger = LogFactory.getLog(ApplicationTests.class);
	
	@Test
	public void contextLoads() {
		String secret1 = JwtUtil.createKey();
		String secret2 = JwtUtil.createKey();
		
		logger.debug("secret1 : " + secret1);
		logger.debug("secret2 : " + secret2);
		
		//Test1
		if(false){
			String jwt = JwtUtil.createJWT("subject", secret1, 0);
			try {
				JwtUtil.parseJWT(jwt, secret2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Test2
		if(true){
			String jwt = JwtUtil.createJWT("subject", secret1, 1500l);
			for(int i=0; i<10; i++) {
				try {
					JwtUtil.parseJWT(jwt, secret1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					Thread.currentThread().sleep(100l);
				} catch (InterruptedException e) {}
			}
		}
	}

}
