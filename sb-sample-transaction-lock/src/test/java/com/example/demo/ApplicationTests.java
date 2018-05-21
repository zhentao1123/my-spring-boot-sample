package com.example.demo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	TestService service;
	
	@Autowired
	ThreadPoolComponent threadPool;

	@Before
	@Rollback(false)
	public void setUp() {
		service.init();
	}
	
	/**
	 * 同步
	 * 正常
	 */
	@Test
	@Rollback(false)
	public void update1_1() {
		for(int i=0; i<10; i++) {
			service.update1();
		}
	}
	
	/**
	 * 异步并发
	 * 脏读，不一致
	 */
	@Test
	@Rollback(false)
	public void update1_2() {
		for(int i=0; i<10; i++) {
			Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                		service.update1();
                		try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            });
			threadPool.submit(t);
		}
	}
	
	/**
	 * 异步并发，悲观锁
	 * 正常，
	 */
	@Test
	@Rollback(false)
	public void update2() {
		for(int i=0; i<10; i++) {
			Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                		service.update2();
                		/*
                		try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					*/
                }
            });
			threadPool.submit(t);
		}
	}
	
	/**
	 * 异步并发，乐观锁
	 * 正常，
	 */
	@Test
	@Rollback(false)
	public void update3() {
		for(int i=0; i<10; i++) {
			Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                		service.update3();
                		
                		try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
                }
            });
			threadPool.submit(t);
		}
	}
}
