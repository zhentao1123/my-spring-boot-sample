package com.example.demo.handler;

import com.example.demo.model.ResultR;

public abstract class JobHandler {

	public abstract ResultR<String> execute(String param) throws Exception;

	public void init() {
		// TODO
	}

	public void destroy() {
		// TODO
	}
}
