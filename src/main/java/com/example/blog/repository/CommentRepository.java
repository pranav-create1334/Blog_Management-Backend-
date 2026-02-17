package com.example.blog.repository;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlog(Blog blog);
}
