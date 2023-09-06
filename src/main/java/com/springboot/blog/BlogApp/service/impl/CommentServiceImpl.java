package com.springboot.blog.BlogApp.service.impl;

import com.springboot.blog.BlogApp.entity.Comment;
import com.springboot.blog.BlogApp.entity.PostEntity;
import com.springboot.blog.BlogApp.exception.ResourceNotFoundException;
import com.springboot.blog.BlogApp.payload.CommentDto;
import com.springboot.blog.BlogApp.repository.CommentRepository;
import com.springboot.blog.BlogApp.repository.PostRepository;
import com.springboot.blog.BlogApp.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

//        retrieve post entity by id
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId)
        );

//        set post to comment entity
        comment.setPost(postEntity);

//        save comment
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
