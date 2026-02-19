package com.example.blog.service;

import com.example.blog.entity.*;
import com.example.blog.entity.BlogLikeType;
import com.example.blog.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogLikeService {

    private final BlogLikeRepository blogLikeRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    // LIKE OR DISLIKE
    public String reactToBlog(Long blogId, BlogLikeType type) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        // Check if already reacted
        BlogLike existing = blogLikeRepository
                .findByBlogAndUser(blog, user)
                .orElse(null);

        if (existing != null) {
            // If same reaction → remove
            if (existing.getType() == type) {
                blogLikeRepository.delete(existing);
                return "Reaction removed";
            }
            // If different → update
            existing.setType(type);
            blogLikeRepository.save(existing);
            return "Reaction updated";
        }

        BlogLike blogLike = BlogLike.builder()
                .blog(blog)
                .user(user)
                .type(type)
                .createdAt(LocalDateTime.now())
                .build();

        blogLikeRepository.save(blogLike);

        return "Reaction added";
    }

    // REMOVE REACTION
    @Transactional
    public void removeReaction(Long blogId) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow();

        blogLikeRepository.deleteByBlogAndUser(blog, user);
    }

    // GET LIKE COUNT
    public long getLikeCount(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow();

        return blogLikeRepository.countByBlogAndType(blog, BlogLikeType.LIKE);
    }

    // GET DISLIKE COUNT
    public long getDislikeCount(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow();

        return blogLikeRepository.countByBlogAndType(blog, BlogLikeType.DISLIKE);
    }
}
