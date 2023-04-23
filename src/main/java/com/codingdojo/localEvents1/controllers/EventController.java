package com.codingdojo.localEvents1.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.localEvents1.models.Event;
import com.codingdojo.localEvents1.services.EventService;

public class EventController {
//	@Autowired
//	private UserService userService
	
	@Autowired
	private EventService eventService;

//TO DO
	//CHECK THE 'USER' NAMES IN ALL THE ROUTES. ENSURE THEY MATCH UP WITH THE LOGIN/REG MODELS/SERVICES/CONTROLLERS
	
//DASHBOARD ROUTE
	@GetMapping("/dashboard")
	public String dashboard (Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long)session.getAttribute("userId");
		User user = userService.findById(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("events", eventService.allEvents())
		return "dashboard.jsp";
	}
	
//CREATE NEW EVENT
	@GetMapping("/events/new")
	public String newEvent(@ModelAttribute("event")Event event, HttpSession session,Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long)session.getAttribute("userId");
			event.setUser(userService.findById(userId));
		
		if (result.hasErrors()) {
			return "newEvent.jsp";
		}
		eventService.createEvent(event);
		return "redirect:/dashboard";
	}
	
//VIEW EVENT DETAILS
	@GetMapping("/event/{id}")
	public String viewEvent(@PathVariable("id")Long id, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
			}
		Event event = eventService.findEventById(id);
		model.addAttribute("event", event);
			return "eventDetails.jsp";
	}
	
//EDIT EVENT
	@GetMapping("/event/{id}/edit")
	public String editEvent(@PathVariable("id") Long id, @Valid @ModelAttribute("event") Event event, BindingResult result, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
			}
		event = eventService.findEventById(id);
		model.addAttribute("event", event);
			return "editEvent.jsp";
	}
	
	@PostMapping("/events/{id}/edit")
	public String editEvent(@Valid @ModelAttribute("event") Event event,BindingResult result, @PathVariable("id") Long id, HttpSession session ) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
			}
		if (result.hasErrors()) {
			return "editEvent.jsp";
		}
		else {
			event.setId(id);
			eventService.updateEvent(event);
			return "redirect:/events";
		}
	}
	
//DELETE
	@RequestMapping("/events/delete/{id}")
	public String deleteEvent(@PathVariable("id")Long id, HttpSession session)
	if(session.getAttribute("userId") == null) {
		return "redirect:/logout";
	}
	Event event = eventService.findEventById(id);
	eventService.deleteEvent(event);
	return "redirect:/events";
}
