package com.vitu.code.services;

import java.util.List;

import com.vitu.code.payload.PostDto;
import com.vitu.code.payload.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto Postdto,Integer userId, Integer categoryId);
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(int postId);
	
	PostDto updatePost(PostDto postDto,int postId);
	
	void deletePost(int postId);
	
	List<PostDto> getPostsByUser(int userId);
	List<PostDto> getpostByCategory(int categoryId);
	List<PostDto> searchPost(String keyword);
	
	
}
