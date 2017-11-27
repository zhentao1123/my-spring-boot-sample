package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests2 {

	Logger logger = LoggerFactory.getLogger(ApplicationTests2.class);
	
	@Autowired
    private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc.perform(get("/v2/users/clear"));
	}
	
	@Test
	public void postUser() throws Exception {
		ObjectMapper om = new ObjectMapper();
		
		User user = new User(1l, "Jack", 20);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("id", user.getId()+"");
		params.add("name", user.getName());
		params.add("age", user.getAge()+"");
		//String userJson = om.writeValueAsString(user);
		
		mockMvc.perform(post("/v2/users/").params(params));
		
		List<User> userList = Lists.newArrayList();
		userList.add(user);
		String userListJson =  om.writeValueAsString(userList);
		
		mockMvc
		.perform(get("/v2/users/"))
		.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(userListJson));
	}

	@Test
	public void getUser() throws Exception {
		ObjectMapper om = new ObjectMapper();
		
		//初始化
		User user = new User(1l, "Jack", 20);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("id", user.getId()+"");
		params.add("name", user.getName());
		params.add("age", user.getAge()+"");
		String userJson = om.writeValueAsString(user);
		
		mockMvc.perform(post("/v2/users/").params(params));
		
		//验证
		mockMvc
		.perform(get("/v2/users/"+user.getId()))
		.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(userJson));
	}
	
	@Test
	public void putUser() throws Exception {
		ObjectMapper om = new ObjectMapper();
		
		//创建
		User user = new User(1l, "Jack", 20);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("id", user.getId()+"");
		params.add("name", user.getName());
		params.add("age", user.getAge()+"");
		String userJson = om.writeValueAsString(user);
		mockMvc.perform(post("/v2/users/").params(params));
		
		//修改
		user = new User(1l, "Rost", 21);
		params = new LinkedMultiValueMap<String, String>();
		params.add("id", user.getId()+"");
		params.add("name", user.getName());
		params.add("age", user.getAge()+"");
		userJson = om.writeValueAsString(user);
		mockMvc.perform(put("/v2/users/"+user.getId()).params(params));
		
		//验证
		mockMvc
		.perform(get("/v2/users/"+user.getId()))
		.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(userJson));
	}
	
	@Test
	public void deleteUser() throws Exception {
		ObjectMapper om = new ObjectMapper();
		
		//初始化
		User user = new User(1l, "Jack", 20);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("id", user.getId()+"");
		params.add("name", user.getName());
		params.add("age", user.getAge()+"");
		//String userJson = om.writeValueAsString(user);
		
		mockMvc.perform(post("/v2/users/").params(params));
		
		mockMvc.perform(delete("/v2/users/"+user.getId()));
		
		//验证
		mockMvc
		.perform(get("/v2/users/"))
		.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
	}
}
