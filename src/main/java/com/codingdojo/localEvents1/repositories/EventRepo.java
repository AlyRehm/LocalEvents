package com.codingdojo.localEvents1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingdojo.localEvents1.models.Event;

@Repository
public interface EventRepo extends CrudRepository <Event, Long> {
	List<Event>findAll();
	
	Event findByIdIs(Long id);


	@Query(value="SELECT * FROM events e "
			+ "WHERE e.event_name LIKE %:keyword% "
			+ "OR e.location LIKE %:keyword% "
			+ "OR e.type LIKE %:keyword% "
			+ "OR e.description LIKE %:keyword% "
			+ "OR e.price LIKE %:keyword% "
			+ "OR e.event_date LIKE %:keyword% "
			, nativeQuery=true)
	List<Event> findByKeyword(@Param("keyword") String keyword);
	

}
