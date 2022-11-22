package com.vitu.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vitu.code.entity.Category;
import com.vitu.code.entity.Post;
import com.vitu.code.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User userId);
	List<Post> findByCategory(Category categoryId);
	
	//@Query("select p from posts p where p.title like :key")
	List<Post> findByTitleContaining(String title);
}
