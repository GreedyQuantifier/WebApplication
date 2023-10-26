package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findBySchemaStartingWith(String schema);

}
