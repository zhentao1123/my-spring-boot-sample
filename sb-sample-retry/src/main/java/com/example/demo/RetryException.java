package com.example.demo;

public class RetryException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;
    private String message;
    
	public RetryException() {
		super();
	}
	public RetryException(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
	public RetryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public RetryException(String message, Throwable cause) {
		super(message, cause);
	}
	public RetryException(String message) {
		super(message);
	}
	public RetryException(Throwable cause) {
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
