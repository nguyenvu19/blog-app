package com.springboot.blog.BlogApp.service;

import com.springboot.blog.BlogApp.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
