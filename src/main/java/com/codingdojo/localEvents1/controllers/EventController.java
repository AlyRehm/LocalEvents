package com.codingdojo.localEvents1.controllers;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.localEvents1.models.Event;
import com.codingdojo.localEvents1.models.User;
import com.codingdojo.localEvents1.services.EventService;
import com.codingdojo.localEvents1.services.UserService;

@Controller
public class EventController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;

	
// DASHBOARD
	@GetMapping("/dashboard")
	public String dashboard (Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("events", eventService.allEvents());
		return "dashboard.jsp";
	} 
	
//	SEARCH FEATURE
	@GetMapping("/search")
	public String search(Model model, String keyword) {
		if (keyword != null) {
			model.addAttribute("events", eventService.findByKeyword(keyword)) ;
		} else {
			model.addAttribute("events", eventService.allEvents());
		}
		return "searchDashboard.jsp";
	}
			
			
// CREATE NEW EVENT
	@GetMapping("/event/new")
	public String newEvent(@ModelAttribute("event") Event event, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		model.addAttribute("user", user);


		return "newEvent.jsp";

	}
	@PostMapping("/events/new")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "newEvent.jsp";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		eventService.createEvent(event);
		Event unassignedEvent = eventService.findEventById(event.getId());
		user.getEvents().add(unassignedEvent);
		userService.updateUser(user);
		unassignedEvent.setUser(user);
		eventService.updateEvent(unassignedEvent);
		return "redirect:/dashboard";
	}
	
// VIEW EVENT DETAILS
	@GetMapping("/account")
	public String account(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("userEvents", user.getEvents());
//		model.addAttribute("attendedEvents", user.getAttendingEvents());
		return "account.jsp";
	}
		
		
	@GetMapping("/event/{id}")
	public String viewEvent(@PathVariable("id") Long id, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
			}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		Event event = eventService.findEventById(id);
		model.addAttribute("event", event);
			return "eventDetails.jsp";
	}

	
// EDIT EVENT
	@GetMapping("/event/{id}/editEvent")
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
			return "redirect:/dashboard";}
		}
	
	
// DELETE
	@RequestMapping("/events/delete/{id}")
	public String deleteEvent(@PathVariable("id")Long id, HttpSession session, Model model) {
	if(session.getAttribute("userId") == null) {
		return "redirect:/logout";
	}
	Event event = eventService.findEventById(id);
	eventService.deleteEvent(event);
	return "redirect:/dashboard";
	}
}

	//ADDING ATTENDING EVENT TO USER
//	@RequestMapping("/events/attend/{id}")
//	public String attendEvent(@PathVariable("id") Long id, HttpSession session, Model model) {
//		if(session.getAttribute("userId") == null) {
//			return "redirect:/logout";
//		}
//		Long userId = (Long) session.getAttribute("userId");
//		User user = userService.findById(userId);
	//	Event event = eventService.findEventById(id); 
	//	user.getAttendingEvents().add(event);
	//	userService.updateUser(user);
	//	return "redirect:/dashboard";
//	}

