package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByIdArticle(Long IdArticle);
}
