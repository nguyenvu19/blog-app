package com.springboot.blog.BlogApp.repository;

import com.springboot.blog.BlogApp.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
