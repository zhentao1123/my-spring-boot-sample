package com.example.demo.model;

public class ResultR<R> {
	private String code;
	private String message;
	private R data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public R getData() {
		return data;
	}
	public void setData(R data) {
		this.data = data;
	}
}
