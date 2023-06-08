package com.springboot.blog.BlogApp.service;

import com.springboot.blog.BlogApp.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);
}
