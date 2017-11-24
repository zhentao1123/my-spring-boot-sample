package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.exception.BizException;

@Controller
@RequestMapping(path="/")
public class DemoController {
	
	@RequestMapping
	@ResponseBody
	public String index() {
		return "Hello";
	}
	
	@RequestMapping(path="test1")
	@ResponseBody
	public String testBizException(Integer a) throws Exception{
		if(null!=a && a==1) {
			throw new BizException("1","Something BizException is hapening");
		}
		return "test1";
	}
	
	@RequestMapping(path="test2")
	@ResponseBody
	public String testException(Integer a) throws Exception{
		if(null!=a && a==1) {
			throw new Exception("Something Exception is hapening");
		}
		return "test2";
	}
	
	@RequestMapping(path="test3")
	@ResponseBody
	public String testRuntimeException(Integer a) throws Exception{
		if(null!=a && a==1) {
			throw new RuntimeException("Something RuntimeException is hapening");
		}
		return "test1";
	}
}
