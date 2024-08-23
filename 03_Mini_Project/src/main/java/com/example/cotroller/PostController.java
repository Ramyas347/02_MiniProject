package com.example.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.PostForm;
import com.example.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/save")
	public String savePost(@RequestBody PostForm postForm) {
	
		boolean save = postService.save(postForm);
		if(save) {
			return "Saved successfully";
		}
		return null;
	}
}
