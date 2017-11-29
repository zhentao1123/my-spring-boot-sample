package com.example.demo.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(hidden=false, tags="User接口")
public class UserController {
	
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
	
	/**
	 * get user list
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUserList() {
		List<User> r = new ArrayList<User>(users.values());
        return r;
	}

	/**
	 * create user
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户对象", required = true, dataType = "User")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postUser(@RequestBody User user) {
		users.put(user.getId(), user);
		return "success";
	}

	/**
	 * get one user
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取用户详细信息", notes="id来获取用户详细信息")
	//@PathVariable参数不要@ApiImplicitParam标记
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}

	/**
	 * modify user
	 * @param id
	 * @param user
	 * @return
	 */
	@ApiOperation(value="更新用户详细信息", notes="根据user对象来更新用户详细信息")
	/*
	 * @PathVariable参数不要@ApiImplicitParam标记
	 */
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "user", value = "用户对象", required = true, dataType = "User")
	})
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @RequestBody User user) {
		User u = users.get(id);
		u.setAge(user.getAge());
		u.setName(user.getName());
		users.put(id, u);
		return "success";
	}

	/**
	 * delete user
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除用户", notes="根据用户ID删除用户")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}
	
	/**
	 * delete all users
     * just 4 test
     * @return
     */
	@ApiOperation(value="删除所有用户", notes="删除所有用户")
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String deleteAllUser() {
		users.clear();
		return "success";
	}
}
