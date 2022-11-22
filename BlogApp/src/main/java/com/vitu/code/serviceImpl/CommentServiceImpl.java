package com.vitu.code.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitu.code.entity.Comment;
import com.vitu.code.entity.Post;
import com.vitu.code.exceptions.ResourceNotFoundException;
import com.vitu.code.payload.CommentDto;
import com.vitu.code.repository.CommentRepo;
import com.vitu.code.repository.PostRepo;
import com.vitu.code.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Override
	public CommentDto createComments(CommentDto commentDto, Integer postId) {

		Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("POST NOT FOUND WITH NTHIS ID "+postId));
		
		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = commentRepo.save(comment);
		CommentDto commentDtos = this.modelmapper.map(savedComment, CommentDto.class);
		return commentDtos;
	}

	@Override
	public void deleteComments(Integer commentId) {
	
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("COMMENT IS NOT FOUND BY USING THIS ID "+commentId));
		commentRepo.delete(comment);
	}

}
