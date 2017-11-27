package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void test1() throws Exception {
		mockMvc.perform(
			get("/user/show1")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content("{\"name\":\"Jack\"}")
			.accept(MediaType.APPLICATION_JSON_UTF8)
		)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("{\"name\":\"Jack\"}"));
	}
	
	@Test
	public void test2() throws Exception {
		mockMvc.perform(
			get("/user/show2")
			.contentType(MediaType.TEXT_PLAIN)
			.param("name", "Rose")
			.accept(MediaType.APPLICATION_JSON_UTF8)
		)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("{\"name\":\"Rose\"}"));
	}

}
