package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

@RestController
public class UserRestServiceImpl implements UserRestService{

	//static ConcurrentMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
	
	@Override
	public List<User> getUserList() {
		List<User> r = new ArrayList<User>(users.values());
        return r;
	}

	@Override
	public String postUser(@ModelAttribute User user) {
		users.put(user.getId(), user);
		return "success";
	}

	@Override
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}

	@Override
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u = users.get(id);
		u.setAge(user.getAge());
		u.setName(user.getName());
		users.put(id, u);
		return "success";
	}

	@Override
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

	@Override
	public String deleteAllUser() {
		users.clear();
		return "success";
	}
}
