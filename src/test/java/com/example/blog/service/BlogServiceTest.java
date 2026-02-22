package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @Test
    void getBlogByIdTest() {

        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Mockito Blog");

        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));

        Blog result = blogService.getBlogById(1L);

        assertEquals("Mockito Blog", result.getTitle());
    }
}