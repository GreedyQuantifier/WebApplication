package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.SchemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemaRepo extends JpaRepository<SchemaEntity, Long> {
    boolean existsSchemaBySchema(String schema);
}
