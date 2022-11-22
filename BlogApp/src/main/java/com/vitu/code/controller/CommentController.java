package com.vitu.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitu.code.payload.CommentDto;
import com.vitu.code.serviceImpl.CommentServiceImpl;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	@PostMapping("/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable int postId)
	{
		CommentDto createComments = commentServiceImpl.createComments(commentDto, postId);
	
		return new ResponseEntity<CommentDto>(createComments,HttpStatus.CREATED);
	
	}
	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId)
	{
commentServiceImpl.deleteComments(commentId);
	
		return new ResponseEntity<String>("COMMENET HAS BEEN DELETED SUCCESSFULLY!!..",HttpStatus.OK);
	

	}
	
}
