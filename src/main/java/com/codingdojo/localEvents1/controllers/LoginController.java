package com.codingdojo.localEvents1.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.localEvents1.models.Event;
import com.codingdojo.localEvents1.models.LoginUser;
import com.codingdojo.localEvents1.models.User;
import com.codingdojo.localEvents1.services.EventService;
import com.codingdojo.localEvents1.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/")
	public String index () {
		return "redirect:/login";
	}
	
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
	@GetMapping("/account")
	public String account(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		return "account.jsp";
	}
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
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User user = userService.login(newLogin, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		} else {
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		User user = userService.register(newUser, result);
		
		if (user == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "register.jsp";
		} else {
			session.setAttribute("userId", newUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
}
