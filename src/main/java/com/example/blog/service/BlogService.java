package com.example.blog.service;

import com.example.blog.entity.AuditLog;
import com.example.blog.entity.Blog;
import com.example.blog.entity.Category;
import com.example.blog.entity.User;
import com.example.blog.repository.AuditLogRepository;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final AuditLogRepository auditLogRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BlogService(UserRepository userRepository,
                       BlogRepository blogRepository,
                       AuditLogRepository auditLogRepository,
                       CategoryRepository categoryRepository) {

        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.auditLogRepository = auditLogRepository;
        this.categoryRepository = categoryRepository;
    }

    // ✅ CREATE BLOG
    public Blog create(Blog blog, Long categoryId) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        blog.setAuthor(user);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        blog.setCategory(category);

        Blog savedBlog = blogRepository.save(blog);

        AuditLog auditLog = new AuditLog();
        auditLog.setAction("BLOG_CREATED");
        auditLog.setPerformedBy(username);
        auditLog.setBlogId(savedBlog.getId());
        auditLog.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);

        return savedBlog;
    }

    // ✅ GET BLOGS BY CATEGORY
    public List<Category> getBlogsByCategory(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    // ✅ PAGINATION
    public Page<Blog> getAllBlogs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogRepository.findAll(pageable);
    }

    // ✅ SEARCH WITH PAGINATION (FIXED 🔥)
    public Page<Blog> searchBlogs(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    // ✅ GET ALL (WITHOUT PAGINATION)
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    // ✅ DELETE BLOG
    public void delete(Long id) {

        Blog blog = blogRepository.findById(id).orElseThrow();
        blogRepository.delete(blog);

        AuditLog log = new AuditLog();
        log.setAction("DELETE_BLOG");
        log.setPerformedBy(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        log.setBlogId(id);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }

    public Blog getBlogById(long id) {
        return blogRepository.getById(id);
    }
}
