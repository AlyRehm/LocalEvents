package com.codingdojo.localEvents1.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.codingdojo.localEvents1.services.EventService;

public class EventController {
//	@Autowired
//	private UserService userService
	
	@Autowired
	private EventService eventService;
}
