package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.LoginForm;
import com.example.binding.UserForm;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean login(LoginForm loginForm) {
		// TODO Auto-generated method stub
		//TODO email and password -> valid
		UserEntity userEntity = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		if(userEntity!=null) {
			session.setAttribute("userId", userEntity.getUserId());
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean register(UserForm userForm) {
		// TODO Auto-generated method stub
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userForm, userEntity);
		UserEntity save = userRepo.save(userEntity);
		return true;
	}
	
	
}
