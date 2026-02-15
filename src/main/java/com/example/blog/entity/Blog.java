package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    // Many blogs -> One category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
