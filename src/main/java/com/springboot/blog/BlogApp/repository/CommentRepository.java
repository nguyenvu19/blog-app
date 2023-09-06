package com.springboot.blog.BlogApp.repository;

import com.springboot.blog.BlogApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
