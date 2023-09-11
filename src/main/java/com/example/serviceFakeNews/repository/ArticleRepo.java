package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.dto.ArticleWithSchema;
import com.example.serviceFakeNews.dto.SiteDto;
import com.example.serviceFakeNews.entity.ArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepo extends CrudRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAll(Pageable pageable);

    long count();

    List<ArticleEntity> findAll();

    ArticleEntity findByArticle(String article);


    @Query(value = "SELECT art.article AS article, art.description as description, art.body as body,art.image as image," +
            " art.title as title, tag.cluster AS cluster FROM articles art " +
            "INNER JOIN tags tag  ON art.article_id = tag.article_id " +
            "WHERE tag.schema = (SELECT sch.id FROM schemas sch WHERE sch.schema = :schema ) ORDER BY tag.cluster",
            nativeQuery = true)
    List<ArticleWithSchema> findBySchema(@Param("schema") String schema);


    @Query(value = "SELECT art.article AS article, art.description as description, art.body as body,art.image as image," +
            " art.title as title, tag.cluster AS cluster FROM articles art " +
            "INNER JOIN tags tag  ON art.article_id = tag.article_id " +
            "WHERE tag.schema = (SELECT sch.id FROM schemas sch WHERE sch.schema = :schema ) ORDER BY tag.cluster",
            nativeQuery = true)
    List<ArticleWithSchema> findBySchemaWithPageable(Pageable pageable, @Param("schema") String schema);
}
