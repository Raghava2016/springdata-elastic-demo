package com.raghava.learning.springelasticdemo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.raghava.learning.springelasticdemo.model.User;

public interface UsersRepository extends ElasticsearchRepository<User, Long> {

	List<User> findByName(String text);

	List<User> findBySalary(Long salary);

}
