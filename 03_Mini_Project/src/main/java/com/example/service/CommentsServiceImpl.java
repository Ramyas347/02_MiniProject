package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.binding.CommentForm;
import com.example.entity.CommentsEntity;
import com.example.entity.PostEntity;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean addComment(CommentForm comntForm) {
		// TODO Auto-generated method stub
		Integer postId = (Integer)session.getAttribute("postId");
		
		if(postId!=null) {
			Optional<PostEntity> findById = postRepo.findById(postId);
			PostEntity postEntity = findById.get();
			
			CommentsEntity comntEntity = new CommentsEntity();
			BeanUtils.copyProperties(comntForm, comntEntity);
			comntEntity.setPostEntity(postEntity);
			commentRepo.save(comntEntity);
			return true;
		}
		
		return false;
	}

	public List<CommentsEntity> getComments(Integer postId) {
		// TODO Auto-generated method stub
		//List<CommentsEntity> comments = commentRepo.findByPostEntity_PostId(postId);
		return commentRepo.findByPostEntity_PostId(postId);
    }
}
	
	
	

