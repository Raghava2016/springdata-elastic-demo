package com.raghava.learning.springelasticdemo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.raghava.learning.springelasticdemo.model.User;
import com.raghava.learning.springelasticdemo.repository.UsersRepository;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping(value = "/name/{text}")
	public List<User> searchByName(@PathVariable final String text)
	{
		return usersRepository.findByName(text);
	}
	
	@GetMapping(value = "/salary/{salary}")
	public List<User> searchBySalary(@PathVariable final Long salary)
	{
		return usersRepository.findBySalary(salary);
	}
	
	@GetMapping(value="/all")
	public List<User> findAll()
	{
		List<User> users = Lists.newArrayList();
		usersRepository.findAll().forEach(users::add);		 
		return users;
	}
}
