package com.example.demo.model;

public class ResultR<R> {
	
	public static final String SUCCESS_CODE = "200";
	public static final String FAIL_CODE = "500";
	public static final ResultR<String> SUCCESS = new ResultR<String>(null);
	public static final ResultR<String> FAIL = new ResultR<String>(FAIL_CODE, null);
	
	private String code;
	private String message;
	private R data;
	
	public ResultR(){}
	public ResultR(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public ResultR(R data) {
		this.code = SUCCESS_CODE;
		this.data = data;
	}
	
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
