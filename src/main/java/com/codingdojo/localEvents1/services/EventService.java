package com.codingdojo.localEvents1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.localEvents1.models.Event;
import com.codingdojo.localEvents1.repositories.EventRepo;

@Service
public class EventService {
	
	@Autowired
	private EventRepo eventRepo;

	
//GET EVENT BY ID
	public Event findEventById(Long id) {
		Optional<Event> result = eventRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
//FIND ALL EVENTS
	public List<Event> allEvents(){
		return eventRepo.findAll();
	}

//NEW EVENT
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}
	
//	UPDATE EVENT
	public Event updateEvent(Event event) {
		return eventRepo.save(event);
	}
	
//DELETE EVENT
	public void deleteEvent (Event event) {
		eventRepo.delete(event);
	}
}
