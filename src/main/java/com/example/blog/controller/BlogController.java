package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Category;
import com.example.blog.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
// only user can create blog..
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public Blog create(@RequestBody Blog blog,
                       @RequestParam Long categoryId) {
        return blogService.create(blog, categoryId);
    }
//    @GetMapping("/category/{categoryId}")
//    public List<Category> getBlogsByCategory(@PathVariable Long categoryId) {
//        return blogService.getBlogsByCategory(categoryId);
//    }

    @GetMapping("/debug")
    public String debug() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @GetMapping("/all")
    public List<Blog> all() {
        return blogService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        blogService.delete(id);
    }
    // pagination::
    @GetMapping("/page/all")
    public Page<Blog> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return blogService.getAllBlogs(page, size);
    }

    @GetMapping("/search")
    public Page<Blog> searchBlogs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return blogService.searchBlogs(keyword, page, size);
    }

}
