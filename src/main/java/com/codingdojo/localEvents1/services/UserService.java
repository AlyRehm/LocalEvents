package com.codingdojo.localEvents1.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.localEvents1.models.LoginUser;
import com.codingdojo.localEvents1.models.User;
import com.codingdojo.localEvents1.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());
		
		if(!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "User not found!");
			return null;
		}
		User user = potentialUser.get();
		
		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
        	result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if(result.hasErrors()) {
        return null;
    }	
        return user;
    }
		
		
//		if (!potentialUser.isPresent() || !BCrypt.checkpw(user.getPassword(), potentialUser.get().getPassword())){
	//		return null;
//		}
//		return potentialUser.get();
	
	
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", null, "An account with this email already exists.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", null, "Passwords do not match.");
		}
		if (result.hasErrors()) {
			return null;
		} else {
			String hashPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashPW);
			return userRepository.save(newUser);
		}
	}

	public User findById(Long id) {
		Optional <User> potentialUser = userRepository.findById(id);
		if (!potentialUser.isPresent()) {
			return null;
		} else {
			return potentialUser.get();
		}
	}
	
	public List<User> allUsers(){
		return userRepository.findAll();
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
