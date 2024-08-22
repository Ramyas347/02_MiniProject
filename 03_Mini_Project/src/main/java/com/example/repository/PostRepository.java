package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
