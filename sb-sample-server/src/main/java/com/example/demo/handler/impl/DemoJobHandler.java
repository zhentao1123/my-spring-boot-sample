package com.example.demo.handler.impl;

import org.springframework.stereotype.Component;

import com.example.demo.handler.IJobHandler;
import com.example.demo.handler.annotation.JobHandler;
import com.example.demo.model.ResultR;

@JobHandler(value="demoJobHandler")
@Component
public class DemoJobHandler extends IJobHandler{

	@Override
	public ResultR<String> execute(String param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
