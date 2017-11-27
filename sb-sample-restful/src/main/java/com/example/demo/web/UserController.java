package com.example.demo.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	//static ConcurrentMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUserList() {
		List<User> r = new ArrayList<User>(users.values());
        return r;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		users.put(user.getId(), user);
		return "success";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u = users.get(id);
		u.setAge(user.getAge());
		u.setName(user.getName());
		users.put(id, u);
		return "success";
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}
	
	/**
     * just 4 test
     * @return
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String deleteAllUser() {
		users.clear();
		return "success";
	}
}
