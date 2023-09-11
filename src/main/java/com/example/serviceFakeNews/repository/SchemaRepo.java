package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.SchemaEntity;
import org.springframework.data.repository.CrudRepository;

public interface SchemaRepo extends CrudRepository<SchemaEntity, Long> {
    boolean existsSchemaBySchema(String schema);
}
