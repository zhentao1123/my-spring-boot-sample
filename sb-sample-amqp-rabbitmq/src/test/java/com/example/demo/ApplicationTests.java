package com.example.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.rabbitmq.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private Sender sender;

	@Test
    public void sendTest() throws Exception {
		int i = 10;
        while(i-->0){
            String msg = new Date().toString();
            sender.sendText(msg);
            Thread.sleep(1000);
        }
    }

}
