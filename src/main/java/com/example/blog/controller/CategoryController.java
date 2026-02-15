package com.example.blog.controller;

import com.example.blog.entity.Category;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    private final BlogService blogService;

    public CategoryController(CategoryService categoryService, BlogService blogService) {
        this.categoryService = categoryService;
        this.blogService = blogService;
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/category/{categoryId}")
    public List<Category> getBlogsByCategory(@PathVariable Long categoryId) {
        return blogService.getBlogsByCategory(categoryId);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
