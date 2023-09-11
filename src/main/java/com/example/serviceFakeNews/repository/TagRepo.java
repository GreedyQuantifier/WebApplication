package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepo extends CrudRepository<TagEntity, Long> {
    List<TagEntity> findBySchemaStartingWith(String schema);

}
