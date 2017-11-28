package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void test() throws Exception {
		String level = "ERROR";
		
		//动态设置日志级别
		mockMvc.perform(
			post("/loggers/com.example.demo")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content("{\"configuredLevel\":\""+level+"\"}"));
		
		//打印日志验证
		mockMvc.perform(get("/test"));
	}

}
