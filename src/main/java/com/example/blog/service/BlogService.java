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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

    UserRepository userRepository;
    AuditLogRepository auditLogRepository;
    @Autowired
    private final BlogRepository blogRepository;
    @Autowired
    private final CategoryRepository categoryRepository;


    public BlogService(UserRepository userRepository,
                       BlogRepository blogRepository,
                       AuditLogRepository auditLogRepository, CategoryRepository categoryRepository) {

        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.auditLogRepository = auditLogRepository;  // ✅ IMPORTANT
        this.categoryRepository = categoryRepository;
    }

    public Blog create(Blog blog, Long categoryId)
    {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        blog.setAuthor(user);

        // ✅ NEW: Set Category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        blog.setCategory(category);

        // ✅ 1️⃣ Save Blog
        Blog savedBlog = blogRepository.save(blog);

        // ✅ 2️⃣ Create Audit Log
        AuditLog auditLog = new AuditLog();
        auditLog.setAction("BLOG_CREATED");
        auditLog.setPerformedBy(username);
        auditLog.setBlogId(savedBlog.getId());
        auditLog.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);

        return savedBlog;
    }
    public List<Category> getBlogsByCategory(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }



    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

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
}
