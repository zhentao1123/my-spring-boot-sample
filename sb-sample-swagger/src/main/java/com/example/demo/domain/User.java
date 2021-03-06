package com.example.demo.domain;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel //不设置也没关系
public class User {
	
	//@ApiModelProperty(hidden = true) //这个有点用
	private Long id;
	private String name;
	private Integer age;
	private Date createTime;
	
	public User() {
		super();
	}
	public User(Long id, String name, Integer age, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
