package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findByIdArticle(Long IdArticle);
}
