package com.springboot.blog.BlogApp.controller;

import com.springboot.blog.BlogApp.payload.PostDto;
import com.springboot.blog.BlogApp.payload.PostResponse;
import com.springboot.blog.BlogApp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //    Get all posts
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return postService.getAllPosts(pageNumber,pageSize);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<PostDto>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted success", HttpStatus.OK);
    }
}
