package com.example.blog.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    // One category -> Many blogs
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Blog> blogs;

    public Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return categoryId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<Blog> getBlogs() { return blogs; }

    public void setBlogs(List<Blog> blogs) { this.blogs = blogs; }
}
