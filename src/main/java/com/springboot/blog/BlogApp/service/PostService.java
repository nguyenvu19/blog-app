package com.springboot.blog.BlogApp.service;

import com.springboot.blog.BlogApp.payload.PostDto;
import com.springboot.blog.BlogApp.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNumber, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
