package com.example.demo.exception;

@SuppressWarnings("serial")
public class BizException extends Exception{

	private String code;
	
	private String message;
	
	public BizException() {
		super();
	}
	
	public BizException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
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
	
}
