package com.example.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.PostForm;
import com.example.entity.PostEntity;
import com.example.entity.UserEntity;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class PostSerrviceImpl implements PostService{

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private HttpSession session;
	
	public boolean save(PostForm postForm) {
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId != null) { // Ensure userId is not null
			Optional<UserEntity> findById = userRepo.findById(userId);
			UserEntity userEntity = findById.get();
	        PostEntity postEntity = new PostEntity();
	        
	        // Set userId to the postEntity
	        postEntity.setUser(userEntity);
	        
	        BeanUtils.copyProperties(postForm, postEntity);
	        postRepo.save(postEntity);
	        
	        session.setAttribute("postId", postEntity.getPostId());
	        return true;
	    }
		
	    return false;
		
	}

}
