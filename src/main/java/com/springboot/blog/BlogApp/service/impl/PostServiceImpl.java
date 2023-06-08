package com.springboot.blog.BlogApp.service.impl;

import com.springboot.blog.BlogApp.entity.PostEntity;
import com.springboot.blog.BlogApp.payload.PostDto;
import com.springboot.blog.BlogApp.repository.PostRepository;
import com.springboot.blog.BlogApp.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    /*
    If a class is configured as a spring bean and it has only
    one constructor then we can omit the @Autowired annotation
     */
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
//        Convert DTO to entity
        PostEntity post = new PostEntity();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        PostEntity newPost = postRepository.save(post);

//        Convert entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
