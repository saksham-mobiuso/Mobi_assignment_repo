package com.mobi.springbootstarter.topic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;



@Service
public interface TopicRepository extends CrudRepository<Topic, String>{
	
	
}
