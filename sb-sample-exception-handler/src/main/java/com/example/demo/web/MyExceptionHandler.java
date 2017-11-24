package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.BizException;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class MyExceptionHandler {
	
	private static final Logger logger =  LoggerFactory.getLogger(MyExceptionHandler.class);

	/**
	 * 统一异常处理
	 * 处理自定义异常类型，ResponseBody返回
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ BizException.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseException processException(BizException exception) {
		logger.info("自定义异常处理-BizException");
		ResponseException responseException = new ResponseException(exception.getCode(), exception.getMessage());
		return responseException;
	}
	
	/**
	 * 统一异常处理
	 * 模版页返回
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(RuntimeException exception) {
		logger.info("自定义异常处理-RuntimeException");
		ModelAndView m = new ModelAndView();
		m.addObject("message", exception.getMessage());
		m.setViewName("error/500");
		return m;
	}

	/**
	 * 统一异常处理
	 * 模版页返回
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(Exception exception) {
		logger.info("自定义异常处理-Exception");
		ModelAndView m = new ModelAndView();
		m.addObject("message", exception.getMessage());
		m.setViewName("error/500");
		return m;
	}
	
	public static final class ResponseException{
		private String code;
		private String message;
		
		public ResponseException() {
			super();
		}
		public ResponseException(String code, String message) {
			super();
			this.code = code;
			this.message = message;
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
}
