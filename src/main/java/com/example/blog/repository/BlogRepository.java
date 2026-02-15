package com.example.blog.repository;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
//    List<Category> findByCategoryId(Long categoryId);
}
