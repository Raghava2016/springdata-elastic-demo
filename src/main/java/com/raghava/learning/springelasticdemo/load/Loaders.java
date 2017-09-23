package com.raghava.learning.springelasticdemo.load;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.raghava.learning.springelasticdemo.model.User;
import com.raghava.learning.springelasticdemo.repository.UsersRepository;

@Component
public class Loaders {
     @Autowired
     ElasticsearchOperations elasticsearchOperations;
    
     @Autowired
     UsersRepository usersRepository;
	
	 @PostConstruct
	 @Transactional
	 public void loadAll()
	 {
		 elasticsearchOperations.putMapping(User.class);
		 System.out.println("Loading Data");
		 usersRepository.save(getData());
		 System.out.println("Loading Completed");
	 }

	private List<User> getData() {
		List<User> users = new ArrayList<User>();
		users.add(new User("Test1", 1L, "TN1", 12000L));
		users.add(new User("Test2", 2L, "TN2", 14000L));
		users.add(new User("Test3", 3L, "TN3", 16000L));
		users.add(new User("Test4", 4L, "TN4", 18000L));
		
		return users;
	}
}
