package com.example.serviceFakeNews.service;

import com.example.serviceFakeNews.entity.TagEntity;
import com.example.serviceFakeNews.repository.SchemaRepo;
import com.example.serviceFakeNews.repository.TagRepo;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private SchemaRepo schemaRepo;

    public boolean existsSchemaBySchema(String schema) {
        return schemaRepo.existsSchemaBySchema(schema);
    }

}
