package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class AuditLog
{
    @Id
    @GeneratedValue
    private Long id;

    private String action;
    private String performedBy;
    private Long blogId;
    private LocalDateTime timestamp;
}
