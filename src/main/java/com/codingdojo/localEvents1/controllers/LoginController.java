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

import com.codingdojo.localEvents1.models.LoginUser;
import com.codingdojo.localEvents1.models.User;
import com.codingdojo.localEvents1.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index () {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User user = userService.login(loginUser, result);
		
		if (user == null) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		} else {
			session.setAttribute("userID", user.getId());
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
			session.setAttribute("userID", newUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userID", null);
		return "redirect:/";
	}
}
