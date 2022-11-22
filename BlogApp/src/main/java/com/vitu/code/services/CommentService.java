package com.vitu.code.services;

import com.vitu.code.payload.CommentDto;

public interface CommentService {
	CommentDto createComments(CommentDto commentDto,Integer postId);
	void deleteComments(Integer commentId);
}
