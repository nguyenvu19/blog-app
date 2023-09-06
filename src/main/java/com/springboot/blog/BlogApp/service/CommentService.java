package com.springboot.blog.BlogApp.service;

import com.springboot.blog.BlogApp.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
