package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;

@RequestMapping(value = "/v2/users")
public interface UserRestService {
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User> getUserList();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String postUser(@ModelAttribute User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable Long id);

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    String putUser(@PathVariable Long id, @ModelAttribute User user);

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    String deleteUser(@PathVariable Long id);
    
    /**
     * just 4 test
     * @return
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    String deleteAllUser();
}
