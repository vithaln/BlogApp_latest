package com.vitu.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitu.code.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

	
}
