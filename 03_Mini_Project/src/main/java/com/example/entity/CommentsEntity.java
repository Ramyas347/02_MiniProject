package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
//Dont write @Data when we are having bidirectional relationship as its call toString method and raises stackoverflow error
@Getter
@Setter
public class CommentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	private String name;
	private String email;
	
	@Lob
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	@JsonBackReference
	private PostEntity postEntity;
}
