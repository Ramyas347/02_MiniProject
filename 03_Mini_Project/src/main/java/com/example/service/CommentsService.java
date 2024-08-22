package com.example.service;

import java.util.List;

import com.example.binding.CommentForm;
import com.example.entity.CommentsEntity;

public interface CommentsService {

	public boolean addComment(CommentForm comntForm);
	
	public List<CommentsEntity> getComments(Integer postId);
}
