package com.example.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.CommentForm;
import com.example.entity.CommentsEntity;
import com.example.service.CommentsService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentController {

	@Autowired
	private CommentsService cmmntService;
	
	@Autowired
	private HttpSession session;
	
	
	@PostMapping("/addComment")
	public String addComment(@RequestBody CommentForm cmntForm) {
		
		boolean comment = cmmntService.addComment(cmntForm);
		if(comment) {
			return "Comment added";
		}
		return null;
	}
	
	@GetMapping("/viewComments/{postId}")
	public List<CommentsEntity> viewComment(@PathVariable Integer postId) {
		
		return cmmntService.getComments(postId);
		
	}
}
