package com.example.blog.repository;

import com.example.blog.entity.BlogLike;
import com.example.blog.entity.Blog;
import com.example.blog.entity.User;
import com.example.blog.entity
        .BlogLikeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogLikeRepository extends JpaRepository<BlogLike, Long> {

    Optional<BlogLike> findByBlogAndUser(Blog blog, User user);

    long countByBlogAndType(Blog blog, BlogLikeType type);

    void deleteByBlogAndUser(Blog blog, User user);
}
