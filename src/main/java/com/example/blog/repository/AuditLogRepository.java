package com.example.blog.repository;

import com.example.blog.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    // 🔹 Get all audit logs performed by a specific admin
    List<AuditLog> findByPerformedBy(String performedBy);

    // 🔹 Get all audit logs related to a specific blog
    List<AuditLog> findByBlogId(Long blogId);
}
