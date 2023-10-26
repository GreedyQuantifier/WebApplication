package com.example.serviceFakeNews.service;

import com.example.serviceFakeNews.dto.*;
import com.example.serviceFakeNews.entity.ArticleEntity;
import com.example.serviceFakeNews.entity.CommentEntity;
import com.example.serviceFakeNews.exception.NotFoundException;
import com.example.serviceFakeNews.repository.ArticleRepo;
import com.example.serviceFakeNews.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthService authService;


    public Model getPage(String site, Model model) throws NotFoundException {
        ArticleEntity page = Optional.ofNullable(articleRepo.findByArticle(site)).orElseThrow(() -> new NotFoundException("That article not found"));


        List<CommentEntity> comments = commentService.getComments(page.getId());

        model.addAttribute("page", page);
        model.addAttribute("comments", comments);
        model.addAttribute("metrics", ModelDtoMapper.toModelDto(page, comments));


        return model;
    }


    public Model getIndex(Model model, String page, String countArticle, String query, String schema) {
        Long count = schema.isEmpty() ? articleRepo.count() : articleRepo.countSchemas(schema);
        Pagination pagination = new Pagination(count, page, countArticle);
        pagination.createQuery(schema, query);

        Pageable of = pagination.pageable();


        model.addAttribute("pagination", pagination);

        if (!schema.isEmpty()) {
            model.addAttribute("articlesClustering", SiteClusteringDtoMapper.articlesToSites(articleRepo.findBySchemaWithPageable(of, schema)));
        } else {
            Page<ArticleEntity> all = articleRepo.findAll(of);
            model.addAttribute("articles", all.stream().map(SiteDtoMapper::toSiteDto).toList());
        }
        model.addAttribute("auth", authService);

        return model;
    }

    public List<ModelDto> getData() {
        return articleRepo.findAll().stream().map(entity ->
                ModelDtoMapper.toModelDto(entity, commentService.getComments(entity.getId()))).collect(Collectors.toList());
    }


    public List<ArticleWithSchema> getArticleWithClustering(String schema) {
        return articleRepo.findBySchema(schema);
    }

}
