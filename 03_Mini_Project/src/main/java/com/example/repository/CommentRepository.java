package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CommentsEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity, Integer>{

	public List<CommentsEntity> findByPostEntity_PostId(Integer postId);
}
