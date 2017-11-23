package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.User;

public interface UserService {
	/**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age) throws Exception;

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name) throws Exception;

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers() throws Exception;
    
    /**
     * 批量新增用户
     * @param userList
     */
    void batchCreat(List<User> userList);
}
