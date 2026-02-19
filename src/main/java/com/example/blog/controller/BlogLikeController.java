package com.example.blog.controller;

import com.example.blog.entity.BlogLikeType;
import com.example.blog.service.BlogLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogLikeController {

    private final BlogLikeService blogLikeService;

    // Like / Dislike
    @PostMapping("/{blogId}/react")
    public String reactToBlog(
            @PathVariable Long blogId,
            @RequestParam BlogLikeType type
    ) {
        return blogLikeService.reactToBlog(blogId, type);
    }

    // Remove reaction
    @DeleteMapping("/{blogId}/react")
    public String removeReaction(@PathVariable Long blogId) {
        blogLikeService.removeReaction(blogId);
        return "Reaction removed";
    }

    // Like count (Public)
    @GetMapping("/{blogId}/likes")
    public long getLikes(@PathVariable Long blogId) {
        return blogLikeService.getLikeCount(blogId);
    }

    // Dislike count (Public)
    @GetMapping("/{blogId}/dislikes")
    public long getDislikes(@PathVariable Long blogId) {
        return blogLikeService.getDislikeCount(blogId);
    }
}
