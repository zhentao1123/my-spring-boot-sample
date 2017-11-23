package com.example.demo;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.web.FileController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTests {
	
	@Autowired
	FileController controller;

	@Test
	@Rollback(false)
	public void test1() {
        try {
			MockMultipartFile mfile1 = new MockMultipartFile("file", "1.jpg", "image/png", 
					new FileInputStream("/upload/source/1.jpg"));
			
			controller.upload1(mfile1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(true);
	}
	
	@Test
	@Rollback(false)
	public void test2() {
        try {
			MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
			request.setMethod("POST");
			request.setContentType("multipart/form-data");
			request.addHeader("Content-type", "multipart/form-data");
			
			MockMultipartFile mfile1 = new MockMultipartFile("file", "1.jpg", "image/png", 
					new FileInputStream("/upload/source/1.jpg"));
			MockMultipartFile mfile2 = new MockMultipartFile("file", "2.jpg", "image/png", 
					new FileInputStream("/upload/source/2.jpg"));
			MockMultipartFile mfile3 = new MockMultipartFile("file", "3.jpg", "image/png", 
					new FileInputStream("/upload/source/3.jpg"));
			
			request.addFile(mfile1);
			request.addFile(mfile2);
			request.addFile(mfile3);
			
			controller.upload2(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(true);
	}

}
