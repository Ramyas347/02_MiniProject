package com.example.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.LoginForm;
import com.example.binding.UserForm;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		boolean login = userService.login(loginForm);
		if(login) {
			return "Success";
		}else {
			return "Invalid credentials";
		}
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserForm userForm) {
		boolean register = userService.register(userForm);
		if(register) {
			return "successful";
		}
		return null;
}
}
