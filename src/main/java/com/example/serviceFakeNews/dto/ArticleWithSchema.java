package com.example.serviceFakeNews.dto;

import com.example.serviceFakeNews.entity.ArticleEntity;

public interface ArticleWithSchema {
    String getArticle();

    String getDescription();

    String getBody();

    String getImage();

    String getTitle();

    String getCluster();
}
