package com.springboot.blog.BlogApp.service.impl;

import com.springboot.blog.BlogApp.entity.PostEntity;
import com.springboot.blog.BlogApp.exception.ResourceNotFoundException;
import com.springboot.blog.BlogApp.payload.PostDto;
import com.springboot.blog.BlogApp.repository.PostRepository;
import com.springboot.blog.BlogApp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        PostEntity postEntity = mapToEntity(postDto);

        PostEntity newPostEntity = postRepository.save(postEntity);

//        Convert entity to DTO
        PostDto postResponse = mapToDto(newPostEntity);

        return postResponse;

    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDto(postEntity);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());

        PostEntity updatePost = postRepository.save(postEntity);

        return mapToDto(updatePost);
    }

    @Override
    public void deletePost(long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(postEntity);
    }

    private PostDto mapToDto(PostEntity postEntity) {
        PostDto postDto = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setContent(postEntity.getContent());
        postDto.setDescription(postEntity.getDescription());
        return postDto;
    }

    private PostEntity mapToEntity(PostDto postDto) {
        PostEntity post = new PostEntity();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
