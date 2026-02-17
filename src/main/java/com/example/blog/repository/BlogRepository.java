package com.example.blog.repository;

import com.example.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
