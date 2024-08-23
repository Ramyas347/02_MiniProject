package com.example.service;

import com.example.binding.LoginForm;
import com.example.binding.UserForm;

public interface UserService {

	public boolean login(LoginForm loginForm);
	
	public boolean register(UserForm userForm);
}
