package com.codingdojo.localEvents1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.localEvents1.models.Event;

@Repository
public interface EventRepo extends CrudRepository <Event, Long> {
	List<Event>findAll();
	
	Event findByIdIs(Long id);
}
