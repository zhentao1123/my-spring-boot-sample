package com.example.demo.domain;

import java.util.Date;


public class User {

	private Long id;

	private Integer age;

	private Date createTime;

	private String name;

	public User() {
	}
	
	public User(Long id, String name, Integer age, Date createTime) {
		super();
		this.id = id;
		this.age = age;
		this.createTime = createTime;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
