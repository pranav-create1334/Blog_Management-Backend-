package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{blogId}")
    public Comment addComment(@PathVariable Long blogId,
                              @RequestBody String content) {
        return commentService.addComment(blogId, content);
    }

    @GetMapping("/com/{blogId}")
    public List<Comment> getComments(@PathVariable Long blogId) {
        return commentService.getCommentsByBlog(blogId);
    }
}
