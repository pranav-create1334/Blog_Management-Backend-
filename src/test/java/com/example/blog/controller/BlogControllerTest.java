//package com.example.blog.controller;
//
//import com.example.blog.entity.Blog;
//import com.example.blog.service.BlogService;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BlogController.class)
//public class BlogControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BlogService blogService;
//
//    @Test
//    void getAllBlogsTest() throws Exception {
//
//        Blog blog = new Blog();
//        blog.setTitle("Test Blog");
//
//        Mockito.when(blogService.getAll())
//                .thenReturn((List<Blog>) Arrays.asList(blog));
//
//        mockMvc.perform(get("/api/blogs"))
//                .andExpect(status().isOk());
//    }
//}