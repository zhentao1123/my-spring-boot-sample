package com.example.demo.biz;

import com.example.demo.model.ResultR;

public interface ExecutorBiz {
	
	public ResultR<String> run(String param);
	
	public ResultR<String> kill(String jobId);
}
